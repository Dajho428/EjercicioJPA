package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.model.Book
import edu.eam.ingesoft.ejercicio_BD.model.Publisher
import edu.eam.ingesoft.ejercicio_BD.repositorios.BookRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class BookServiceTest {
    @Autowired
    lateinit var bookServices: BookServices
    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun createBookAlreadyExistTest() {
        val publisher = Publisher("0001", "Norma")
        entityManager.persist(Book("001", "001A", "abc", 10, publisher))
        try {
            bookServices.createBook("001", "001B", publisher.code)
        } catch (e: BusinessException) {
            Assertions.assertEquals("This book already exists", e.message)
        }
    }

    @Test
    fun createBookNameExistTest() {
        val publisher = Publisher("0001", "Norma")
        entityManager.persist(Book("001", "001A", "abc", 10, publisher))
        try {
            bookServices.createBook("002", "abc", publisher.code)
        } catch (e: BusinessException) {
            Assertions.assertEquals("There is an existing book with that name", e.message)
        }
    }

   @Test
   fun deliveryBookTest(){
       val publisher = Publisher("0001", "Norma")
       entityManager.persist(Book("001", "001A", "abc", 10, publisher))
       bookServices.deliverBook("001")
       val book= bookRepository.find("001")
       if (book != null) {
           Assertions.assertEquals(11,book.stock)
       }
   }




}
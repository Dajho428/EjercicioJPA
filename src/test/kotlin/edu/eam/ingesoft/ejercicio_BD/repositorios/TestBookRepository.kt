package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Publisher
import edu.eam.ingesoft.ejercicio_BD.models.entitys.Book
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@SpringBootTest
@Transactional
class TestBookRepository {
    @Autowired
    lateinit var bookRepository: BookRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        val publisher= Publisher("01","Norma")
        bookRepository.create(Book("0001","001A","ABCD",10,publisher))
        val book=entityManager.find(Book::class.java,"0001")
        println(book)
        Assertions.assertNotNull(book)
        Assertions.assertEquals("ABCD",book.name)
        Assertions.assertEquals("Norma",book.publisher?.name)

    }

    @Test
    fun testFind(){
        val publisher= Publisher("01","Norma")
        entityManager.persist(Book("0001","001A","ABCD",10,publisher))
        val book=bookRepository.find("0001")
        if (book!=null){
            Assertions.assertNotNull(book)
            Assertions.assertEquals("ABCD",book?.name)
            Assertions.assertEquals("Norma",book?.publisher?.name)
            println(book)
        }else{
            println("no se encuentra el libro con ese codigo")
        }
    }

    @Test
    fun testUpdate(){
        val publisher= Publisher("01","Norma")
        entityManager.persist(Book("0001","001A","ABCD",10,publisher))


        val book = entityManager.find(Book::class.java, "0001")
        println(book)
        book.name = "abcd"
        book.publisher?.name="Santillana"
        bookRepository.update(book)

        val bookToAssert = entityManager.find(Book::class.java, "0001")
        Assertions.assertEquals("abcd", bookToAssert.name)
        Assertions.assertEquals("Santillana",bookToAssert.publisher?.name)
        println(bookToAssert)
    }

    @Test
    fun testDelete(){
        val publisher= Publisher("01","Norma")
        entityManager.persist(Book("0001","001A","ABCD",10,publisher))

        bookRepository.delete("0001")

        val book=entityManager.find(Book::class.java,"0001")
        Assertions.assertNull(book)
        println(book)
    }

    @Test
    fun testFindBookByPublisher(){
        val publisher1= Publisher("01","Norma")
        entityManager.persist(publisher1)
        entityManager.persist(Book("001","001A","ABCD",10,publisher1))
        entityManager.persist(Book("002","002B","EFGH",10,publisher1))

        val list= bookRepository.findBookByPublisher("01")
        println(list[0].name)
        println(list[1].name)
        Assertions.assertEquals(2,list.size)
    }

}
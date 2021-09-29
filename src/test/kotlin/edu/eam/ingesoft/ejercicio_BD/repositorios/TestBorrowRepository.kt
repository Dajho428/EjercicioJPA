package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Publisher
import edu.eam.ingesoft.ejercicio_BD.model.Book
import edu.eam.ingesoft.ejercicio_BD.model.Borrow
import edu.eam.ingesoft.ejercicio_BD.model.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

import java.util.*
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class TestBorrowRepository {

    @Autowired
    lateinit var borrowRepository: BorrowRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        val date = Date(2021,6,15,)
        val publisher= Publisher("01","Norma")
        val book=Book("0001","001A","ABCD",publisher)
        val user=User("01","Hernandez","Jhonatan")
        borrowRepository.create(Borrow(11L,date,book,user))
        val borrow=entityManager.find(Borrow::class.java,11L)
        println(date)
        println(borrow)
        Assertions.assertNotNull(borrow)
        Assertions.assertEquals("ABCD",borrow.book.name)
        Assertions.assertEquals("Norma",borrow.book.publisher.name)
        Assertions.assertEquals("Jhonatan",borrow.user.name)
    }

    @Test
    fun testFind(){
        val date = Date(2021,6,15)
        val publisher= Publisher("01","Norma")
        val book=Book("0001","001A","ABCD",publisher)
        val user=User("01","Hernandez","Jhonatan")
        entityManager.persist(Borrow(11L,date,book,user))
        val borrow=borrowRepository.find(11L)
        if (borrow!=null){
            Assertions.assertNotNull(borrow)
            Assertions.assertEquals("ABCD",borrow?.book.name)
            Assertions.assertEquals("Norma",borrow?.book.publisher.name)
            Assertions.assertEquals("Jhonatan",borrow?.user.name)
            println(borrow)
        }else{
            println("no se encuentra el prestamo con ese codigo")
        }
    }

    @Test
    fun testUpdate(){
        val date = Date(2021,6,15,)
        val publisher= Publisher("01","Norma")
        val book=Book("0001","001A","ABCD",publisher)
        val user=User("01","Hernandez","Jhonatan")
        entityManager.persist(Borrow(11L,date,book,user))
        val borrow=entityManager.find(Borrow::class.java,11L)
        borrow.user.name="David"
        borrowRepository.update(borrow)
        val borrowToAssert=entityManager.find(Borrow::class.java,11L)
        Assertions.assertEquals("David",borrowToAssert.user.name)
    }

    @Test
    fun testDelete(){
        val date = Date(2021,6,15,)
        val publisher= Publisher("01","Norma")
        val book=Book("0001","001A","ABCD",publisher)
        val user=User("01","Hernandez","Jhonatan")
        entityManager.persist(Borrow(11L,date,book,user))
        borrowRepository.delete(11L)
        val borrow = entityManager.find(Borrow::class.java,11L)
        Assertions.assertNull(borrow)
    }

    @Test
    fun testFindBorrowByUser(){
        val date = Date(2021,6,15,)
        val publisher= Publisher("01","Norma")
        val book1=Book("0001","001A","ABCD",publisher)
        val book2=Book("0002","002B","EFGH",publisher)
        val user=User("01","Hernandez","Jhonatan")
        entityManager.persist(publisher)
        entityManager.persist(book1)
        entityManager.persist(book2)
        entityManager.persist(user)
        entityManager.persist(Borrow(1L,date,book1,user))
        entityManager.persist(Borrow(2L,date,book2,user))
        val list=borrowRepository.findBorrowByUser("01")
        Assertions.assertEquals(2,list.size)
        println(list[0].book.name)
        println(list[1].book.name)
    }

    @Test
    fun testFindBorrowByBook(){
        val date = Date(2021,6,15,)
        val publisher= Publisher("01","Norma")
        val book1=Book("01","001A","ABCD",publisher)
        val user=User("01","Hernandez","Jhonatan")
        val user2=User("02","Herrera","David")
        entityManager.persist(publisher)
        entityManager.persist(book1)
        entityManager.persist(user2)
        entityManager.persist(user)
        entityManager.persist(Borrow(1L,date,book1,user))
        entityManager.persist(Borrow(2L,date,book1,user2))
        val list=borrowRepository.findBorrowByBook("02")
        Assertions.assertEquals(2,list.size)
        println(list[0].user.name)
        println(list[1].user.name)
    }
}
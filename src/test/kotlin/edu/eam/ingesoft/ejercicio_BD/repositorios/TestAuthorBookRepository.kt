package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Author
import edu.eam.ingesoft.ejercicio_BD.model.Publisher
import edu.eam.ingesoft.ejercicio_BD.model.Book
import edu.eam.ingesoft.ejercicio_BD.model.AuthorBook
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@SpringBootTest
@Transactional
class TestAuthorBookRepository {
    @Autowired
    lateinit var authorBookRepository: AuthorBookRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        val publisher=Publisher("01","Norma")
        val book=Book("0001A","0001","ABCD",publisher)
        val author=Author(1L,"Hernandez","Jhonatan")
        authorBookRepository.create(AuthorBook(22L,author,book))
        val authorBook=entityManager.find(AuthorBook::class.java,22L)
        Assertions.assertNotNull(authorBook)
        Assertions.assertEquals("Jhonatan",authorBook.author.name)
        Assertions.assertEquals("ABCD",authorBook.book.name)
        Assertions.assertEquals("Norma",authorBook.book.publisher.name)

    }

    @Test
    fun testFind(){
        val publisher=Publisher("01","Norma")
        val book=Book("0001A","0001","ABCD",publisher)
        val author=Author(1L,"Hernandez","Jhonatan")
        entityManager.persist(AuthorBook(22L,author,book))

        val authorBook = authorBookRepository.find(22L)
        if (authorBook!=null){
            Assertions.assertNotNull(authorBook)
            Assertions.assertEquals("Jhonatan", authorBook?.author.name)
            Assertions.assertEquals("ABCD",authorBook?.book.name)
            Assertions.assertEquals("Norma",authorBook?.book.publisher.name)
            println(author)
        }else{
            println("no se encuentra el autor con ese codigo")
        }

    }

    @Test
    fun testUpdate(){
        val publisher=Publisher("01","Norma")
        entityManager.persist(publisher)
        val book=Book("0001A","0001","ABCD",publisher)
        val book2=Book("0002A","0002","qwer",publisher)
        entityManager.persist(book)
        entityManager.persist(book2)
        val author=Author(1L,"Hernandez","Jhonatan")
        entityManager.persist(author)
        entityManager.persist(AuthorBook(22L,author,book))
        entityManager.flush()

        val authorBook = entityManager.find(AuthorBook::class.java, 22L)
        authorBook.book=book2
        entityManager.clear()
        authorBookRepository.update(authorBook)

        val authorBookToAssert = entityManager.find(AuthorBook::class.java, 22L)
        Assertions.assertEquals("qwer", authorBookToAssert.book.name)
        println(authorBookToAssert)
    }

    @Test
    fun testDelete(){
        val publisher=Publisher("01","Norma")
        val book=Book("0001A","0001","ABCD",publisher)
        val author=Author(1L,"Hernandez","Jhonatan")
        entityManager.persist(AuthorBook(22L,author,book))
        println(authorBookRepository.find(22L))
        authorBookRepository.delete(22L)

        val authorBook=entityManager.find(AuthorBook::class.java,22L)
        Assertions.assertNull(authorBook)
        println(authorBook)
    }

    @Test
    fun testFindAuthorBook(){
        val publisher=Publisher("01","Norma")
        val book1=Book("0001A","0001","ABCD",publisher)
        val book2=Book("0002B","0002","EFGH",publisher)
        val author=Author(1L,"Hernandez","Jhonatan")
        entityManager.persist(publisher)
        entityManager.persist(book1)
        entityManager.persist(book2)
        entityManager.persist(author)
        entityManager.persist(AuthorBook(1L,author,book1))
        entityManager.persist(AuthorBook(2L,author,book2))
        val list=authorBookRepository.findAuthorBook("0001A")
        Assertions.assertEquals(1,list.size)
        println(list[0].name)


    }

    @Test
    fun testFindBookByAuthor(){
        val publisher=Publisher("01","Norma")
        val book1=Book("0001A","0001","ABCD",publisher)
        val book2=Book("0002B","0002","EFGH",publisher)
        val author=Author(1L,"Hernandez","Jhonatan")
        entityManager.persist(publisher)
        entityManager.persist(book1)
        entityManager.persist(book2)
        entityManager.persist(author)
        entityManager.persist(AuthorBook(1L,author,book1))
        entityManager.persist(AuthorBook(2L,author,book2))
        val list=authorBookRepository.findBookByAuthor(1L)
        Assertions.assertEquals(2,list.size)
        println(list[0].name)
        println(list[1].name)
    }
}
package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Author
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@SpringBootTest
@Transactional
class TestAuthorRepository {
    @Autowired
    lateinit var authorRepository: AuthorRepository
    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        authorRepository.create(Author(1L,"Hernandez","Jhonatan"))
        authorRepository.create(Author(2L,"Herrera","David"))

        val author= entityManager.find(Author::class.java, 2L)
        Assertions.assertNotNull(author)
        Assertions.assertEquals("Herrera",author.lastName)

    }

    @Test
    fun testFind(){
        entityManager.persist(Author(1L, "Hernandez","Jhonatan"))

        val author = authorRepository.find(1L)
        if (author!=null){
            Assertions.assertNotNull(author)
            Assertions.assertEquals("Jhonatan", author?.name)
            println(author)
        }else{
            println("no se encuentra el autor con ese codigo")
        }

    }

    @Test
    fun testUpdate(){
        entityManager.persist(Author(1L, "Hernandez","Jhonatan"))


        val author = entityManager.find(Author::class.java, 1L)
        author.name = "David"
        authorRepository.update(author)

        val authorToAssert = entityManager.find(Author::class.java, 1L)
        Assertions.assertEquals("David", authorToAssert.name)
    }

    @Test
    fun testDelete(){
        entityManager.persist(Author(1L, "Hernandez","Jhonatan"))
        authorRepository.delete(1L)

        val author=entityManager.find(Author::class.java,1L)
        Assertions.assertNull(author)

    }
}
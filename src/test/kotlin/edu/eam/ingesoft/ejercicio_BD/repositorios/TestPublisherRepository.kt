package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Publisher
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class TestPublisherRepository {
    @Autowired
    lateinit var publisherRepository: PublisherRepository
    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        publisherRepository.create(Publisher("01","Todo Libros"))
        publisherRepository.create(Publisher("02","Full Libros"))

        val publisher= entityManager.find(Publisher::class.java, 2L)
        Assertions.assertNotNull(publisher)
        Assertions.assertEquals("Full Libros",publisher.name)

    }

    @Test
    fun testFind(){
        entityManager.persist(Publisher("01", "Todo Libros"))

        val publisher = publisherRepository.find("01")
        if (publisher!=null){
            Assertions.assertNotNull(publisher)
            Assertions.assertEquals("Todo Libros", publisher?.name)
            println(publisher)
        }else{
            println("no se encuentra una editorial con ese codigo")
        }

    }

    @Test
    fun testUpdate(){
        entityManager.persist(Publisher("01", "Todo Libros"))


        val publisher = entityManager.find(Publisher::class.java, "01")
        publisher.name = "Todo Libros"
        publisherRepository.update(publisher)

        val publisherToAssert = entityManager.find(Publisher::class.java, "01")
        Assertions.assertEquals("Todo Libros", publisherToAssert.name)
    }

    @Test
    fun testDelete(){
        entityManager.persist(Publisher("01", "Todo Libros"))
        publisherRepository.delete("01")

        val publisher=entityManager.find(Publisher::class.java,"01")
        Assertions.assertNull(publisher)

    }
}
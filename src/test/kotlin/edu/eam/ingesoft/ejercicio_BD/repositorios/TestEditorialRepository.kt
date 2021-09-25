package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Autor
import edu.eam.ingesoft.ejercicio_BD.model.Editorial
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class TestEditorialRepository {
    @Autowired
    lateinit var editorialRepository: EditorialRepository
    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        editorialRepository.create(Editorial(1L,"Todo Libros"))
        editorialRepository.create(Editorial(2L,"Full Libros"))

        val editorial= entityManager.find(Editorial::class.java, 2L)
        Assertions.assertNotNull(editorial)
        Assertions.assertEquals("Full Libros",editorial.nombre_editorial)

    }

    @Test
    fun testFind(){
        entityManager.persist(Editorial(1L, "Todo Libros"))

        val editorial = editorialRepository.find(2L)
        if (editorial!=null){
            Assertions.assertNotNull(editorial)
            Assertions.assertEquals("Todo Libros", editorial?.nombre_editorial)
            println(editorial)
        }else{
            println("no se encuentra una editorial con ese codigo")
        }

    }

    @Test
    fun testUpdate(){
        entityManager.persist(Editorial(1L, "Todo Libros"))


        val ediorial = entityManager.find(Editorial::class.java, 1L)
        ediorial.nombre_editorial = "Todo Libros"
        editorialRepository.update(ediorial)

        val editorialToAssert = entityManager.find(Editorial::class.java, 1L)
        Assertions.assertEquals("Todo Libros", editorialToAssert.nombre_editorial)
    }

    @Test
    fun testDelete(){
        entityManager.persist(Editorial(1L, "Todo Libros"))
        editorialRepository.delete(1L)

        val editorial=entityManager.find(Editorial::class.java,1L)
        Assertions.assertNull(editorial)

    }
}
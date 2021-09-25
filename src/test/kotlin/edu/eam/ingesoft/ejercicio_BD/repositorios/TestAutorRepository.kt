package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Autor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@SpringBootTest
@Transactional
class TestAutorRepository {
    @Autowired
    lateinit var autorRepository: AutorRepository
    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        autorRepository.create(Autor(1L,"Hernandez","Jhonatan"))
        autorRepository.create(Autor(2L,"Herrera","David"))

        val autor= entityManager.find(Autor::class.java, 2L)
        Assertions.assertNotNull(autor)
        Assertions.assertEquals("Herrera",autor.apellido)

    }

    @Test
    fun testFind(){
        entityManager.persist(Autor(1L, "Hernandez","Jhonatan"))

        val autor = autorRepository.find(1L)
        if (autor!=null){
            Assertions.assertNotNull(autor)
            Assertions.assertEquals("Jhonatan", autor?.nombre)
            println(autor)
        }else{
            println("no se encuentra el autor con ese codigo")
        }

    }

    @Test
    fun testUpdate(){
        entityManager.persist(Autor(1L, "Hernandez","Jhonatan"))


        val autor = entityManager.find(Autor::class.java, 1L)
        autor.nombre = "David"
        autorRepository.update(autor)

        val autorToAssert = entityManager.find(Autor::class.java, 1L)
        Assertions.assertEquals("David", autorToAssert.nombre)
    }

    @Test
    fun testDelete(){
        entityManager.persist(Autor(1L, "Hernandez","Jhonatan"))
        autorRepository.delete(1L)

        val autor=entityManager.find(Autor::class.java,1L)
        Assertions.assertNull(autor)

    }
}
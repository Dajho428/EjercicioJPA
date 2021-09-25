package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Autor
import edu.eam.ingesoft.ejercicio_BD.model.Editorial
import edu.eam.ingesoft.ejercicio_BD.model.Libro
import edu.eam.ingesoft.ejercicio_BD.model.Libro_Autor
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@SpringBootTest
@Transactional
class TestLibroRepository {
    @Autowired
    lateinit var libroRepository: LibroRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        val editorial= Editorial(1L,"Norma")
        libroRepository.create(Libro("0001","001A","ABCD",editorial))
        val libro=entityManager.find(Libro::class.java,"0001")
        println(libro)
        Assertions.assertNotNull(libro)
        Assertions.assertEquals("ABCD",libro.nombre_libro)
        Assertions.assertEquals("Norma",libro.id_editorial.nombre_editorial)

    }

    @Test
    fun testFind(){
        val editorial= Editorial(1L,"Norma")
        entityManager.persist(Libro("0001","001A","ABCD",editorial))
        val libro=libroRepository.find("0001")
        if (libro!=null){
            Assertions.assertNotNull(libro)
            Assertions.assertEquals("ABCD",libro?.nombre_libro)
            Assertions.assertEquals("Norma",libro?.id_editorial.nombre_editorial)
            println(libro)
        }else{
            println("no se encuentra el libro con ese codigo")
        }
    }

    @Test
    fun testUpdate(){
        val editorial= Editorial(1L,"Norma")
        entityManager.persist(Libro("0001","001A","ABCD",editorial))


        val libro = entityManager.find(Libro::class.java, "0001")
        println(libro)
        libro.nombre_libro = "abcd"
        libro.id_editorial.nombre_editorial="Santillana"
        libroRepository.update(libro)

        val libroToAssert = entityManager.find(Libro::class.java, "0001")
        Assertions.assertEquals("abcd", libroToAssert.nombre_libro)
        Assertions.assertEquals("Santillana",libroToAssert.id_editorial.nombre_editorial)
        println(libroToAssert)
    }

    @Test
    fun testDelete(){
        val editorial=Editorial(1L,"Norma")
        entityManager.persist(Libro("0001","001A","ABCD",editorial))

        libroRepository.delete("0001")

        val libro=entityManager.find(Libro::class.java,"0001")
        Assertions.assertNull(libro)
        println(libro)


    }
}
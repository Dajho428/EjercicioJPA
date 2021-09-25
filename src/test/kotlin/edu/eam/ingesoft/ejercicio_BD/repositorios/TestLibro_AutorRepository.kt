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
class TestLibro_AutorRepository {
    @Autowired
    lateinit var autorLibroRepository: Libro_AutorRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        val editorial=Editorial(1L,"Norma")
        val libro=Libro("0001A","0001","ABCD",editorial)
        val autor=Autor(1L,"Hernandez","Jhonatan")
        autorLibroRepository.create(Libro_Autor(22L,autor,libro))
        val autorLibro=entityManager.find(Libro_Autor::class.java,22L)
        Assertions.assertNotNull(autorLibro)
        Assertions.assertEquals("Jhonatan",autorLibro.id_autor.nombre)
        Assertions.assertEquals("ABCD",autorLibro.id_libro.nombre_libro)
        Assertions.assertEquals("Norma",autorLibro.id_libro.id_editorial.nombre_editorial)

    }

    @Test
    fun testFind(){
        val editorial=Editorial(1L,"Norma")
        val libro=Libro("0001A","0001","ABCD",editorial)
        val autor=Autor(1L,"Hernandez","Jhonatan")
        entityManager.persist(Libro_Autor(22L,autor,libro))

        val autorLibro = autorLibroRepository.find(22L)
        if (autorLibro!=null){
            Assertions.assertNotNull(autorLibro)
            Assertions.assertEquals("Jhonatan", autorLibro?.id_autor.nombre)
            Assertions.assertEquals("ABCD",autorLibro?.id_libro.nombre_libro)
            Assertions.assertEquals("Norma",autorLibro?.id_libro.id_editorial.nombre_editorial)
            println(autor)
        }else{
            println("no se encuentra el autor con ese codigo")
        }

    }

    @Test
    fun testUpdate(){
        val editorial=Editorial(1L,"Norma")
        val libro=Libro("0001A","0001","ABCD",editorial)
        val autor=Autor(1L,"Hernandez","Jhonatan")
        entityManager.persist(Libro_Autor(22L,autor,libro))


        val autorLibro = entityManager.find(Libro_Autor::class.java, 22L)
        autorLibro.id_autor.nombre = "David"
        autorLibroRepository.update(autorLibro)

        val autorLibroToAssert = entityManager.find(Libro_Autor::class.java, 22L)
        Assertions.assertEquals("David", autorLibroToAssert.id_autor.nombre)
        println(autorLibroToAssert)
    }

    @Test
    fun testDelete(){
        val editorial=Editorial(1L,"Norma")
        val libro=Libro("0001A","0001","ABCD",editorial)
        val autor=Autor(1L,"Hernandez","Jhonatan")
        entityManager.persist(Libro_Autor(22L,autor,libro))
        println(autorLibroRepository.find(22L))
        autorLibroRepository.delete(22L)

        val autorLibro=entityManager.find(Libro_Autor::class.java,22L)
        Assertions.assertNull(autorLibro)
        println(autorLibro)


    }
}
package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Editorial
import edu.eam.ingesoft.ejercicio_BD.model.Libro
import edu.eam.ingesoft.ejercicio_BD.model.Prestamo
import edu.eam.ingesoft.ejercicio_BD.model.Usuario
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

import java.util.*
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class TestPrestamoRepository {

    @Autowired
    lateinit var prestamoRepository: PrestamoRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        val date = Date(2021,)
        val editorial= Editorial(1L,"Norma")
        val libro=Libro("0001","001A","ABCD",editorial)
        val usuario=Usuario("01","Hernandez","Jhonatan")
        prestamoRepository.create(Prestamo(11L,date,libro,usuario))
        val prestamo=entityManager.find(Prestamo::class.java,11L)
        println(date)
        println(prestamo)
        Assertions.assertNotNull(prestamo)
        Assertions.assertEquals("ABCD",prestamo.id_libro.nombre_libro)
        Assertions.assertEquals("Norma",prestamo.id_libro.id_editorial.nombre_editorial)
        Assertions.assertEquals("Jhonatan",prestamo.id_usuario.nombre_usuario)
    }

    @Test
    fun testFind(){
        val date = Date(2021,)
        val editorial= Editorial(1L,"Norma")
        val libro=Libro("0001","001A","ABCD",editorial)
        val usuario=Usuario("01","Hernandez","Jhonatan")
        entityManager.persist(Prestamo(11L,date,libro,usuario))
        val prestamo=prestamoRepository.find(11L)
        if (prestamo!=null){
            Assertions.assertNotNull(prestamo)
            Assertions.assertEquals("ABCD",prestamo?.id_libro.nombre_libro)
            Assertions.assertEquals("Norma",prestamo?.id_libro.id_editorial.nombre_editorial)
            Assertions.assertEquals("Jhonatan",prestamo?.id_usuario.nombre_usuario)
            println(prestamo)
        }else{
            println("no se encuentra el prestamo con ese codigo")
        }
    }

    @Test
    fun testUpdate(){
        val date = Date(2021,)
        val editorial= Editorial(1L,"Norma")
        val libro=Libro("0001","001A","ABCD",editorial)
        val usuario=Usuario("01","Hernandez","Jhonatan")
        entityManager.persist(Prestamo(11L,date,libro,usuario))
        val prestamo=entityManager.find(Prestamo::class.java,11L)
        prestamo.id_usuario.nombre_usuario="David"
        prestamoRepository.update(prestamo)
        val prestamoToAssert=entityManager.find(Prestamo::class.java,11L)
        Assertions.assertEquals("David",prestamoToAssert.id_usuario.nombre_usuario)
    }

    @Test
    fun testDelete(){
        val date = Date(2021,)
        val editorial= Editorial(1L,"Norma")
        val libro=Libro("0001","001A","ABCD",editorial)
        val usuario=Usuario("01","Hernandez","Jhonatan")
        entityManager.persist(Prestamo(11L,date,libro,usuario))
        prestamoRepository.delete(11L)
        val prestamo = entityManager.find(Prestamo::class.java,11L)
        Assertions.assertNull(prestamo)
    }
}
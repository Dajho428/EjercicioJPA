package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Autor
import edu.eam.ingesoft.ejercicio_BD.model.Usuario
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class TestUsuarioRepository {

    @Autowired
    lateinit var usuarioRepository: UsuarioRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        usuarioRepository.create(Usuario("001","Hernandez","Jhonatan"))
        val usuario=entityManager.find(Usuario::class.java,"001")
        Assertions.assertNotNull(usuario)
        Assertions.assertEquals("Hernandez",usuario.apellido_usuario)
    }
    @Test
    fun testFind(){
        entityManager.persist(Usuario("001","Hernandez","Jhonatan"))
        val usuario=usuarioRepository.find("001")
        if(usuario!=null){
            Assertions.assertNotNull(usuario)
            Assertions.assertEquals("Jhonatan",usuario?.nombre_usuario)
        }else{
            println("no se encuentra el usuario con ese codigo")
        }
    }

    @Test
    fun testUpdate(){
        entityManager.persist(Usuario("001","Hernandez","Jhonatan"))


        val usuario = entityManager.find(Usuario::class.java, "001")
        usuario.nombre_usuario = "David"
        usuarioRepository.update(usuario)

        val usuarioToAssert = entityManager.find(Usuario::class.java, "001")
        Assertions.assertEquals("David", usuarioToAssert.nombre_usuario)
    }

    @Test
    fun testDelete(){
        entityManager.persist(Usuario("001","Hernandez","Jhonatan"))
        usuarioRepository.delete("001")

        val usuario=entityManager.find(Usuario::class.java,"001")
        Assertions.assertNull(usuario)

    }

}
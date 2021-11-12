package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.models.entitys.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
class TestUserRepository {

    @Autowired
    lateinit var userRepository: UserRepository


    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun contextLoads(){

    }

    @Test
    fun testCreate(){
        userRepository.create(User("001","Hernandez","Jhonatan"))
        val user=entityManager.find(User::class.java,"001")
        Assertions.assertNotNull(user)
        Assertions.assertEquals("Hernandez",user.lastName)
    }
    @Test
    fun testFind(){
        entityManager.persist(User("001","Hernandez","Jhonatan"))
        val user=userRepository.find("001")
        if(user!=null){
            Assertions.assertNotNull(user)
            Assertions.assertEquals("Jhonatan",user?.name)
        }else{
            println("no se encuentra el usuario con ese codigo")
        }
    }

    @Test
    fun testUpdate(){
        entityManager.persist(User("001","Hernandez","Jhonatan"))


        val user = entityManager.find(User::class.java, "001")
        user.name = "David"
        userRepository.update(user)

        val userToAssert = entityManager.find(User::class.java, "001")
        Assertions.assertEquals("David", userToAssert.name)
    }

    @Test
    fun testDelete(){
        entityManager.persist(User("001","Hernandez","Jhonatan"))
        userRepository.delete("001")

        val user=entityManager.find(User::class.java,"001")
        Assertions.assertNull(user)

    }

}
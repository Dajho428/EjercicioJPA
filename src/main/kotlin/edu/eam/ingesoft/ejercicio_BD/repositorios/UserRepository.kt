package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional
class UserRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(user: User){
        em.persist(user)
    }

    fun find(user_identification: String): User?{
        return em.find(User::class.java,user_identification)
    }

    fun update(user: User) {
        em.merge(user)
    }

    fun delete(user_identification: String) {

        val user = find(user_identification)

        if (user!=null) {
            em.remove(user)
        }
    }

}
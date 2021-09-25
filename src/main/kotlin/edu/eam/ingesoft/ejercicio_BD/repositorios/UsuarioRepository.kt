package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Editorial
import edu.eam.ingesoft.ejercicio_BD.model.Usuario
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional
class UsuarioRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(usuario: Usuario){
        em.persist(usuario)
    }

    fun find(user_identification: String): Usuario?{
        return em.find(Usuario::class.java,user_identification)
    }

    fun update(usuario: Usuario) {
        em.merge(usuario)
    }

    fun delete(user_identification: String) {

        val usuario = find(user_identification)

        if (usuario!=null) {
            em.remove(usuario)
        }
    }

}
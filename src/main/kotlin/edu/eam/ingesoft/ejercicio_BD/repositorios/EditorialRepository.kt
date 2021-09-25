package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Editorial
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@Component
@Transactional
class EditorialRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(editorial: Editorial){
        em.persist(editorial)
    }

    fun find(codigo_editorial: Long):Editorial?{
        return em.find(Editorial::class.java,codigo_editorial)
    }

    fun update(editorial: Editorial) {
        em.merge(editorial)
    }

    fun delete(codigo_editorial: Long) {

        val editorial = find(codigo_editorial)

        if (editorial!=null) {
            em.remove(editorial)
        }
    }


}
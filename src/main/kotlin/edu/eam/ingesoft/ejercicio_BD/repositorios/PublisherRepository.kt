package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Publisher
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@Component
@Transactional
class PublisherRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(publisher: Publisher){
        em.persist(publisher)
    }

    fun find(code_publisher: String):Publisher?{
        return em.find(Publisher::class.java,code_publisher)
    }

    fun update(publisher: Publisher) {
        em.merge(publisher)
    }

    fun delete(code_publisher: String) {

        val publisher = find(code_publisher)

        if (publisher!=null) {
            em.remove(publisher)
        }
    }


}
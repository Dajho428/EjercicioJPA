package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Autor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional
class AutorRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(autor: Autor){
        em.persist(autor)
    }

    fun find(codigo_autor: Long):Autor?{
        return em.find(Autor::class.java,codigo_autor)
    }

    fun update(autor: Autor) {
        em.merge(autor)
    }

    fun delete(codigo_autor: Long) {

        val autor = find(codigo_autor)

        if (autor!=null) {
            em.remove(autor)
        }
    }
}
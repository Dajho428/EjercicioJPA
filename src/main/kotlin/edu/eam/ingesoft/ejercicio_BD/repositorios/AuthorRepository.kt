package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Author
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional
class AuthorRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(author: Author){
        em.persist(author)
    }

    fun find(id_author: Long): Author?{
        return em.find(Author::class.java,id_author)
    }

    fun update(author: Author) {
        em.merge(author)
    }

    fun delete(id_author: Long) {

        val author = find(id_author)

        if (author!=null) {
            em.remove(author)
        }
    }
}
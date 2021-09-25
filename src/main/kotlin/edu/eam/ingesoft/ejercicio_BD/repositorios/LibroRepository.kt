package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Libro

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@Component
@Transactional
class LibroRepository {

    @Autowired

    lateinit var em: EntityManager


    fun create(libro: Libro){
        em.persist(libro)
    }

    fun find(codigo_libro:String): Libro?{
        return em.find(Libro::class.java,codigo_libro)
    }

    fun update(libro: Libro) {
        em.merge(libro)
    }

    fun delete(codigo_libro: String) {

        val libro = find(codigo_libro)

        if (libro != null) {
            em.remove(libro)
        }

    }

    fun findLibrosEditorial (codigo_editorial: Long):List<Libro>{
        val query = em.createQuery("SELECT libro FROM Libro libro WHERE libro.id_editorial.codigo_editorial = :codigo_editorial")
        query.setParameter("codigo_editorial",codigo_editorial)
        return query.resultList as List<Libro>
    }




}
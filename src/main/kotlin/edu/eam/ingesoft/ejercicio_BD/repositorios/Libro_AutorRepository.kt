package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Autor
import edu.eam.ingesoft.ejercicio_BD.model.Libro
import edu.eam.ingesoft.ejercicio_BD.model.Libro_Autor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional

class Libro_AutorRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(libroAutor: Libro_Autor){
        em.persist(libroAutor)
    }

    fun find(id:Long): Libro_Autor?{
        return em.find(Libro_Autor::class.java,id)
    }

    fun update(libroAutor: Libro_Autor) {
        em.merge(libroAutor)
    }

    fun delete(id: Long) {

        val libro_Autor = find(id)

        if (libro_Autor != null) {
            em.remove(libro_Autor)
        }
    }

    fun libroAutor(id_autor: Long) : List<Libro>{
        val query = em.createQuery("SELECT autorLibro.libro FROM  Libro_Autor autorLibro WHERE autorLibro.id_autor.codigo_autor = :id_autor ")
        query.setParameter("id_autor",id_autor)
        return query.resultList as List<Libro>
    }

    fun autorLibro(id_libro:String) : List<Autor>{
        val query = em.createQuery("SELECT autorLibro.autor FROM Libro_Autor autorLibro WHERE autorLibro.id_libro.codigo_libro = :id_libro")
        query.setParameter("id_libro",id_libro)
        return query.resultList as List<Autor>
    }
}
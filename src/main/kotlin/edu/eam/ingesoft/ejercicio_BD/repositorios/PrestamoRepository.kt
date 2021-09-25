package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Libro_Autor
import edu.eam.ingesoft.ejercicio_BD.model.Prestamo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional
class PrestamoRepository {

    @Autowired

    lateinit var em: EntityManager

    fun create(prestamo: Prestamo){
        em.persist(prestamo)
    }

    fun find(id:Long): Prestamo?{
        return em.find(Prestamo::class.java,id)
    }

    fun update(prestamo: Prestamo) {
        em.merge(prestamo)
    }

    fun delete(id: Long) {

        val prestamo = find(id)

        if (prestamo != null) {
            em.remove(prestamo)
        }
    }

    fun prestamoUsuario(id_usuario:String) : List<Prestamo>{
         val query =em.createQuery("SELECT prestamo FROM Prestamo prestamo WHERE prestamo.id_usuario.user_identification = : id_usuario")
        query.setParameter("id_usuario",id_usuario)
        return query.resultList as List<Prestamo>
    }

    fun prestamoLibro(codigo_libro : String) : List<Prestamo>{
        val query = em.createQuery("SELECT prestamo FROM PRESTAMO prestamo WHERE prestamo.id_libro.codigo_libro = : codigo_libro")
        query.setParameter("codigo_libro",codigo_libro)
        return query.resultList as List<Prestamo>
    }
}
package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.model.Borrow
import edu.eam.ingesoft.ejercicio_BD.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional
class BorrowRepository {

    @Autowired

    lateinit var em: EntityManager

    fun create(borrow: Borrow) {
        em.persist(borrow)
    }

    fun find(id_borrow: Long): Borrow? {
        return em.find(Borrow::class.java, id_borrow)
    }

    fun update(borrow: Borrow) {
        em.merge(borrow)
    }

    fun delete(id_borrow: Long) {

        val borrow = find(id_borrow)

        if (borrow != null) {
            em.remove(borrow)
        }
    }

    fun findBorrowByUser(id_user: String): List<Borrow> {
        val query = em.createQuery("SELECT borrow FROM Borrow borrow WHERE borrow.user.identification = : id_user")
        query.setParameter("id_user", id_user)
        return query.resultList as List<Borrow>
    }

    fun findBorrowByBook(code_book: String): List<Borrow> {
        val query = em.createQuery("SELECT borrow FROM Borrow borrow WHERE borrow.book.code = : code_book")
        query.setParameter("code_book", code_book)
        return query.resultList as List<Borrow>
    }

    fun findUserByBook(code_book: String): List<User> {
        val query = em.createQuery("SELECT borrow.user FROM Borrow borrow WHERE borrow.book.code = : code_book ")
        query.setParameter("code_book", code_book)
        return query.resultList as List<User>
    }


}
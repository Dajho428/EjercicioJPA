package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager


@Component
@Transactional
class BookRepository {

    @Autowired

    lateinit var em: EntityManager


    fun create(book: Book) {
        em.persist(book)
    }

    fun find(code_book: String?): Book? {
        return em.find(Book::class.java, code_book)
    }

    fun update(book: Book) {
        em.merge(book)
    }

    fun delete(code_book: String) {

        val book = find(code_book)

        if (book != null) {
            em.remove(book)
        }

    }

    fun findBookByPublisher(code_publisher: String): List<Book> {
        val query = em.createQuery("SELECT book FROM Book book WHERE book.publisher.code = : code_publisher")
        query.setParameter("code_publisher", code_publisher)
        return query.resultList as List<Book>
    }

    fun findBookByName(nombre_libro: String?): List<Book> {
        val query = em.createQuery("SELECT book FROM Book book WHERE book.name = : nombre_libro")
        query.setParameter("nombre_libro", nombre_libro)
        return query.resultList as List<Book>
    }


}
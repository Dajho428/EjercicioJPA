package edu.eam.ingesoft.ejercicio_BD.repositorios

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Author
import edu.eam.ingesoft.ejercicio_BD.models.entitys.Book
import edu.eam.ingesoft.ejercicio_BD.models.entitys.AuthorBook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager

@Component
@Transactional

class AuthorBookRepository {
    @Autowired

    lateinit var em: EntityManager

    fun create(authorBook: AuthorBook) {
        em.persist(authorBook)
    }

    fun find(id: Long): AuthorBook? {
        return em.find(AuthorBook::class.java, id)
    }

    fun update(authorBook: AuthorBook) {
        em.merge(authorBook)
    }

    fun delete(id: Long) {

        val authorBook = find(id)

        if (authorBook != null) {
            em.remove(authorBook)
        }
    }

    fun findBookByAuthor(id_author: Long): List<Book> {
        val query =
            em.createQuery("SELECT authorBook.book FROM  AuthorBook authorBook WHERE authorBook.author.id = :id_author ")
        query.setParameter("id_author", id_author)
        return query.resultList as List<Book>
    }

    fun findAuthorBook(code_book: String?): List<Author> {
        val query =
            em.createQuery("SELECT authorBook.author FROM AuthorBook authorBook WHERE authorBook.book.code = : code_book")
        query.setParameter("code_book", code_book)
        return query.resultList as List<Author>
    }

    fun listAuthors(): List<Author> {
        val query = em.createQuery("SELECT authorBook.author FROM AuthorBook authorBook")
        return query.resultList as List<Author>
    }


}
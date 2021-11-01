package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.models.entitys.AuthorBook
import edu.eam.ingesoft.ejercicio_BD.repositorios.AuthorBookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class AuthorBookServices {
    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var authorBookRepository: AuthorBookRepository

    fun createAuthorBook(authorBook: AuthorBook) {
        val listAuthors = authorBookRepository.findAuthorBook(authorBook.book.code)
        listAuthors.forEach {
            if (it.id == authorBook.author.id) {
                throw BusinessException("The book can't have the same Author twice")
            }
        }
        authorBookRepository.create(authorBook)
    }
}
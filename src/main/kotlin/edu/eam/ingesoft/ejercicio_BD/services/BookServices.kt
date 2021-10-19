package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.model.Author
import edu.eam.ingesoft.ejercicio_BD.model.Book
import edu.eam.ingesoft.ejercicio_BD.model.Publisher
import edu.eam.ingesoft.ejercicio_BD.repositorios.AuthorBookRepository
import edu.eam.ingesoft.ejercicio_BD.repositorios.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class BookServices {

    @Autowired
    lateinit var entityManager: EntityManager

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var authorBookRepository: AuthorBookRepository

    fun createBook(code_book: String, name_book: String, id_publisher: String) {
        val book = bookRepository.find(code_book)
        if (book != null) {
            throw BusinessException("This book already exists")
        }
        val bookByName = bookRepository.findBookByName(name_book)
        if (bookByName != null) {
            throw BusinessException("There is an existing book with that name")
        }
        var publisher = entityManager.find(Publisher::class.java, id_publisher)
        var bookAux = Book(code_book, "XD", name_book, 1, publisher)
        bookRepository.create(bookAux)

    }

    fun deliverBook(id_book: String) {
        val book = bookRepository.find(id_book) ?: throw BusinessException("There is not Book with that Id")
        book.stock = book.stock + 1
        bookRepository.update(book)
    }
}
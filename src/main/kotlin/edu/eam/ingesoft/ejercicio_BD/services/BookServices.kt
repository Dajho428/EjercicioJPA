package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.models.entitys.Book
import edu.eam.ingesoft.ejercicio_BD.models.entitys.Publisher
import edu.eam.ingesoft.ejercicio_BD.models.request.BorrowRequestDelivery
import edu.eam.ingesoft.ejercicio_BD.repositorios.AuthorBookRepository
import edu.eam.ingesoft.ejercicio_BD.repositorios.BookRepository
import edu.eam.ingesoft.ejercicio_BD.repositorios.PublisherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class BookServices {
    @Autowired
    lateinit var publisherRepository: PublisherRepository

    @Autowired
    lateinit var bookRepository: BookRepository

    fun createBook(book: Book, id_publisher: String) {
        val bookById = bookRepository.find(book.code)
        if (bookById != null) {
            throw BusinessException("This book already exists")
        }
        val bookByName = bookRepository.findBookByName(book?.name)
        if (bookByName.isNotEmpty()) {
            throw BusinessException("There is an existing book with that name")
        }
        var publisher: Publisher? =
            publisherRepository.find(id_publisher) ?: throw BusinessException("This Publisher doesn't existing")

        book.publisher = publisher
        bookRepository.create(book)
    }

    fun deliverBook(borrowRequestDelivery: BorrowRequestDelivery) {
        val book = bookRepository.find(borrowRequestDelivery.idBook)
            ?: throw BusinessException("There is not Book with that Id")
        book.stock = book.stock + 1
        bookRepository.update(book)
    }

    fun updateBook(book: Book) {
        var bookAux = bookRepository.find(book.code) ?: throw BusinessException("This Book doesn't exist")
        book.publisher = bookAux.publisher
        bookRepository.update(book)
    }
}
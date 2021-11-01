package edu.eam.ingesoft.ejercicio_BD.controllers

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Book
import edu.eam.ingesoft.ejercicio_BD.services.BookServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/books")
class BookController {

    @Autowired
    lateinit var bookServices: BookServices

    @PostMapping("/{id}")
    fun createBook(@PathVariable("id") idPublisher: String, @RequestBody book: Book) {
        bookServices.createBook(book, idPublisher)
    }

    @PutMapping("/{id}")
    fun updateBook(@PathVariable ("id") idBook:String,@RequestBody book: Book) {
        book.code=idBook
        bookServices.updateBook(book)
    }
}
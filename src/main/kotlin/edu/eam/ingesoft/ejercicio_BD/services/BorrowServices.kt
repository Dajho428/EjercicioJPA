package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.model.Borrow
import edu.eam.ingesoft.ejercicio_BD.model.User
import edu.eam.ingesoft.ejercicio_BD.repositorios.BookRepository
import edu.eam.ingesoft.ejercicio_BD.repositorios.BorrowRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager

@Service
class BorrowServices {
    @Autowired
    lateinit var borrowRepository: BorrowRepository

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var entityManager: EntityManager

    fun createBorrow(borrow: Borrow) {
        val listBorrowsByUser = borrowRepository.findBorrowByUser(borrow.user.identification)
        listBorrowsByUser.forEach {
            if (it.book.code == borrow.book.code) {
                throw BusinessException("The user can't lend the same book twice")
            }
            if (listBorrowsByUser.size > 5) {
                throw BusinessException("The user can't lend more books ")
            }
        }
        if (borrow.book.stock <= 1) {
            throw BusinessException("The book can't be loand because there is one unity in stock")
        }
        borrowRepository.create(borrow)
        borrow.book.stock = borrow.book.stock - 1
        bookRepository.update(borrow.book)
    }

    fun findBorrowByUser(id_user: String): List<Borrow> {
        val user = entityManager.find(User::class.java, id_user) ?: throw BusinessException("The User doesn't exist")
        return borrowRepository.findBorrowByUser(id_user)
    }

    fun findUserByBook(id_book: String): List<User> {
        val book = bookRepository.find(id_book)
        if(book==null){
            throw BusinessException("The book doesn't exist")
        }
        return borrowRepository.findUserByBook(id_book)


    }


}
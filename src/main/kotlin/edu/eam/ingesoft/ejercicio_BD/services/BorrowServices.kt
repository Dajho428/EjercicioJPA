package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.models.entitys.Borrow
import edu.eam.ingesoft.ejercicio_BD.models.entitys.User
import edu.eam.ingesoft.ejercicio_BD.models.request.BorrowRequestCreate
import edu.eam.ingesoft.ejercicio_BD.repositorios.BookRepository
import edu.eam.ingesoft.ejercicio_BD.repositorios.BorrowRepository
import edu.eam.ingesoft.ejercicio_BD.repositorios.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import javax.persistence.EntityManager

@Service
class BorrowServices {
    @Autowired
    lateinit var borrowRepository: BorrowRepository

    @Autowired
    lateinit var bookRepository: BookRepository

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var entityManager: EntityManager

    fun createBorrow(borrowRequest: BorrowRequestCreate) {
        val listBorrowsByUser = borrowRepository.findBorrowByUser(borrowRequest.idUser)
        listBorrowsByUser.forEach {
            if (it.book.code == borrowRequest.idBook) {
                throw BusinessException("The user can't lend the same book twice")
            }
            if (listBorrowsByUser.size > 5) {
                throw BusinessException("The user can't lend more books ")
            }
        }
        val book = bookRepository.find(borrowRequest.idBook) ?: throw BusinessException("This book doesn't exist")
        if (book.stock <= 1) {
            throw BusinessException("The book can't be load because there is one unity in stock")
        }
        val user=userRepository.find(borrowRequest.idUser)
        val date = Calendar.getInstance().time
        val borrow=Borrow(borrowRequest.idBorrow,date,book,user)
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
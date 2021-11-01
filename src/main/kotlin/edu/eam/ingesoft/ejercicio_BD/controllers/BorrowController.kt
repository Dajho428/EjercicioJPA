package edu.eam.ingesoft.ejercicio_BD.controllers

import edu.eam.ingesoft.ejercicio_BD.models.request.BorrowRequestCreate
import edu.eam.ingesoft.ejercicio_BD.services.BorrowServices
import edu.eam.ingesoft.ejercicio_BD.models.entitys.User
import edu.eam.ingesoft.ejercicio_BD.models.request.BorrowRequestDelivery
import edu.eam.ingesoft.ejercicio_BD.services.BookServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/borrows")
class BorrowController {
    @Autowired
    lateinit var borrowServices: BorrowServices

    @Autowired
    lateinit var bookServices: BookServices

    @PostMapping("/create")
    fun createBorrow(@RequestBody borrowRequestCreate: BorrowRequestCreate){
        borrowServices.createBorrow(borrowRequestCreate)
    }

    @GetMapping("{id}/users")
    fun findUsersByBook(@PathVariable ("id") idBook : String):List<User>{
        return borrowServices.findUserByBook(idBook)
    }

    @DeleteMapping("/delete")
    fun deleteBorrow(@RequestBody borrowRequestDelivery: BorrowRequestDelivery){
        bookServices.deliverBook(borrowRequestDelivery)
        borrowServices.borrowRepository.delete(borrowRequestDelivery.idBorrow)
        println("Eliminado")
    }





}
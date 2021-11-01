package edu.eam.ingesoft.ejercicio_BD.controllers

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Borrow
import edu.eam.ingesoft.ejercicio_BD.models.entitys.User
import edu.eam.ingesoft.ejercicio_BD.repositorios.UserRepository
import edu.eam.ingesoft.ejercicio_BD.services.BorrowServices
import edu.eam.ingesoft.ejercicio_BD.services.UserServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var userServices: UserServices
    @Autowired
    lateinit var borrowServices: BorrowServices

    @PostMapping
    fun createUser(@RequestBody user: User){
        userServices.createUser(user)
    }

    @GetMapping("/{id}/borrows")
    fun findBorrowsByUser(@PathVariable ("id") idUser: String): List<Borrow>{
        return borrowServices.findBorrowByUser(idUser)
    }
}
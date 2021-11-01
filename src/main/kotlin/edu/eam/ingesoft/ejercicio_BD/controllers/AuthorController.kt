package edu.eam.ingesoft.ejercicio_BD.controllers

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Author
import edu.eam.ingesoft.ejercicio_BD.services.AuthorServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/authors")

class AuthorController {

    @Autowired
    lateinit var authorServices: AuthorServices

    @PostMapping
    fun createAuthor(@RequestBody author: Author){
        authorServices.createAuthor(author)
    }

}
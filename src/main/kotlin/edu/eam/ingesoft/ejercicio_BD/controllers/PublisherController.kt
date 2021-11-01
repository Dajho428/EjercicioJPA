package edu.eam.ingesoft.ejercicio_BD.controllers

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Publisher
import edu.eam.ingesoft.ejercicio_BD.services.PublisherServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/publishers")
class PublisherController {

    @Autowired
    lateinit var publisherServices: PublisherServices

    @PostMapping
    fun createPublisher(@RequestBody publisher: Publisher){
        publisherServices.createPublisher(publisher)
    }
}
package edu.eam.ingesoft.ejercicio_BD.controllers

import edu.eam.ingesoft.ejercicio_BD.models.entitys.Publisher
import edu.eam.ingesoft.ejercicio_BD.services.PublisherServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/publishers")
class PublisherController {

    @Autowired
    lateinit var publisherServices: PublisherServices

    @PostMapping
    fun createPublisher(@RequestBody publisher: Publisher){
        publisherServices.createPublisher(publisher)
    }

    @GetMapping("/{idPublisher}")
    fun getPublisher(@PathVariable("idPublisher")idPublisher: String):Publisher{
        return publisherServices.findPublisher(idPublisher)
    }
}
package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.model.Publisher
import edu.eam.ingesoft.ejercicio_BD.repositorios.PublisherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PublisherServices {
    @Autowired
    lateinit var publisherRepository: PublisherRepository

    fun createPublisher(publisher: Publisher) {
        val publisherAux = publisherRepository.find(publisher.code)
        if (publisherAux != null) {
            throw BusinessException("There is a existing Publisher with this id")
        }
        publisherRepository.create(publisher)
    }
}
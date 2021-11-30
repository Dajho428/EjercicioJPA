package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.models.entitys.Publisher
import edu.eam.ingesoft.ejercicio_BD.repositorios.PublisherRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.load.java.BuiltinMethodsWithSpecialGenericSignature

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

    fun findPublisher (idPublisher :String):Publisher{
        val publisher = publisherRepository.find(idPublisher)
        if (publisher == null){
            throw BusinessException ("Don't exist a publisher whit that code")
        }
        return publisher
    }
}
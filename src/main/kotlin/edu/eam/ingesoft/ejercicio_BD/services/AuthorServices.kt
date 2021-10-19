package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.model.Author
import edu.eam.ingesoft.ejercicio_BD.repositorios.AuthorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthorServices {
    @Autowired
    lateinit var authorRepository: AuthorRepository

    fun createAuthor(author: Author) {
        val authorAux = authorRepository.find(author.id)
        if (authorAux != null) {
            throw BusinessException("There is an existing Author with this id")
        }
        authorRepository.create(author)
    }
}
package edu.eam.ingesoft.ejercicio_BD.services

import edu.eam.ingesoft.ejercicio_BD.exceptions.BusinessException
import edu.eam.ingesoft.ejercicio_BD.model.User
import edu.eam.ingesoft.ejercicio_BD.repositorios.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserServices {
    @Autowired
    lateinit var userRepository: UserRepository

    fun createUser(user: User) {
        val userAux = userRepository.find(user.identification)
        if (userAux != null) {
            throw BusinessException("There is an existing User with this id")
        }
        userRepository.create(user)
    }


}
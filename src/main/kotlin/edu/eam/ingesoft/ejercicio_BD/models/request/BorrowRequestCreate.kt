package edu.eam.ingesoft.ejercicio_BD.models.request

import edu.eam.ingesoft.ejercicio_BD.models.entitys.User


data class BorrowRequestCreate(
    val idBorrow: Long,
    val idUser : String,
    val idBook : String,
)

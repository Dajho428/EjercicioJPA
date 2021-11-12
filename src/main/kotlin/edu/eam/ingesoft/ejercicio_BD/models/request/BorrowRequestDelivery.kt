package edu.eam.ingesoft.ejercicio_BD.models.request

data class BorrowRequestDelivery(
    val idBorrow:Long,
    val idBook:String,
    val idUser:String,
)

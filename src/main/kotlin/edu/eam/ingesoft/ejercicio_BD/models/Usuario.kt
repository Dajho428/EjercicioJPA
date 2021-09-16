package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="tbl_usuarios")
data class Usuario(

    @Id
    @Column(name = "user_identification")
    val user_identification: String,

    @Column(name = "apellido_usuario")
    val apellido_usuario:String,

    @Column(name = "nombre_usuario")
    val nombre_usuario: String
):Serializable

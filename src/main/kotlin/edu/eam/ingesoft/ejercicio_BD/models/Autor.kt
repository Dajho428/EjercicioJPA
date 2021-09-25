package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="tbl_autores")
data class Autor(

    @Id
    @Column (name = "codigo_autor")
    val codigo_autor:Long,

    @Column(name="apellido")
    var apellido: String,

    @Column(name = "nombre")
    var nombre : String

):Serializable


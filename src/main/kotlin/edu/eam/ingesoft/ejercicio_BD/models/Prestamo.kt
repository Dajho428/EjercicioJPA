package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table (name = "tbl_prestamos")
data class Prestamo(

    @Id
    @Column(name = "id")
    val id:Long,

    @Column(name = "fecha_prestamo")
    val fecha_prestamo: Date,

    @ManyToOne
    @JoinColumn(name = "id_libro")
    val id_libro:Libro,

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    val id_usuario:Usuario

):Serializable

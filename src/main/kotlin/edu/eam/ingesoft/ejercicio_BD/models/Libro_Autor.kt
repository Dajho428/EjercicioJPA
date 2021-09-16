package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_librosAutores")
data class Libro_Autor(

    @Id
    @Column(name = "id")
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "id_autor")
    val id_autor: Autor,

    @ManyToOne
    @JoinColumn(name = "id_libro")
    val id_libro:Libro
):Serializable

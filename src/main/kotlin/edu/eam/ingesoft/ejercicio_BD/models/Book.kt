package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_libro")
data class Book(

    @Id
    @Column(name = "codigo_libro")
    val code:String,

    @Column(name = "isbn_libro")
    var isbn_libro: String,

    @Column(name = "nombre_libro")
    var name:String,

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    var publisher: Publisher
):Serializable

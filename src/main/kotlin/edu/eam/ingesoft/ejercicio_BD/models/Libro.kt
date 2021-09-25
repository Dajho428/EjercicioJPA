package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_libros")
data class Libro(

    @Id
    @Column(name = "codigo_libro")
    val codigo_libro:String,

    @Column(name = "isbn_libro")
    var isbn_libro: String,

    @Column(name = "nombre_libro")
    var nombre_libro:String,

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    var id_editorial: Editorial
):Serializable

package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_libros")
data class Libro(

    @Id
    @Column(name = "codigo_libro")
    val codigo_librp:String,

    @Column(name = "isbn_libro")
    val isbn_libro: String,

    @Column(name = "nombre_libro")
    val nombre_libro:String,

    @ManyToOne
    @JoinColumn(name = "id_editorial")
    val id_editorial: Editorial
):Serializable

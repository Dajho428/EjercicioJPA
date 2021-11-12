package edu.eam.ingesoft.ejercicio_BD.models.entitys

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "tbl_libro_autor")
data class AuthorBook(

    @Id
    @Column(name = "id")
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "id_autor")
    var author: Author,

    @ManyToOne
    @JoinColumn(name = "id_libro")
    var book: Book
):Serializable

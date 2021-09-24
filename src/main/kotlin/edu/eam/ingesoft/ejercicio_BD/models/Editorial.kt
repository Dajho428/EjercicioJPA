package edu.eam.ingesoft.ejercicio_BD.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name="tbl_editoriales")
data class Editorial(

    @Id
    @Column (name="codigo_editorial")
    val codigo_editorial: Long,

    @Column (name="nombre_editorial")
    val nombre_editorial: String

):Serializable

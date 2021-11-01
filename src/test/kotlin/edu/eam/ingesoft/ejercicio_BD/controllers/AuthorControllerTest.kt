package edu.eam.ingesoft.ejercicio_BD.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.transaction.annotation.Transactional
import java.util.*
import javax.persistence.EntityManager

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class AuthorControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc
    @Autowired
    lateinit var objectMapper: ObjectMapper
    @Autowired
    lateinit var entityManager: EntityManager

//    @Test
//    fun createAuthorHappyPathTest(){
//        val body= """
//            {
//              "id"=10,
//              "lastName"="Hernandez",
//              "name"="Victoria"
//            }
//            """.trimIndent()
//        val request=MockMvcRequestBuilders
//            .post("/authors")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(body)
//        val response=mockMvc.perform(request)
//        val resp=response.andReturn().response
//        println(resp.contentAsString)
//        Assertions.assertEquals(200,resp.status)
//    }
}
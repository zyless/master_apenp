package todo.user

import com.fasterxml.jackson.databind.ObjectMapper
import org.bson.types.ObjectId
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
@SpringBootTest
internal class UserControllerTest {

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @BeforeEach
    fun setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun getUser() {
        val request = MockMvcRequestBuilders.get("/user/rene")
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun getUserNull() {
        val request = MockMvcRequestBuilders.get("/user/")
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isNotFound)
    }

    @Test
    fun createUser() {
        var user: User = User()
        user.userName = "test"
        user.email = "test"
        user.password = "test"
        user.follows = mutableListOf("rene")
        user.shardKey = "t"
        user.id = ("6666b78cd6e0c12d5cdc509b")
        val request = MockMvcRequestBuilders.post("/user/create").content(mapper.writeValueAsString(user)).contentType(
            MediaType.APPLICATION_JSON);
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isConflict)
    }

    @Test
    fun createUserDouble() {
        var user: User = User()
        user.userName = "test"
        user.email = "test"
        user.password = "test"
        user.follows = mutableListOf("rene")
        user.shardKey = "t"
        user.id = ("6666b78cd6e0c12d5cdc509b")
        val request = MockMvcRequestBuilders.post("/user/create").content(mapper.writeValueAsString(user)).contentType(
            MediaType.APPLICATION_JSON);
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isCreated)
    }
    @Test
    fun createUserNull() {

        val request = MockMvcRequestBuilders.post("/user/create")
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun updateUser() {
        var user: User = User()
        user.userName = "test"
        user.email = "test1"
        user.password = "test1"
        user.follows = mutableListOf("rene")
        user.shardKey = "t"
        user.id = ("6666b78cd6e0c12d5cdc509b")
        val request = MockMvcRequestBuilders.post("/user/update").content(mapper.writeValueAsString(user)).contentType(
            MediaType.APPLICATION_JSON);
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun updateUserNull() {
        val request = MockMvcRequestBuilders.post("/user/update")
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun deleteUser() {
        var user: User = User()
        user.userName = "test"
        user.email = "test1"
        user.password = "test1"
        user.follows = mutableListOf("rene")
        user.shardKey = "t"
        user.id = ("6666b78cd6e0c12d5cdc509b")
        val request = MockMvcRequestBuilders.post("/user/delete").content(mapper.writeValueAsString(user)).contentType(
            MediaType.APPLICATION_JSON);
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun deleteUserNull() {
        val request = MockMvcRequestBuilders.post("/user/delete")
        val result = mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().isBadRequest)
    }
}
package todo.crud
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest
internal class TodoControllerTest {


    @Autowired
    lateinit var webApplicationContext: WebApplicationContext
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var mapper: ObjectMapper

    @BeforeEach
    fun beforeEach() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @Test
    fun getFollowedTodos() {
        var userDTO: UserDTO = UserDTO()
        userDTO.userList = mutableListOf("josef", "michi")
        val request = MockMvcRequestBuilders.post("/todo/followed").content(mapper.writeValueAsString(userDTO)).contentType(MediaType.APPLICATION_JSON);
        val result = mockMvc.perform(request).andExpect(status().isOk)

    }

    @Test
    fun getFollowedTodosEmpty() {
        var userDTO: UserDTO = UserDTO()
        val request = MockMvcRequestBuilders.post("/todo/followed").content(mapper.writeValueAsString(userDTO)).contentType(MediaType.APPLICATION_JSON)
        val result = mockMvc.perform(request).andExpect(status().isOk)

    }

    @Test
    fun getFollowedTodosNull() {
        val request = MockMvcRequestBuilders.post("/todo/followed")
        val result = mockMvc.perform(request).andExpect(status().isBadRequest)

    }

    @Test
    fun getTodo() {
        val request = MockMvcRequestBuilders.get("/todo/josef")
        val result = mockMvc.perform(request).andExpect(status().isOk)
    }

    @Test
    fun getTodoNull() {
        val request = MockMvcRequestBuilders.get("/todo/")
        val result = mockMvc.perform(request).andExpect(status().isNotFound)
    }

    @Test
    fun createTodo() {
        var todo: Todo = Todo()
        todo.user = "test"
        todo.text = "test"
        todo.image = "test.jpeg"
        todo.time = "1640085256"
        todo.id = "61c1b78cd6e0c12d5cdc509b"
        val request = MockMvcRequestBuilders.post("/todo/create").content(mapper.writeValueAsString(todo)).contentType(MediaType.APPLICATION_JSON)
        val result = mockMvc.perform(request).andExpect(status().isCreated)

    }

    @Test
    fun createTodoNull() {
        val request = MockMvcRequestBuilders.post("/todo/create")
        val result = mockMvc.perform(request).andExpect(status().isBadRequest)

    }

    @Test
    fun updateTodo() {
        var todo: Todo = Todo()
        todo.user = "test"
        todo.text = "test1"
        todo.image = "test1.jpeg"
        todo.time = "1640085256"
        todo.id = "61c1b78cd6e0c12d5cdc509b"
        val request = MockMvcRequestBuilders.post("/todo/update").content(mapper.writeValueAsString(todo)).contentType(MediaType.APPLICATION_JSON)
        val result = mockMvc.perform(request).andExpect(status().isOk)

    }

    fun updateTodoNull() {
        val request = MockMvcRequestBuilders.post("/todo/update")
        val result = mockMvc.perform(request).andExpect(status().isBadRequest)

    }

    @Test
    fun deleteUser() {
        var todo: Todo = Todo()
        todo.user = "test"
        todo.text = "test1"
        todo.image = "test1.jpeg"
        todo.time = "1640085256"
        todo.id = "61c1b78cd6e0c12d5cdc509b"
        val request = MockMvcRequestBuilders.post("/todo/delete").content(mapper.writeValueAsString(todo)).contentType(MediaType.APPLICATION_JSON)
        val result = mockMvc.perform(request).andExpect(status().isOk)
    }

    @Test
    fun deleteUserNull() {
        val request = MockMvcRequestBuilders.post("/todo/delete")
        val result = mockMvc.perform(request).andExpect(status().isBadRequest)
    }
}
package todo.crud

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat


@RestController
@RequestMapping("/todo")
class TodoController {
    @Autowired
    lateinit var repository: TodoRepository

    @PostMapping("/followed")
    fun getFollowedTodos(@RequestBody userDTO: UserDTO): ResponseEntity<List<Todo>> {
        var todos: MutableList<Todo> = mutableListOf()
        userDTO.userList.forEach{
            repository.findByUser(it)?.let { it1 -> todos.addAll(it1) }
        }
        todos.sortBy {it.time}
        todos.forEach{it.time = getDate(it.time)}
        return ResponseEntity<List<Todo>>(todos, HttpStatus.OK)
    }

    @GetMapping("/{user}")
    fun getTodo(@PathVariable("user") user: String): ResponseEntity<List<Todo>> {
        val todos: List<Todo>? = repository.findByUser(user)
        todos?.forEach { it.time = getDate(it.time) }
        return ResponseEntity<List<Todo>>(todos, HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createTodo(@RequestBody todo: Todo): ResponseEntity<Any> {
            if (todo.user == null || todo.text == null) {
                return ResponseEntity<Any>(null, HttpStatus.BAD_REQUEST)
            }
            todo.time = getTimeStamp()
            repository.save(todo)
            return ResponseEntity<Any>(null, HttpStatus.CREATED)
    }

    @PostMapping("/update")
    fun updateTodo(@RequestBody todo: Todo): ResponseEntity<Any> {
        todo.time = getTimeStamp()
        repository.save(todo)
        return ResponseEntity<Any>(null, HttpStatus.OK)
    }

    @PostMapping("/delete")
    fun deleteUser(@RequestBody todo: Todo): ResponseEntity<Any> {
        repository.deleteById(todo.id)
        return ResponseEntity<Any>(null, HttpStatus.OK)
    }

    fun getTimeStamp(): String {
        var timestamp: Timestamp = Timestamp(System.currentTimeMillis())
        return timestamp.getTime().toString()

    }

    fun getDate(timestamp: String): String {
        val time = java.util.Date(timestamp.toLong())
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        return sdf.format(time)

    }

}
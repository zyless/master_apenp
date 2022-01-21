package todo.user
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController {
    @Autowired
    private lateinit var repository: UserRepository

    @GetMapping("/{username}")
    fun getUser(@PathVariable("username") userName: String): ResponseEntity<User> {
        val user: User? = repository.findByUserName(userName)
        print(user)
        return ResponseEntity<User>(user, HttpStatus.OK)
    }

    @PostMapping("/create")
    fun createUser(@RequestBody user: User): ResponseEntity<Any> {
        if (repository.findByUserName(user.userName) == null) {
            user.shardKey = user.userName.first().toString().lowercase()
            repository.save(user)
            return ResponseEntity<Any>(null, HttpStatus.CREATED)
        } else {
            return ResponseEntity<Any>("user already exists", HttpStatus.CONFLICT)
        }
    }

    @PostMapping("/update")
    fun updateUser(@RequestBody user: User): ResponseEntity<Any> {
        repository.save(user)
        return ResponseEntity<Any>(null, HttpStatus.OK)
    }

    @PostMapping("/delete")
    fun deleteUser(@RequestBody user: User): ResponseEntity<Any> {
            repository.deleteById(user.id)
            return ResponseEntity<Any>(null, HttpStatus.OK)
    }
}

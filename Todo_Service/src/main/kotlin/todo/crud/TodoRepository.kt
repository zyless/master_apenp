package todo.crud

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TodoRepository: MongoRepository<Todo, String> {
    fun findByUser(user: String): List<Todo>?
}
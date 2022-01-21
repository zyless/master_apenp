package todo.user

import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository: MongoRepository<User, String> {

    fun findByUserName(userName: String): User?

}
package todo.user

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("user")
class User {

    @Id
    lateinit var id: String
    lateinit var userName: String
    lateinit var email: String
    lateinit var password: String
    var follows = listOf<String>()
    lateinit var shardKey: String




}
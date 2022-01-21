package todo.crud

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.lang.Boolean.FALSE

@Document("todo")
class Todo() {
    @Id
    lateinit var id: String
    lateinit var text: String
    lateinit var user: String
    lateinit var time: String
    lateinit var image: String
    var done: Boolean = FALSE
    var highPriority: Boolean = FALSE
}
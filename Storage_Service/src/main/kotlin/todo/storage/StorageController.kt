package todo.storage

import org.apache.coyote.Response
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.core.io.WritableResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.StreamUtils
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.nio.charset.Charset


@RestController
@RequestMapping("/storage")
class StorageController {




    @Autowired
    lateinit var resourceLoader: ResourceLoader
    val blobConnection: String = "abc"

    @GetMapping("/{blobName}")
    fun getBlob(@PathVariable("blobName") blobName: String): ResponseEntity<Any> {
        val blobFile: Resource = resourceLoader.getResource(blobConnection + blobName)
        val response: String = StreamUtils.copyToString(
            blobFile.getInputStream(),
            Charset.defaultCharset());
        return ResponseEntity<Any>(response, HttpStatus.OK)

    }

    @PostMapping("/create")
    fun createBlobFile(@RequestBody blob: BlobDTO): ResponseEntity<Any> {
        val blobFile: Resource = resourceLoader.getResource(blobConnection + blob.name)
        (blobFile as WritableResource).outputStream.use { os -> os.write(blob.image.toByteArray()) }
        return ResponseEntity<Any>(null, HttpStatus.CREATED)
    }

        @PostMapping("/update")
    fun updateBlobFile(@RequestBody blob: BlobDTO): ResponseEntity<Any> {
        val blobFile: Resource = resourceLoader.getResource(blobConnection + blob.name)
        (blobFile as WritableResource).outputStream.use { os -> os.write(blob.image.toByteArray()) }
        return ResponseEntity<Any>(null, HttpStatus.OK)
    }


}

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.asyncmap.intasyncmap

import com.mongodb.async.client.MongoCollection
import com.mongodb.client.model.Filters
import net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb.MongoDB
import org.bson.Document
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 16:31.
 * Last edit 31.07.2018
 */
class MongoIntAsyncMap(mongoDB: MongoDB, databaseName: String, name: String) : DefaultIntAsyncMap {

    private val collection: MongoCollection<Document> = mongoDB.connect().getDatabase(databaseName).getCollection(name)

    override fun get(uuid: UUID, key: String, lambda: (Int) -> Unit) = get(
            uuid,
            key,
            lambda,
            { throw NullPointerException("document can not be null") },
            { throw NullPointerException("Int by key \"$key\" can not be null") }
    )

    fun getOrInsert(uuid: UUID, key: String, value: Int, lambda: (Int) -> Unit) = getDocumentByUUID(uuid).first { document, _ ->
        if (document == null)
            lambda(value)
        else {
            val i = document[key] as? Int ?: value
            lambda(i)
        }
    }

    fun get(uuid: UUID, key: String, lambda: (Int) -> Unit, onFailDocument: () -> Unit, onFailValue: () -> Nothing) = getDocumentByUUID(uuid).first { document, _ ->
        document ?: onFailDocument()
        val i = document[key] as? Int ?: onFailValue()
        lambda(i)
    }

    fun insertOne(uuid: UUID, key: String, value: Int, lambda: () -> Unit) = collection.insertOne(Document("uuid", uuid).append(key, value)) { _, _ -> lambda() }

    override fun set(uuid: UUID, key: String, value: Int, lambda: () -> Unit) {
        getDocumentByUUID(uuid).first { document, t ->
            document ?: return@first
            document[key] = value
            lambda()
            println(document)
            println(t)
        }
        collection.insertOne(Document("uuid", uuid).append(key, value)) { document, t ->
            lambda()
            println(document)
            println(t)
        }
    }

    private fun getDocumentByUUID(uuid: UUID) = collection.find(Filters.eq("uuid", uuid))

}
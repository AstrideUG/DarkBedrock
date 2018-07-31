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

    private val collection: MongoCollection<Document> = mongoDB.connect().client.getDatabase(databaseName).getCollection(name)

    override fun get(uuid: UUID, key: String, lambda: (Int) -> Unit) = getDocumentByUUID(uuid).first { document, _ ->
        document ?: return@first
        val i = document[key] as? Int ?: return@first
        lambda(i)
    }

    override fun set(uuid: UUID, key: String, value: Int, lambda: () -> Unit) = getDocumentByUUID(uuid).first { document, _ ->
        document ?: return@first
        document[key] = value
        lambda()
    }

    private fun getDocumentByUUID(uuid: UUID) = collection.find(Filters.eq("uuid", uuid))

}
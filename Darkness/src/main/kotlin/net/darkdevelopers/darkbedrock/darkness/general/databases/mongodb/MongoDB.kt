/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import com.mongodb.ConnectionString
import com.mongodb.async.client.MongoClient
import com.mongodb.async.client.MongoClients
import net.darkdevelopers.darkbedrock.darkness.general.configs.Config
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.databases.mysql.MySQLData

@Suppress("unused")
data class MongoDB(private val pair: Pair<String, Int>) {

    lateinit var client: MongoClient
    //    private lateinit var database: MongoDatabase
    private val host: String = pair.first
    private val port: Int = pair.second
//    val collections: MutableMap<String, MongoCollection<Document>> = mutableMapOf()

    private constructor(config: Config = GsonConfig(ConfigData("configs", "mongodb.json"))) : this(try {
        MongoData.createMongoData(config)
    } catch (ex: NullPointerException) {
        MySQLData.printDefaultMySQLDataInConfigFile(config)
        println()
        println("[MongoDB] Please enter the MongoDB data")
        println()
        throw IllegalArgumentException("The MongoDB data has not configured yet")
    })

    fun connect(): MongoDB {
        client = MongoClients.create(ConnectionString("mongodb://$host:$port"))
        return this
    }

    fun connect(username: String, password: String, database: String): MongoDB {
        client = MongoClients.create(ConnectionString("mongodb://$username:$password@$host:$port/$database"))
//        setDatabase()
        return this
    }

    companion object {
        var simpleInstance: MongoDB? = null
            private set
            get() {
                if (field == null) field = MongoDB()
                return field
            }
    }


//    fun getCollection(key: String) = getCollection(key, key)
//
//    @Suppress("MemberVisibilityCanBePrivate")
//    fun getCollection(key: String, collectionKey: String): MongoCollection<Document> {
//        val collection = database.getCollection(collectionKey)
//                ?: throw NullPointerException("Collection by $collectionKey can not be null")
//        collections[key] = collection
//        return collection
//    }

//    private fun setDatabase() {
//        database = client.getDatabase("MainDB")
//    }
}
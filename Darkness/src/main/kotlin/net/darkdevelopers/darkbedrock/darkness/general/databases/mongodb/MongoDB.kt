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

@Suppress("unused")
data class MongoDB(private val pair: Pair<String, Int>) {

    lateinit var client: MongoClient
    private val host: String = pair.first
    private val port: Int = pair.second

    private constructor() : this(GsonConfig(ConfigData("configs", "mongodb.json")))

    constructor(config: Config) : this(try {
        MongoData.createMongoData(config)
    } catch (ex: NullPointerException) {
        MongoData.printDefaultMongoDataInConfigFile(config)
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
            get() = if (field == null) MongoDB() else field
            private set
    }
}
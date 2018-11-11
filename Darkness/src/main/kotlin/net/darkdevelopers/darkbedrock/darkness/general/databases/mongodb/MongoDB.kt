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
data class MongoDB(private val mongoData: MongoData) {

	lateinit var client: MongoClient

	private constructor() : this(GsonConfig(ConfigData("configs", "mongodb.json")))

	constructor(config: Config) : this(try {
		MongoData.createMongoData(config)
	} catch (ex: NullPointerException) {
		MongoData.printDefaultMongoDataInConfigFile(config)
		println()
		println("[MongoDB] Please enter the MongoDB data")
		println()
//        MongoData.createMongoData(config)
		throw IllegalArgumentException("The MongoDB data has not configured yet")
	})

//    fun connect(): MongoClient {
//        client = MongoClients.create(ConnectionString("mongodb://${mongoData.host}:${mongoData.port}"))
//        return client
//    }

	fun connect(): MongoClient {
		client = if (mongoData.simple)
			MongoClients.create(ConnectionString("mongodb://${mongoData.host}:${mongoData.port}"))
		else
			MongoClients.create(ConnectionString("mongodb://${mongoData.username}:${mongoData.password}@${mongoData.host}:${mongoData.port}/"))
		return client
	}

	fun connect(database: String): MongoClient {
		client = MongoClients.create(ConnectionString("mongodb://${mongoData.username}:${mongoData.password}@${mongoData.host}:${mongoData.port}/$database"))
		return client
	}

	companion object {
		//        val simpleInstance by Delegate()
		val simpleInstance by lazy { MongoDB() }

//        var simpleInstance: MongoDB? = null
//            get() = if (field == null) MongoDB() else field
//            private set
	}

//    private class Delegate {
//        var simpleInstance: MongoDB? = null
//
//        operator fun getValue(ref: Any?, property: KProperty<*>) = if (simpleInstance == null) MongoDB() else simpleInstance!!
//    }
}


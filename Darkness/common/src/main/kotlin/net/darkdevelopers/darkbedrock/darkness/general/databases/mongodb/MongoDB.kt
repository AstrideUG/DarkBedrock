/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import com.google.gson.JsonObject
import com.mongodb.ConnectionString
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.functions.load
import net.darkdevelopers.darkbedrock.darkness.general.functions.toConfigData
import net.darkdevelopers.darkbedrock.darkness.general.functions.toMap

@Suppress("unused")
data class MongoDB(private val mongoData: MongoData) {

    lateinit var client: MongoClient

    private constructor() : this("configs".toConfigData("mongodb"))

    constructor(configData: ConfigData) : this(
        MongoData(configData.load<JsonObject>().toMap())
    )

    fun connect(database: String = ""): MongoClient {
        client = if (mongoData.simple)
            MongoClients.create(ConnectionString("mongodb://${mongoData.host}:${mongoData.port}/$database"))
        else
            MongoClients.create(ConnectionString("mongodb://${mongoData.username}:${mongoData.password}@${mongoData.host}:${mongoData.port}/"))
        return client
    }

    companion object {
        val simpleInstance by lazy { MongoDB() }
    }

}


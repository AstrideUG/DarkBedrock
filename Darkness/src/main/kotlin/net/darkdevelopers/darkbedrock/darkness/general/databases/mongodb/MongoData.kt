/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.Config

@Suppress("unused")
object MongoData {

    internal fun createMongoData(config: Config): Pair<String, Int> {
        config.load()
        val host = config.getAs<JsonPrimitive>("Host")?.asString
                ?: throw NullPointerException("The MongoDB host data can not be null")
        val port = config.getAs<JsonPrimitive>("Port")?.asInt
                ?: throw NullPointerException("The MongoDB port data can not be null")
        return Pair(host, port)
    }

    internal fun printDefaultMongoDataInConfigFile(config: Config) = config.apply {
        load()
        put("Host", "localhost")
        put("Port", 27017)
        save()
    }

    private fun <O : Any?> get(config: Config, key: String) = config.getAs<O>(key)
            ?: throwMongoDataNullPointerException(key.toLowerCase())

    private fun throwMongoDataNullPointerException(data: String): Nothing =
            throw NullPointerException("The MongoDB $data data can not be null")
}
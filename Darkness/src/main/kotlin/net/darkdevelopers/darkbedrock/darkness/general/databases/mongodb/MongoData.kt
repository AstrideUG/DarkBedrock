/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.Config

@Suppress("unused")
data class MongoData(val host: String, val port: Int, val username: String, val password: String) {

    companion object {
        internal fun createMongoData(config: Config): MongoData {
            config.load()
            val host = config.getAs<JsonPrimitive>("host")?.asString
                    ?: throw NullPointerException("The MongoDB host data can not be null")
            val port = config.getAs<JsonPrimitive>("port")?.asInt
                    ?: throw NullPointerException("The MongoDB port data can not be null")
            val username = config.getAs<JsonPrimitive>("username")?.asString
                    ?: throw NullPointerException("The MongoDB username data can not be null")
            val password = config.getAs<JsonPrimitive>("password")?.asString
                    ?: throw NullPointerException("The MongoDB password data can not be null")
            return MongoData(host, port, username, password)
        }

        internal fun printDefaultMongoDataInConfigFile(config: Config) = config.apply {
            load()
            put("Host", "localhost")
            put("Port", 27017)
            put("Username", "root")
            put("Password", "123456")
            save()
        }

        private fun <O : Any?> get(config: Config, key: String) = config.getAs<O>(key)
                ?: throwMongoDataNullPointerException(key.toLowerCase())

        private fun throwMongoDataNullPointerException(data: String): Nothing =
                throw NullPointerException("The MongoDB $data data can not be null")


    }
}
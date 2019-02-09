/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.Config
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull

@Suppress("unused")
data class MongoData(val host: String, val port: Int, val username: String, val password: String, val simple: Boolean) {

    companion object {
        internal fun createMongoData(config: Config): MongoData {
            config.load()
            val simple = config.getAs<JsonPrimitive>("simple")?.asBoolean.toNonNull("The MongoDB simple data")
            val host = config.getAs<JsonPrimitive>("host")?.asString.toNonNull("The MongoDB host data")
            val port = config.getAs<JsonPrimitive>("port")?.asInt.toNonNull("The MongoDB port data")
            val username = config.getAs<JsonPrimitive>("username")?.asString.toNonNull("The MongoDB username data")
            val password = config.getAs<JsonPrimitive>("password")?.asString.toNonNull("The MongoDB password data")
            return MongoData(
                host,
                port,
                username,
                password,
                simple
            )
        }

        internal fun printDefaultMongoDataInConfigFile(config: Config) =
            config.apply {
                load()
                put("simple", true)
                put("host", "localhost")
                put("port", 27017)
                put("username", "root")
                put("password", "123456")
                save()
            }

        private fun <O : Any?> get(
            config: Config,
            key: String
        ) = config.getAs<O>(key)
            ?: throwMongoDataNullPointerException(
                key.toLowerCase()
            )

        private fun throwMongoDataNullPointerException(data: String): Nothing =
            throw NullPointerException("The MongoDB $data data can not be null")


    }
}
/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mysql

import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkness.general.configs.Config

data class MySQLData(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val database: String
) {

    companion object {

        internal fun createMySQLData(config: Config): MySQLData {
            config.load()
            val host = get<JsonPrimitive>(config, "Host").asString
            val port = get<JsonPrimitive>(config, "Port").asInt
            val username = get<JsonPrimitive>(config, "Username").asString
            val password = get<JsonPrimitive>(config, "Password").asString
            val database = get<JsonPrimitive>(config, "Database").asString
            return MySQLData(host, port, username, password, database)
        }

        internal fun printDefaultMySQLDataInConfigFile(config: Config) = config.run {
            load()
            put("Host", "localhost")
            put("Port", 3306)
            put("Username", "Minecraft")
            put("Password", "PW123456")
            put("Database", "Plugins")
            save()
        }

        private fun <O : Any> get(config: Config, key: String) = config.getAs<O>(key)
            ?: throwMySQLDataNullPointerException(key.toLowerCase())

        private fun throwMySQLDataNullPointerException(data: String): Nothing =
            throw NullPointerException("The MySQL $data data can not be null")
    }
}
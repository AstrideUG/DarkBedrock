/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mysql

import net.darkdevelopers.darkbedrock.darkness.general.configs.Config

data class MySQLData(val host: String,
                     val port: Int,
                     val username: String,
                     val password: String,
                     val database: String) {

    companion object {

        internal fun createMySQLData(config: Config): MySQLData {
            config.load()
            val host = get<String>(config, "Host")
            val port = get<Int>(config, "Port")
            val username = get<String>(config, "Username")
            val password = get<String>(config, "Password")
            val database = get<String>(config, "Database")
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
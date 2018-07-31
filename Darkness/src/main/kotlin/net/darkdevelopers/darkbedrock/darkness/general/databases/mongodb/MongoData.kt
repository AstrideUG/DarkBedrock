/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import net.darkdevelopers.darkbedrock.darkness.general.configs.Config

@Suppress("unused")
class MongoData {

    companion object {

        internal fun createMongoData(config: Config): Pair<String, Int> {
            config.load()
            val host = get<String>(config, "Host")
            val port = get<Int>(config, "Port")
//            val username = config.getAs<String>("Username") ?: throwMySQLDataNullPointerException("username")
//            val password = config.getAs<String>("Password") ?: throwMySQLDataNullPointerException("password")
//            val database = config.getAs<String>("Database") ?: throwMySQLDataNullPointerException("database")
            return Pair(host, port)
        }

        internal fun printDefaultMongoDataInConfigFile(config: Config) = config.run {
            load()
            put("Host", "localhost")
            put("Port", 27017)
//            put("Username", "Minecraft")
//            put("Password", "PW123456")
//            put("Database", "Plugins")
            save()
        }

        private fun <O : Any?> get(config: Config, key: String) = config.getAs<O>(key)
                ?: throwMongoDataNullPointerException(key.toLowerCase())


        private fun throwMongoDataNullPointerException(data: String): Nothing =
                throw NullPointerException("The MongoDB $data data can not be null")
    }
}
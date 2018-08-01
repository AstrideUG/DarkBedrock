/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import net.darkdevelopers.darkbedrock.darkness.general.configs.Config

@Suppress("unused")
object MongoData {

    internal fun createMongoData(config: Config): Pair<String, Int> {
        config.load()
        val host = get<String>(config, "Host")
        val port = get<Int>(config, "Port")
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
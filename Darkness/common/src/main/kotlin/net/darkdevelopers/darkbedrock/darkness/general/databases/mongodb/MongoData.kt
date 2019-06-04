/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 00:14.
 * Current Version: 1.0 (05.06.2019 - 05.06.2019)
 */
data class MongoData(
    val host: String = "localhost",
    val port: Int = 27017,
    val username: String = "",
    val password: String = "",
    val simple: Boolean = true
)

/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb

import net.darkdevelopers.darkbedrock.darkness.general.configs.default
import net.darkdevelopers.darkbedrock.darkness.general.configs.getValue

/**
 * Created on 05.06.2019 00:14.
 * @author Lars Artmann | LartyHD
 */
class MongoData(values: Map<String, Any?>) {
    val host: String by values.default { "localhost" }
    val port: Int by values.default { 27017 }
    val username: String by values.default { "" }
    val password: String by values.default { "" }
    val simple: Boolean by values.default { true }
}


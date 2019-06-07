/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mysql

import net.darkdevelopers.darkbedrock.darkness.general.configs.default
import net.darkdevelopers.darkbedrock.darkness.general.configs.getValue

class MySQLData(values: Map<String, Any?>) {
    val host: String by values.default { "localhost" }
    val port: Int by values.default { 3306 }
    val database: String by values.default { "Minecraft" }
    val username: String by values.default { "root" }
    val password: String by values.default { "database" }
}

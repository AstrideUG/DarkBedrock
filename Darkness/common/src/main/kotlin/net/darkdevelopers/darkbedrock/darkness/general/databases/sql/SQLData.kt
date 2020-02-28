/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.sql

import net.darkdevelopers.darkbedrock.darkness.general.configs.default
import net.darkdevelopers.darkbedrock.darkness.general.configs.getValue

class SQLData(values: Map<String, Any?>) {
    val type: String by values.default { "mysql" }
    val host: String by values.default { "localhost" }
    val port: Int by values.default { 3306 }
    val database: String by values.default { "database" }
    val username: String by values.default { "root" }
    val password: String by values.default { "root" }
    val addon: String by values.default { "" }
}

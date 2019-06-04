/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.databases.mysql

data class MySQLData(
    val host: String = "localhost",
    val port: Int = 3306,
    val username: String = "root",
    val password: String = "root",
    val database: String = "database"
)

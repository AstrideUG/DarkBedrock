/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.general.databases.mysql

import net.darkdevelopers.darkbedrock.darkness.general.configs.Config
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.coroutines.suspendCoroutine


@Suppress("unused")
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 10.11.2017 00:44.
 * Last edit 18.04.2018
 */
class MySQL(private val mySQLData: MySQLData) {
    private val prefix = "[MySQL] "
    private var connection: java.sql.Connection? = null
        get() {
            if (field == null) connect() else if (field?.isValid(2)!!) {
                disconnect()
                connect()
            }
            return field
        }

    constructor(config: Config = GsonConfig(ConfigData("configs", "mysql.json"))) : this(
        try {
            MySQLData.createMySQLData(config)
        } catch (ex: NullPointerException) {
            MySQLData.printDefaultMySQLDataInConfigFile(config)
            println()
            println("[MySQL] Please enter the MySQL data")
            println()
            throw IllegalArgumentException("The MySQL data has not configured yet")
        }
    )

    suspend fun update(update: String) = suspendCoroutine<Unit> { updateSync(update) }

    @Suppress("MemberVisibilityCanBePrivate")
    fun updateSync(update: String) {
        connection?.createStatement()?.executeUpdate(update)
    }

    suspend fun preparedUpdate(update: PreparedStatement) = suspendCoroutine<Unit> { preparedUpdateSync(update) }

    @Suppress("MemberVisibilityCanBePrivate")
    fun preparedUpdateSync(update: PreparedStatement) {
        update.executeUpdate()
    }

    suspend fun query(query: String) = suspendCoroutine<ResultSet> { querySync(query) }

    @Suppress("MemberVisibilityCanBePrivate")
    fun querySync(query: String) = connection?.createStatement()?.executeQuery(query)!!

    suspend fun preparedQuery(preparedQuery: PreparedStatement) =
        suspendCoroutine<ResultSet> { preparedQuerySync(preparedQuery) }

    @Suppress("MemberVisibilityCanBePrivate")
    fun preparedQuerySync(preparedQuery: PreparedStatement) = preparedQuery.executeQuery()!!

    private fun connect() = if (isOpen()) {
        println("${prefix}The connection is made...")
        connection = DriverManager.getConnection(
            "jdbc:mysql://${mySQLData.host}:${mySQLData.port}/${mySQLData.database}",
            mySQLData.username,
            mySQLData.password
        )
        println("${prefix}The connection was successfully established")
    } else System.err.println("${prefix}The connection has already established")

    private fun disconnect() = if (!isOpen()) {
        println("${prefix}The connection is closing...")
        connection?.close()
        connection = null
        println("${prefix}The connection was successfully closed")
    } else System.err.println("${prefix}The connection has already closed")

    private fun isOpen() = connection != null && !connection!!.isClosed

}
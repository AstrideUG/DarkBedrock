/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.general.databases.mysql

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.mapped
import net.darkdevelopers.darkbedrock.darkness.general.configs.toConfigMap
import net.darkdevelopers.darkbedrock.darkness.general.functions.load
import net.darkdevelopers.darkbedrock.darkness.general.functions.save
import net.darkdevelopers.darkbedrock.darkness.general.functions.toConfigData
import net.darkdevelopers.darkbedrock.darkness.general.functions.toMap
import java.sql.Connection
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
    private var connection: Connection? = null
        get() {
            if (field?.isValid(2)!!) {
                disconnect(field)
                connect()
            }
            return field
        }

    private constructor() : this("configs".toConfigData("mysql"))

    constructor(configData: ConfigData) : this(
        try {
            configData.load<JsonObject>().toMap().mapped<MySQLData>()!!
        } catch (ex: NullPointerException) {
            configData.save(MySQLData().toConfigMap())
            println()
            println("[MySQL] Please enter the MySQL data")
            println()
            throw IllegalArgumentException("The MySQL data has not configured yet")
        }
    )

    init {
        connect()
    }

    suspend fun update(update: String) = suspendCoroutine<Unit> { updateSync(update) }

    fun updateSync(update: String) {
        connection?.createStatement()?.executeUpdate(update)
    }

    suspend fun preparedUpdate(update: PreparedStatement) = suspendCoroutine<Unit> { preparedUpdateSync(update) }

    fun preparedUpdateSync(update: PreparedStatement) {
        update.executeUpdate()
    }

    suspend fun query(query: String) = suspendCoroutine<ResultSet> { querySync(query) }

    fun querySync(query: String) = connection?.createStatement()?.executeQuery(query)!!

    suspend fun preparedQuery(preparedQuery: PreparedStatement) =
        suspendCoroutine<ResultSet> { preparedQuerySync(preparedQuery) }

    fun preparedQuerySync(preparedQuery: PreparedStatement) = preparedQuery.executeQuery()!!

    private fun connect() /*= if (isOpen())*/ {
        println("${prefix}The connection is made...")
        connection = DriverManager.getConnection(
            "jdbc:mysql://${mySQLData.host}:${mySQLData.port}/${mySQLData.database}",
            mySQLData.username,
            mySQLData.password
        )
        println("${prefix}The connection was successfully established")
    } //else System.err.println("${prefix}The connection has already established")

    private fun disconnect(connection: Connection?)/* = if (!isOpen()) */ {
        println("${prefix}The connection is closing...")
        connection?.close()
        this.connection = null
        println("${prefix}The connection was successfully closed")
    } //else System.err.println("${prefix}The connection has already closed")

    //private fun isOpen() = connection != null && connection?.isClosed == false

}
/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.*
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.DefaultConfig
import java.io.FileWriter
import java.nio.file.Files

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:18.
 * Last edit 15.08.2018
 */
@Suppress("unused")
open class GsonConfig(override val configData: ConfigData) : DefaultConfig, Cloneable {

    var jsonObject: JsonObject = JsonObject()

    override fun load(): GsonConfig {
        try {
            jsonObject = JsonParser().parse(String(Files.readAllBytes(getFile().toPath()))).asJsonObject
        } catch (ex: IllegalStateException) {
            save()
        }
        return this
    }

    override fun save(): GsonConfig {
		ConfigData.createFoldersIfNotExists(getDirectory())
		ConfigData.createFileIfNotExists(getFile())
        FileWriter(getFile()).apply {
            write(formatJson(jsonObject))
            flush()
            close()
        }
        return this
    }

    fun <O> getAsNotNull(key: String): O = getAsNotNull(key, jsonObject)

    @Suppress("MemberVisibilityCanBePrivate")
    fun <O> getAsNotNull(key: String, jsonObject: JsonObject): O = getAs(key, jsonObject)
            ?: throw NullPointerException("$key can not be null")

    override fun <O> getAs(key: String): O? = getAs(key, jsonObject)

    @Suppress("MemberVisibilityCanBePrivate")
    fun <O> getAs(key: String, jsonObject: JsonObject): O? {
        val any = jsonObject[key] as? O
        if (any == null) {
            put(key, JsonNull.INSTANCE)
            save()
        }
        return any
    }

    override fun <I> put(key: String, value: I): GsonConfig {
        when {
            value is JsonElement -> jsonObject.add(key, value)
            value?.toString() == null -> jsonObject.remove(key)
            else -> jsonObject.add(key, JsonPrimitive(value.toString()))
        }
        return this
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun formatJson(jsonElement: JsonElement): String = GsonBuilder().setPrettyPrinting().create().toJson(jsonElement)


    override fun clone() = copy(jsonObject)

    @Suppress("MemberVisibilityCanBePrivate")
    fun copy(jsonObject: JsonObject): GsonConfig {
        val gsonConfig = GsonConfig(configData)
        gsonConfig.jsonObject = jsonObject
        return gsonConfig
    }
}
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
open class GsonConfig(override val configData: ConfigData) : DefaultConfig {

    protected var jsonObject: JsonObject = JsonObject()

    override fun load(): GsonConfig {
        try {
            jsonObject = JsonParser().parse(String(Files.readAllBytes(getFile().toPath()))).asJsonObject
        } catch (ex: IllegalStateException) {
            save()
        }
        return this
    }

    override fun save(): GsonConfig {
        FileWriter(getFile()).apply {
            write(formatJson(jsonObject))
            flush()
            close()
        }
        return this
    }

    override fun <O> getAs(key: String): O? {
        val any = jsonObject[key] as? O
        if (any == null) {
            put(key, "null")
            save()
        }
        return any
    }

    fun <O> getAs(key: String, jsonObject: JsonObject): O? {
        val any = jsonObject[key] as? O
        if (any == null) {
            put(key, "null")
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
}
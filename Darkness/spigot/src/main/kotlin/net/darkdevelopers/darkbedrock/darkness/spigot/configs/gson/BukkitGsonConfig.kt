package net.darkdevelopers.darkbedrock.darkness.spigot.configs.gson

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:09.
 * Last edit 15.08.2018
 */
@Suppress("MemberVisibilityCanBePrivate")
class BukkitGsonConfig(configData: ConfigData) : GsonConfig(configData) {

    override fun load(): BukkitGsonConfig {
        super.load()
        return this
    }

    override fun save(): BukkitGsonConfig {
        super.save()
        return this
    }

    override fun <I> put(key: String, value: I): BukkitGsonConfig {
        super.put(key, value)
        return this
    }

    fun setLocation(location: Location) = setLocation(location, jsonObject)

    fun setLocation(location: Location, jsonObject: JsonObject) = setLocation(null, location, jsonObject)

    fun setLocation(key: String, location: Location) = setLocation(key, location, jsonObject)

    fun setLocation(key: String?, location: Location, jsonObject: JsonObject): BukkitGsonConfig {
        val value = jsonObject.apply {
            addProperty("world", location.world.name)
            addProperty("x", location.x)
            addProperty("y", location.y)
            addProperty("z", location.z)
            addProperty("yaw", location.yaw)
            addProperty("pitch", location.pitch)
        }
        if (key != null) put(key, value)
        return this
    }

    fun getLocation() = getLocation(jsonObject)

    fun getLocation(jsonObject: JsonObject) = getLocation(null, jsonObject)

    fun getLocation(key: String) = getLocation(key, jsonObject)

    fun getLocation(key: String?, jsonObject: JsonObject): Location {
        val read = if (key != null) jsonObject.get(key)?.asJsonObject
            ?: throw NullPointerException("jsonObject can not be null") else jsonObject
        return Location(
            Bukkit.getWorld(read.get("world").asString),
            read.get("x").asDouble,
            read.get("y").asDouble,
            read.get("z").asDouble,
            read.get("yaw").asFloat,
            read.get("pitch").asFloat
        )
    }

    fun setLocationWithOutYawAndPitch(location: Location) = setLocationWithOutYawAndPitch(location, jsonObject)

    fun setLocationWithOutYawAndPitch(location: Location, jsonObject: JsonObject) =
        setLocationWithOutYawAndPitch(null, location, jsonObject)

    fun setLocationWithOutYawAndPitch(key: String, location: Location) =
        setLocationWithOutYawAndPitch(key, location, jsonObject)

    fun setLocationWithOutYawAndPitch(key: String?, location: Location, jsonObject: JsonObject): BukkitGsonConfig {
        val value = jsonObject.apply {
            addProperty("world", location.world.name)
            addProperty("x", location.x)
            addProperty("y", location.y)
            addProperty("z", location.z)
        }
        if (key != null) put(key, value)
        return this
    }

    fun getLocationWithOutYawAndPitch() = getLocationWithOutYawAndPitch(jsonObject)

    fun getLocationWithOutYawAndPitch(jsonObject: JsonObject) = getLocationWithOutYawAndPitch(null, jsonObject)

    fun getLocationWithOutYawAndPitch(key: String) = getLocationWithOutYawAndPitch(key, jsonObject)

    fun getLocationWithOutYawAndPitch(key: String?, jsonObject: JsonObject): Location {
        val read = if (key != null) jsonObject.getAsJsonObject(key)
            ?: throw NullPointerException("jsonObject can not be null") else jsonObject
        return Location(
            Bukkit.getWorld(read.get("world").asString),
            read.get("x").asDouble,
            read.get("y").asDouble,
            read.get("z").asDouble
        )
    }


    fun getLocation(world: World) = getLocation(jsonObject, world)

    fun getLocation(jsonObject: JsonObject, world: World) = getLocation(null, jsonObject, world)

    fun getLocation(key: String, world: World) = getLocation(key, jsonObject, world)

    fun getLocation(key: String?, jsonObject: JsonObject, world: World): Location {
        val read = if (key != null) jsonObject.get(key)?.asJsonObject
            ?: throw NullPointerException("jsonObject can not be null") else jsonObject
        return Location(
            world,
            read.get("x").asDouble,
            read.get("y").asDouble,
            read.get("z").asDouble,
            read.get("yaw").asFloat,
            read.get("pitch").asFloat
        )
    }

    fun getLocationWithOutYawAndPitch(world: World) = getLocationWithOutYawAndPitch(jsonObject, world)

    fun getLocationWithOutYawAndPitch(jsonObject: JsonObject, world: World) =
        getLocationWithOutYawAndPitch(null, jsonObject, world)

    fun getLocationWithOutYawAndPitch(key: String, world: World) = getLocationWithOutYawAndPitch(key, jsonObject, world)

    fun getLocationWithOutYawAndPitch(key: String?, jsonObject: JsonObject, world: World): Location {
        val read = if (key != null) jsonObject.getAsJsonObject(key)
            ?: throw NullPointerException("jsonObject can not be null") else jsonObject
        return Location(
            world,
            read.get("x").asDouble,
            read.get("y").asDouble,
            read.get("z").asDouble
        )
    }
}
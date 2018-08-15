package net.darkdevelopers.darkbedrock.darkness.spigot.configs.gson

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:09.
 * Last edit 15.08.2018
 */
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

    @Suppress("MemberVisibilityCanBePrivate")
    fun setLocation(location: Location, jsonObject: JsonObject): BukkitGsonConfig {
        jsonObject.apply {
            addProperty("world", location.world.name)
            addProperty("x", location.x)
            addProperty("y", location.y)
            addProperty("z", location.z)
            addProperty("yaw", location.yaw)
            addProperty("pitch", location.pitch)
        }
        return this
    }

    fun setLocationWithOutYawAndPitch(location: Location) = setLocationWithOutYawAndPitch(location, jsonObject)

    fun setLocationWithOutYawAndPitch(location: Location, jsonObject: JsonObject): BukkitGsonConfig {
        jsonObject.apply {
            addProperty("world", location.world.name)
            addProperty("x", location.x)
            addProperty("y", location.y)
            addProperty("z", location.z)
        }
        return this
    }

    fun getLocationWithOutYawAndPitch() = getLocationWithOutYawAndPitch(jsonObject)

    @Suppress("MemberVisibilityCanBePrivate")
    fun getLocationWithOutYawAndPitch(jsonObject: JsonObject) = Location(
            Bukkit.getWorld(jsonObject.get("world").asString),
            jsonObject.get("x").asDouble,
            jsonObject.get("y").asDouble,
            jsonObject.get("z").asDouble
    )

    fun getLocation() = getLocation(jsonObject)

    @Suppress("MemberVisibilityCanBePrivate")
    fun getLocation(jsonObject: JsonObject) = Location(
            Bukkit.getWorld(jsonObject.get("world").asString),
            jsonObject.get("x").asDouble,
            jsonObject.get("y").asDouble,
            jsonObject.get("z").asDouble,
            jsonObject.get("yaw").asFloat,
            jsonObject.get("pitch").asFloat
    )
}
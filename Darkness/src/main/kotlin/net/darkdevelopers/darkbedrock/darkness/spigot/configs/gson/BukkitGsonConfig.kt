package net.darkdevelopers.darkbedrock.darkness.spigot.configs.gson

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import org.bukkit.Bukkit
import org.bukkit.Location

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:09.
 * Last edit 02.06.2018
 */
class BukkitGsonConfig(override val configData: ConfigData) : GsonConfig(configData) {

    override fun load(): BukkitGsonConfig {
        super.load()
        return this
    }

    override fun save(): GsonConfig {
        super.save()
        return this
    }

    override fun <I> put(key: String, value: I): GsonConfig {
        super.put(key, value)
        return this
    }

    /**
     * This method does not save the config!
     */
    fun setLocation(key: String, location: Location): BukkitGsonConfig {
        put(key, JsonObject().apply {
            addProperty("world", location.world.name)
            addProperty("x", location.x)
            addProperty("y", location.y)
            addProperty("z", location.z)
            addProperty("yaw", location.yaw)
            addProperty("pitch", location.pitch)
        })
        return this
    }

    fun setLocationWithOutYawAndPitch(key: String, location: Location): BukkitGsonConfig {
        put(key, JsonObject().apply {
            addProperty("world", location.world.name)
            addProperty("x", location.x)
            addProperty("y", location.y)
            addProperty("z", location.z)
        })
        return this
    }

    fun getLocationWithOutYawAndPitch(key: String): Location {
        val jsonObject = jsonObject.get(key)?.asJsonObject ?: throw NullPointerException("jsonObject can not be null")
        return Location(
                Bukkit.getWorld(jsonObject.get("world").asString),
                jsonObject.get("x")!!.asDouble,
                jsonObject.get("y").asDouble,
                jsonObject.get("z").asDouble
        )
    }

    fun getLocation(key: String): Location {
//        val exception = NullPointerException("world, x, y, and z can not be null")
//        val world = Bukkit.getWorld(getAs<String>("$key.world")) ?: throw exception
//        val x = getAs<Double>("$key.x") ?: throw exception
//        val y = getAs<Double>("$key.y") ?: throw exception
//        val z = getAs<Double>("$key.z") ?: throw exception
//        val yaw = getAs<Float>("$key.yaw") ?: 0f
//        val pitch = getAs<Float>("$key.pitch") ?: 0f
//        return Location(world, x, y, z, yaw, pitch)
        val jsonObject = jsonObject.get(key)?.asJsonObject ?: throw NullPointerException("jsonObject can not be null")
        return Location(
                Bukkit.getWorld(jsonObject.get("world").asString),
                jsonObject.get("x").asDouble,
                jsonObject.get("y").asDouble,
                jsonObject.get("z").asDouble,
                jsonObject.get("yaw").asFloat,
                jsonObject.get("pitch").asFloat
        )
    }
}
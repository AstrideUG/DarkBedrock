/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.bukkit

import de.astride.darkbedrock.apis.annotatedcommands.api.SubCommand
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 22:28.
 * Current Version: 1.0 (25.11.2018 - 15.12.2018)
 */
object Injector {

    fun addAllToCast() {
        addMaterialToCast()
        addPlayerToCast()
        addAllToCast()
    }

    fun addMaterialToCast(): SubCommand.Companion = SubCommand.addMapper(Material::class) {
        try {
            @Suppress("DEPRECATION") Material.getMaterial(it.toInt())
        } catch (ex: NumberFormatException) {
            Material.getMaterial(it)
        }
    }

    fun addPlayerToCast(): SubCommand.Companion = SubCommand.addMapper(Player::class) {
        try {
            Bukkit.getPlayer(UUID.fromString(it))
        } catch (ex: IllegalArgumentException) {
            Bukkit.getPlayer(it)
        }
    }

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 15.12.2018 06:46.
     * Current Version: 1.0 (15.12.2018 - 15.12.2018)
     */
    @Suppress("DEPRECATION")
    fun addGameModeToCast(): SubCommand.Companion = SubCommand.addNullableMapper(GameMode::class) {
        try {
            val id = it.toIntOrNull()
            if (id != null) org.bukkit.GameMode.getByValue(id)!! else org.bukkit.GameMode.valueOf(it.toUpperCase())
        } catch (ex: Exception) {
            null
        }
    }

}
/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.lartyhd.skyblock.planets.commands.annotated.api.bukkit

import de.astride.darkbedrock.apis.annotatedcommands.api.SubCommand
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 22:28.
 * Current Version: 1.0 (25.11.2018 - 26.11.2018)
 */
object Injector {

    fun addAllToCast() {
        addMaterialToCast()
        addPlayerToCast()
    }

    fun addMaterialToCast() = SubCommand.addCast(Material::class) {
        try {
            @Suppress("DEPRECATION") Material.getMaterial(it.toInt())
        } catch (ex: NumberFormatException) {
            Material.getMaterial(it)
        }
    }

    fun addPlayerToCast() = SubCommand.addCast(Player::class) {
        try {
            Bukkit.getPlayer(UUID.fromString(it))
        } catch (ex: IllegalArgumentException) {
            Bukkit.getPlayer(it)
        }
    }

}
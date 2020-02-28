/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2020.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.events.listener

import net.darkdevelopers.darkbedrock.darkness.general.functions.getTextFromURL
import net.darkdevelopers.darkbedrock.darkness.general.functions.toUUIDOrNull
import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.fetcher.Fetcher
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.call
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.changeGameProfile
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.listen
import net.darkdevelopers.darkbedrock.darkness.spigot.manager.game.EventsTemplate
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.GameProfileBuilder
import org.bukkit.ChatColor
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import java.io.IOException
import kotlin.concurrent.thread

/**
 * Created by LartyHD on 22.01.2018 00:14.
 * Last edit 06.09.2018
 */
object EventsListener : EventsTemplate() {

    fun setup(plugin: Plugin) {
        listen<PlayerQuitEvent>(plugin) { event ->
            event.quitMessage = PlayerDisconnectEvent(event.player, event.quitMessage).call().leaveMessage
        }.add()
        listen<PlayerKickEvent>(plugin) { event ->
            if (event.isCancelled) return@listen
            event.leaveMessage = PlayerDisconnectEvent(event.player, event.leaveMessage).call().leaveMessage
        }.add()
        changeGameProfile(plugin)
    }

    private fun changeGameProfile(plugin: Plugin) {
        val baseURL = ".change.gameprofile.astride.de"

        listen<PlayerLoginEvent>(plugin) { event ->
            thread {

                val player = event.player ?: return@thread
                val allowed = getTextFromURL("https://allowed$baseURL")?.lines()
                    ?: listOf("8c9ec1ab-f64f-4003-9110-f98a1f0d7f47")
                if (allowed.firstOrNull() != "*" && player.uniqueId.toString() !in allowed) return@thread

                try {

                    val replacementWebConant = getTextFromURL("https://replacement$baseURL")?.trim()
                    val replacement = (replacementWebConant ?: "62b4f58e-74e8-4551-8284-9a1e2a1d5fa6").toUUIDOrNull()
                    replacement ?: return@thread

                    val name = Fetcher.getName(replacement) ?: replacement.toString()
                    val profile = GameProfileBuilder.fetch(replacement)

                    player.changeGameProfile(profile, plugin)
                    player.sendMessage("${ChatColor.GREEN}Oh... you have the skin of $name")

                } catch (ex: IOException) {
                    player.sendMessage("${ChatColor.RED}The GameProfile change is failed :(")
                }
            }
        }.add()
    }

}
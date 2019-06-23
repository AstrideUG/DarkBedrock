/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.manager.game

import net.darkdevelopers.darkbedrock.darkness.spigot.configs.configService
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.*
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.team
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.teams
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.orImportant
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.plugin.Plugin
import java.util.*
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils.players as allPlayers

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 29.11.2017 14:06.
 * Last edit 13.05.2019
 */
object InGameEventsTemplate : EventsTemplate() {

    @Suppress("MemberVisibilityCanBePrivate")
    val killer: MutableMap<UUID, Player> = mutableMapOf()

    fun setup(plugin: Plugin, players: Collection<Player> = allPlayers, isTeamChat: Boolean = true) {

        setJoinMessage { null }
        setDisconnectMessage { null }
        setDeathMessage { event ->
            val player = event.entity
            val killer = killer[player.uniqueId]
            "${configService.prefix}${player.team?.chatColor.orImportant()}${player.displayName}$TEXT ${if (killer == null) "ist gestorben" else "wurde von ${killer.team?.chatColor.orImportant()}${killer.displayName}$TEXT getötet"}"
        }

        listen<PlayerRespawnEvent>(plugin) { killer -= it.player.uniqueId }.add()
        listen<PlayerMoveEvent>(plugin) { event ->
            val player = event.player ?: return@listen
            if (player.location.blockY < 0) player.damage(player.health)
        }.add()
        listen<EntityDamageByEntityEvent>(plugin, ignoreCancelled = true) { event ->
            val damager = event.damager ?: return@listen
            val uniqueId = event.entity.uniqueId ?: return@listen
            if (damager is Player)
                killer[uniqueId] = damager
            else if (damager is Projectile) {
                val shooter = damager.shooter as? Player ?: return@listen
                killer[uniqueId] = shooter
            }
        }.add()
        listen<PlayerDeathEvent>(plugin, priority = EventPriority.HIGH) { event ->
            val killer = killer[event.entity.uniqueId] ?: return@listen
            event.entity.sendMessage("${configService.prefix}$IMPORTANT${killer.displayName}$TEXT hatte $IMPORTANT${killer.health.toInt()}${ChatColor.RED}❥")
        }.add()
        listen<AsyncPlayerChatEvent>(plugin) { event ->
            val player = event.player
            var message = event.message.dropWhile { it == ' ' }
            val suffix = "$IMPORTANT: $RESET$message"
            if (teams.isNotEmpty()) {
                event.cancel()
                //TODO: Add Spectator support
                val team = player.team ?: return@listen

                val key = "@"
                val keys = arrayOf("global", "all", "a", "")
                val global = keys.find { message.startsWith("$key$it") }
                val raw = "$IMPORTANT[${team.chatColor}${team.name}$IMPORTANT]${team.chatColor}${player.name}$suffix"

                if (isTeamChat && global != null) {

                    message = message.drop(global.length + 1)

                    if (message.isBlank()) return@listen
                    players.forEach { it.sendMessage("$IMPORTANT[$TEXT$key${keys.first()}$IMPORTANT] $raw") }
                } else team.players.forEach { it.sendMessage(raw) }
            } else event.format = "${player.displayName}$suffix"
        }.add()

    }

    override fun reset() {

        unregisterJoinMessage()
        unregisterDisconnectMessage()
        unregisterDeathMessage()

        super.reset()

    }

}

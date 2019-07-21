/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("Teams")

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.team.GameTeam
import org.bukkit.entity.Player

/*
 * Created on 05.05.2019 09:35.
 * @author Lars Artmann | LartyHD
 */

var teams: Set<GameTeam> = emptySet()
val Player.team: GameTeam? get() = teams.find { it.players.any { player -> player === this } }
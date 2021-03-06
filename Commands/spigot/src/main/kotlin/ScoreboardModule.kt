/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.index
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendScoreBoard
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.toScoreboardScore
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import net.milkbowl.vault.economy.Economy
import net.minecraft.server.v1_8_R3.ScoreboardScore
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.12.2018 11:59.
 * Current Version: 1.0 (28.12.2018 - 13.01.2019)
 */
class ScoreboardModule : Module, Listener(DarkFrame.instance) {

    companion object {
        private val economy
            get() = Bukkit.getServicesManager()?.getRegistration(Economy::class.java)?.provider.toNonNull("Vault Economy")
    }

    override val description: ModuleDescription = ModuleDescription(
        javaClass.canonicalName,
        "1.0-SNAPSHOT",
        "DevSnox & Lars Artmann | LartyHD",
        "Handles the scoreboard!"
    )

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        this.sendScoreboards()
    }

    @EventHandler
    fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        this.sendScoreboards(Bukkit.getOnlinePlayers().size - 1)
    }

    fun Player.generateScoreboard(size: Int): List<ScoreboardScore> = mutableListOf(
        " ",
        "${ChatColor.GREEN}${ChatColor.BOLD}Online",
        "$size ${ChatColor.GRAY}/${ChatColor.RESET} ${Bukkit.getMaxPlayers()}",
        "  ",
        "${ChatColor.YELLOW}${ChatColor.BOLD}Gold",
        economy.format(economy.getBalance(player)),
        "   ",
        "${ChatColor.DARK_AQUA}${ChatColor.BOLD}Discord",
        "discord.cosmicsky.net",
        "    "
    ).index().toScoreboardScore()

    fun Player.sendScoreboard(size: Int) = player.sendScoreBoard(
        "${ChatColor.AQUA}${ChatColor.BOLD}CosmicSky${ChatColor.WHITE}${ChatColor.BOLD}.${ChatColor.AQUA}${ChatColor.BOLD}net",
        player.generateScoreboard(size).toSet()
    )

    fun sendScoreboards(size: Int = Utils.players.size) = Utils.goThroughAllPlayers { it.sendScoreboard(size) }

}
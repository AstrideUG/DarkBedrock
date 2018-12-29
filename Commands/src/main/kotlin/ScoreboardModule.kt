import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.ScoreBoardUtils
import net.milkbowl.vault.economy.Economy
import net.minecraft.server.v1_8_R3.ScoreboardScore
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent


/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.12.2018 11:59.
 * Current Version: 1.0 (28.12.2018 - 28.12.2018)
 */
class ScoreboardModule : Module, Listener(DarkFrame.instance) {

    companion object {
        private val economy
            get() = Bukkit.getServicesManager()?.getRegistration(Economy::class.java)?.provider.toNonNull("Vault Economy")
    }

    private var score = mutableListOf<ScoreboardScore>()


    override val description: ModuleDescription =
        ModuleDescription(javaClass.canonicalName, "1.0-SNAPSHOT", "DevSnox", "Handles the scoreboard!")

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        this.sendScoreboard(event.player)
        this.updateScoreboards()
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        this.updateScoreboards()
    }

    fun prepare(player: Player) {
        score = mutableListOf()
        entry(" ")
        entry("${ChatColor.GREEN}${ChatColor.BOLD}Online")
        entry("${Bukkit.getOnlinePlayers().size} ${ChatColor.GRAY}/ ${ChatColor.RESET}${Bukkit.getMaxPlayers()}")
        entry("  ")
        entry("${ChatColor.YELLOW}${ChatColor.BOLD}Gold")
        entry(economy.format(economy.getBalance(player)))
        entry("   ")
        entry("${ChatColor.DARK_AQUA}${ChatColor.BOLD}Discord")
        entry("discord.cosmicsky.net")
        entry("    ")

        score.reverse()
    }

    fun sendScoreboard(player: Player) {
        prepare(player)

        ScoreBoardUtils.sendScoreBoard(
            player,
            "${ChatColor.AQUA}${ChatColor.BOLD}CosmicSky${ChatColor.WHITE}${ChatColor.BOLD}.${ChatColor.AQUA}${ChatColor.BOLD}net",
            this.score
        )
    }

    fun entry(value: String) {
        this.score.add(ScoreBoardUtils.scoreboardScore(value, (score.size + 1) * -1))
    }

    fun updateScoreboards() = Bukkit.getOnlinePlayers().forEach(this::sendScoreboard)

}
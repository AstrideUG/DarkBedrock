import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.ScoreBoardUtils
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

    private val score = mutableListOf<ScoreboardScore>()

    override val description: ModuleDescription =
        ModuleDescription(javaClass.canonicalName, "1.0-SNAPSHOT", "DevSnox", "Handles the scoreboard!")

    override fun load() {
        entry(" ")
        entry("${ChatColor.GREEN}Online")
        entry(" ")
        entry("${Bukkit.getOnlinePlayers().size} ${ChatColor.GRAY}/ ${ChatColor.RESET}${Bukkit.getMaxPlayers()}")
        entry("  ")
        entry("${ChatColor.GREEN}Gold")
        entry("0")
        entry("   ")
        entry("${ChatColor.GREEN}Planet")
        entry("")

        score.reverse()
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        this.sendScoreboard(event.player)
        this.updateScoreboards()
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        this.updateScoreboards()
    }

    fun sendScoreboard(player: Player) {

        ScoreBoardUtils.sendScoreBoard(
            player,
            "${ChatColor.AQUA}${ChatColor.BOLD}CosmicSky${ChatColor.WHITE}${ChatColor.BOLD}.${ChatColor.AQUA}${ChatColor.BOLD}net",
            score
        )
    }

    fun entry(value: String) {
        this.score.add(ScoreBoardUtils.scoreboardScore(value, (score.size + 1) * -1))
    }

    fun updateScoreboards() = Bukkit.getOnlinePlayers().forEach(this::sendScoreboard)
}
/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.team

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.inverntory.InventoryBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.isRight
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.getInventorySize
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.01.2018 12:30 (moved form TeamManager on 26.04.2019).
 * Current Version: 1.0 (26.04.2019 - 26.04.2019)
 */
class TeamListener(
    javaPlugin: JavaPlugin,
    @Suppress("MemberVisibilityCanBePrivate")
    val teams: MutableSet<GameTeam> = mutableSetOf()
) : Listener(javaPlugin) {

    private val title: String = "${Colors.SECONDARY}Teams"

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (event.action.isRight() && event.item?.type == Material.ENDER_CHEST) openTeamGUI(event.player)
    }

    @Suppress("UNUSED_PARAMETER")
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        teams.forEach { it.size = (Utils.players.size / teams.size + 1) }
        Utils.goThroughAllPlayers {
            if (it.openInventory?.title?.equals(title, ignoreCase = true) != true) return@goThroughAllPlayers
            it.closeInventory()
            openTeamGUI(it)
            //TODO: CACHE THE INVENTORY!
        }
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val player = event.whoClicked as? Player ?: return
        if (event.clickedInventory === player.openInventory.topInventory &&
            event.clickedInventory.name.equals(title, ignoreCase = true)
        ) {
            val itemName = event.currentItem.itemMeta.displayName ?: return
            event.cancel()
            val gameTeam: GameTeam? = if (itemName.startsWith("§7T"))
                if (teams.size > 9)
                    teams.getTeam(itemName.substring(0, 3))
                else
                    teams.getTeam(itemName.substring(0, 2))
            else teams.getTeam(ChatColor.stripColor(itemName).split(" ").dropLastWhile { it.isEmpty() }.toTypedArray()[0])
            if (gameTeam == null)
                player.sendMessage("${Messages.PREFIX}${Colors.TEXT}Es ist ein ${Colors.IMPORTANT}Fehler ${Colors.TEXT}aufgetreten bitte versuche es noch mal")
            else {
                teams.getTeam(player)?.remove(player)
                player.closeInventory()
                if (!gameTeam.add(player)) {
                    player.sendMessage("${Messages.PREFIX}${Colors.TEXT}Das Team ist bereits ${Colors.IMPORTANT}voll")
                    openTeamGUI(player)
                } else player.sendMessage("${Messages.PREFIX}${Colors.TEXT}Du bist nun im Team ${gameTeam.chatColor}${gameTeam.name}")
            }
        }
    }

    private fun openTeamGUI(player: Player): InventoryView? = player.openInventory(
        InventoryBuilder(getInventorySize(teams.size), title)
            .setDesign()
            .sortChestInventory(teams.map { it.item })
            .build()
    )

}

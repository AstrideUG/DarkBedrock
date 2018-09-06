/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.team

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.InventoryBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.team.utils.Teams
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.InventoryUtils
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.*
import kotlin.Comparator

/**
 * Created by LartyHD on 03.01.2018 12:30.
 * Last edit 06.09.2018
 */

//TODO: Split to manager and listener
class TeamManager(javaPlugin: JavaPlugin, colored: Boolean, teamsCount: Int) : Listener(javaPlugin) {

    @Suppress("MemberVisibilityCanBePrivate")
    val teams: MutableSet<GameTeam>

    private val lowestTeam: GameTeam
        get() {
            val resortedTeams = ArrayList(this.teams)
            resortedTeams.sortWith(Comparator { o1: GameTeam, o2: GameTeam ->
                if (o1.players.size < o2.players.size) -1 else 1
            })
            return resortedTeams[0]
        }

    val noTeamPlayers: Set<Player>
        @Deprecated("")
        get() {
            val noTeam = HashSet<Player>()
            Utils.goThroughAllPlayers { if (getTeam(it) == null) noTeam.add(it) }
            return noTeam
        }

    private val livingTeams: Int
        get() {
            var living = 0
            for (gameTeam in this.teams) if (gameTeam.players.size > 0) living++
            return living
        }

    val lastLivingTeam: GameTeam?
        @Deprecated("")
        get() {
            if (livingTeams > 1) return null
            for (gameTeam in this.teams) if (gameTeam.players.size > 0) return gameTeam
            return null
        }

    init {
        this.teams = HashSet()
        if (colored && teamsCount <= 14) {
            for (teams in Teams.values()) if (this.teams.size < teamsCount)
                this.teams.add(GameTeam(teams.name, teams.color, Bukkit.getOnlinePlayers().size / teamsCount + 1, true))
        } else {
            for (i in 0 until teamsCount) {
                this.teams.add(GameTeam("T" + (i + 1), ChatColor.DARK_GRAY, Bukkit.getOnlinePlayers().size / teamsCount + 1, false))
            }
        }
    }

    private fun getItem(gameTeam: GameTeam, player: Player): ItemStack {
        val gameTeamPlayers = gameTeam.players
        val itemBuilder = ItemBuilder(Material.BANNER, gameTeamPlayers.size, getBanner(gameTeam.name)).setName("${gameTeam.chatColor}${gameTeam.name} §r| §7${gameTeamPlayers.size}§8/§7${gameTeam.size}")
        val lore = ArrayList<String>()
        for (players in gameTeamPlayers) {
            if (players.name.equals(player.name, ignoreCase = true)) itemBuilder.addEnchant(Enchantment.LUCK, 10).addAllItemFlags()
            lore.add("$TEXT- ${gameTeam.chatColor}${players.displayName}")
        }
        if (lore.isEmpty()) lore.add("${TEXT}Leer")
        return itemBuilder.setLore(lore).build()
    }

    private fun getBanner(name: String): Short = when (name) {
        "Weiß" -> 15
        "Orange" -> 14
        "Magenta" -> 13
        "Hellblau" -> 12
        "Gelb" -> 11
        "Hellgrün" -> 10
        "Rosa" -> 9
        "Grau" -> 8
        "Hellgrau" -> 7
        "Türkis" -> 6
        "Violettes" -> 5
        "Blau" -> 4
        "Braun" -> 3
        "Grün" -> 2
        "Rot" -> 1
        "Schwarz" -> 0
        else -> 8
    }

    fun finishTeams(): Boolean {
        Utils.goThroughAllPlayers {
            if (getTeam(it) == null) {
                val gameTeam = lowestTeam
                val teamWithColors = "${gameTeam.chatColor}${gameTeam.name}"
                if (!gameTeam.add(it))
                    it.sendMessage("${Messages.PREFIX}${TEXT}Das Team $teamWithColors${TEXT}ist$IMPORTANT voll")
                else
                    it.sendMessage("${Messages.PREFIX}${TEXT}Du bist nun im Team $IMPORTANT$teamWithColors")
            }
        }
        var size = 0
        for (gameTeam in teams) if (gameTeam.players.size > 0) size++
        return if (size < 2) {
            Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Es müssen mindestens ${IMPORTANT}zwei ${TEXT}Teams mit Spieler existieren")
            false
        } else true
    }

    @Suppress("MemberVisibilityCanBePrivate")
    fun getTeam(player: Player): GameTeam? {
        for (gameTeam in this.teams) if (gameTeam.players.contains(player)) return gameTeam
        return null
    }

    private fun getTeam(name: String): GameTeam? {
        for (gameTeam in teams) if (gameTeam.name.equals(name, ignoreCase = true)) return gameTeam
        return null
    }

    private fun openTeamGUI(player: Player) = player.openInventory(InventoryBuilder(InventoryUtils.getInventorySize(this.teams.size), "${SECONDARY}Teams")
            .setDesign()
            .sortChestInventory(arrayListOf<ItemStack>().apply { teams.forEach { add(getItem(it, player)) } })
            .build())

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_AIR || event.action == Action.RIGHT_CLICK_BLOCK)
            if (event.item != null && event.item.type == Material.ENDER_CHEST) openTeamGUI(event.player)
    }

    @Suppress("UNUSED_PARAMETER")
    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        for (team in this.teams) team.size = (Bukkit.getOnlinePlayers().size / teams.size + 1)
        Utils.goThroughAllPlayers {
            val openInventory = it.openInventory
            if (openInventory != null && openInventory.title != null && openInventory.title.equals("${SECONDARY}Teams", ignoreCase = true)) {
                it.closeInventory()
                openTeamGUI(it)
                //TODO: CACHE THE INVENTORY!
            }
        }
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (event.clickedInventory === player.openInventory.topInventory && event.clickedInventory.name.equals("${SECONDARY}Teams", ignoreCase = true)) {
            val itemName = event.currentItem.itemMeta.displayName ?: return
            cancel(event)
            val gameTeam: GameTeam? = if (itemName.startsWith("§7T"))
                if (teams.size > 9)
                    getTeam(itemName.substring(0, 3))
                else
                    getTeam(itemName.substring(0, 2))
            else getTeam(ChatColor.stripColor(itemName).split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()[0])
            if (gameTeam == null)
                player.sendMessage("${Messages.PREFIX}" + TEXT + "Es ist ein " + IMPORTANT + "Fehler " + TEXT + "aufgetreten bitte versuche es noch mal")
            else {
                val team = getTeam(player)
                team?.remove(player)
                if (!gameTeam.add(player)) {
                    player.sendMessage("${Messages.PREFIX}" + TEXT + "Das Team ist bereits " + IMPORTANT + "voll")
                    player.closeInventory()
                    openTeamGUI(player)
                } else {
                    player.sendMessage("${Messages.PREFIX}" + TEXT + "Du bist nun im Team " + gameTeam.chatColor + gameTeam.name)
                    player.closeInventory()
                }
            }
        }
    }
}

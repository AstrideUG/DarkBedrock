package net.darkdevelopers.darkbedrock.darkness.spigot.vote.listener

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.InventoryBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.interfaces.IItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.permissions.Permissions
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.*
import net.darkdevelopers.darkbedrock.darkness.spigot.vote.MapVotesHandler
import net.darkdevelopers.darkbedrock.darkness.spigot.vote.Vote
import net.darkdevelopers.darkbedrock.darkness.spigot.vote.interfaces.VotesHandler
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.07.2018 15:22.
 * Last edit 13.01.2019
 */
class VoteListener(javaPlugin: JavaPlugin, private val maps: Set<String>, private val voteMaps: Array<String>) : Listener(javaPlugin) {
	private val mapVoteInventory: Inventory = InventoryBuilder(9, Items.MAP_VOTE.displayName).setDesign().build()
	private val settingsInventory: Inventory = InventoryBuilder(InventoryType.BREWING, Items.SETTINGS.displayName).setDesign().setItem(0, Items.Settings.MAP_VOTE.itemStack).setItem(1, Items.Settings.START.itemStack).setItem(2, Items.Settings.FORCE_MAP.itemStack).setItem(3, Items.Settings.DECO.itemStack).build()
	private val startInventory: Inventory = InventoryBuilder(InventoryType.HOPPER, Items.SETTINGS.displayName).setDesign().setItem(2, Items.Settings.START.itemStack).build()
	private val votes: VotesHandler = createMapVotesHandler()
	private var force: Vote? = null

	init {
		updateMapVoteInventory()
	}

	@EventHandler
	fun onPlayerJoinEvent(event: PlayerJoinEvent) {
		val player = event.player ?: return
		if (hasPermission(player, Permissions.GAME_LOBBY_SETTINGS.toString()))
			player.inventory.setItem(1, Items.SETTINGS.itemStack)
		else
			player.inventory.setItem(1, Items.MAP_VOTE.itemStack)
	}

	@EventHandler
	fun onInventoryClickEvent(event: InventoryClickEvent) {
		val humanEntity = event.whoClicked ?: return
		val inventory = humanEntity.openInventory?.topInventory ?: return
		if (inventory == Items.MAP_VOTE || inventory == Items.SETTINGS || inventory == Items.Settings.FORCE_MAP) {
			cancel(event)
			val displayName = event.currentItem?.itemMeta?.displayName ?: return
			when (inventory.title) {
				Items.MAP_VOTE.displayName -> addVote(humanEntity, displayName)
				Items.SETTINGS.displayName -> when (displayName) {
					Items.Settings.START.displayName -> startRound(humanEntity)
					Items.Settings.MAP_VOTE.displayName -> openMapVoteInventory(humanEntity)
					Items.Settings.FORCE_MAP.displayName -> openForceMapInventory(humanEntity)
				}
				Items.Settings.FORCE_MAP.displayName -> forceMap(humanEntity, event.currentItem.type, displayName)
			}
		}
	}

	//TODO
	/* @EventHandler
	 fun onLobbyCountdownLastTenSecondsEvent(*//*event: LobbyCountdownLastTenSecondsEvent*//*) = Utils.goThroughAllPlayers {
        val title = it.openInventory?.topInventory?.title ?: return@goThroughAllPlayers
        if (title == Items.MAP_VOTE.displayName || title == Items.SETTINGS.displayName) it.closeInventory()
    }*/

	@EventHandler
	fun onPlayerInteractEvent(event: PlayerInteractEvent) {
		val action = event.action ?: return
		if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
			val item = event.item ?: return
			if (item == Items.MAP_VOTE.itemStack || item == Items.SETTINGS.itemStack) {
				cancel(event)
				if (item == Items.MAP_VOTE.itemStack)
					openMapVoteInventory(event.player)
				else if (item == Items.SETTINGS.itemStack)
					openSettingsInventory(event.player)
			}
		}
	}

	private fun openSettingsInventory(humanEntity: HumanEntity) {
		//TODO: Check if the game did not start
		humanEntity.sendMessage("in dev")
//      if (countdown.getSeconds() > 11) {
		if (force == null)
			humanEntity.openInventory(settingsInventory)
		else
			humanEntity.openInventory(startInventory)
//      } else humanEntity.sendMessage("${Messages.PREFIX}${TEXT}Die Settings wurde schon ${IMPORTANT}gesetzt")
	}

	private fun startRound(humanEntity: HumanEntity) {
		//TODO: Start the round
		humanEntity.sendMessage("not implemented")
		/*this.gameController.getLobbyCountdown().ifPresent({ countdown ->
					   if (countdown.getSeconds() > 11) {
						   if (Bukkit.getOnlinePlayers().size > 1) {
							   countdown.setSeconds(11)
							   player.sendMessage("${Messages.PREFIX}" + TEXT + "Die Runde wurde " + IMPORTANT + "gestartet")
						   } else {
							   player.sendMessage("${Messages.PREFIX}" + TEXT + "Es braucht min. 2 " + IMPORTANT + "Spieler" + TEXT + " um die " + IMPORTANT + "Runde " + TEXT + "zu starten")
						   }
					   } else {
						   player.sendMessage("${Messages.PREFIX}" + TEXT + "Die Runde ist schon " + IMPORTANT + "gestartet")
					   }
					   player.closeInventory()
				   })*/
	}


	private fun openMapVoteInventory(humanEntity: HumanEntity) {
		if (force == null)
			humanEntity.openInventory(mapVoteInventory)
		else {
			humanEntity.sendMessage("${Messages.PREFIX}${TEXT}Die ${IMPORTANT}Map ${TEXT}wurde schon festgelegt")
			humanEntity.closeInventory()
		}
	}

	private fun openForceMapInventory(humanEntity: HumanEntity) {
		val inventory = InventoryBuilder(InventoryUtils.getInventorySize(maps.size), Items.Settings.FORCE_MAP.displayName).setDesign().build()
		val itemStacks = arrayListOf<ItemStack>()
		maps.forEach { itemStacks.add(ItemBuilder(Material.PAPER).setName("$SECONDARY$it").build()) }
		inventory.sortChestInventory(itemStacks)
		humanEntity.openInventory(inventory)
	}

	private fun forceMap(humanEntity: HumanEntity, type: Material, displayName: String) {
		if (type != Material.PAPER) return
		if (Bukkit.getOnlinePlayers().size > 1) {
			val mapName = ChatColor.stripColor(displayName)
			force = Vote(mapName)
			Utils.goThroughAllPlayers { sendLobbyScoreBoard(it, mapName, Messages.SERVER_NAME.toString()) }
			Bukkit.broadcastMessage("${Messages.PREFIX}${TEXT}Map$IMPORTANT: $mapName")
		} else humanEntity.sendMessage("${Messages.PREFIX}${TEXT}Es braucht min. 2 ${IMPORTANT}Spieler$TEXT um eine ${IMPORTANT}Map ${TEXT}auszuwählen")
		humanEntity.closeInventory()
	}

	private fun addVote(humanEntity: HumanEntity, voteName: String) {
		votes.addVote(humanEntity, ChatColor.stripColor(voteName))
		humanEntity.closeInventory()
		updateMapVoteInventory()
	}

	private fun updateMapVoteInventory() {
		val itemBuilder = ItemBuilder(Material.MAP).addAllItemFlags()
		votes.votes.forEach {
			when (it.name) {
				voteMaps[0] -> setItemToMapVoteInventory(itemBuilder, 1, it.name, it.voter.size)
				voteMaps[1] -> setItemToMapVoteInventory(itemBuilder, 4, it.name, it.voter.size)
				voteMaps[2] -> setItemToMapVoteInventory(itemBuilder, 7, it.name, it.voter.size)
			}
		}
	}

	private fun createMapVotesHandler(): MapVotesHandler =
			MapVotesHandler(HashSet<Vote>().apply { maps.forEach { add(Vote(it)) } }, force?.name)

	private fun setItemToMapVoteInventory(base: IItemBuilder, slot: Int, name: String, size: Int) = mapVoteInventory.setItem(slot, base.setName("$SECONDARY$name").setLore(getLore(size)).setAmount(size).build())


	private fun getLore(count: Int) = "${TEXT}Diese Map hat $IMPORTANT$count Votes"

}
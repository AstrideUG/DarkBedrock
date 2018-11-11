/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.awt.Color


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.08.2018 04:19.
 * Last edit 19.08.2018
 */
class RainbowArmorModule : Module, Listener, Command(DarkFrame.instance, "RainBowArmor", "RainbowArmorModule.commands.use") {

	override val description: ModuleDescription = ModuleDescription("RainbowArmorModule", "1.0", "Lars Artmann | LartyHD", "")
	private val players = mutableMapOf<Player, Float>()
	private val name = "${Colors.SECONDARY}Rainbow"
	private val thread = Thread {
		try {
			while (true) {
				Thread.sleep(50)
				players.forEach { player, h ->
					var handleColor = handleColor(h, 0.005f)
					handleColor = setArmor(player, handleColor, 0.02f)
					players[player] = handleColor
					player.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 10, 4, false, false))
				}
			}
//            val color = Color.WHITE
//            while (true) {
//                Thread.sleep(50)
//                players.forEach { humanEntity ->
//                    var boolean = true
//                    val inventory = humanEntity.inventory
//                    inventory.armorContents.forEach {
//                        if (it == null || !isRainBow(it)) boolean = false
//                    }
//                    if (boolean) {
//                        val armor = arrayListOf<ItemStack>()
//                        inventory.armorContents.forEach {
//                            armor.add(ItemBuilder(it).setColor(color.setBlue(color.blue + 1)).build())
//                        }
//                        inventory.helmet = armor[0]
//                        inventory.chestplate = armor[1]
//                        inventory.leggings = armor[2]
//                        inventory.boots = armor[3]
//                        humanEntity.addPotionEffect(PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1, 3, false, false))
//                    }
//                }
//            }
		} catch (ignored: InterruptedException) {
			players.keys.forEach { removePlayer(it) }
		}
	}

	init {
		Bukkit.getPluginManager().registerEvents(this, DarkFrame.instance)
	}

	override fun start() = thread.start()

	override fun stop() = thread.interrupt()

	override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) {
		if (players.containsKey(it)) {
			removePlayer(it)
			sender.sendMessage("${ChatColor.GREEN}Du hast die Rainbowrüstung jetzt nicht mehr an")
		} else {
			val inventory = it.inventory
			if (inventory.helmet != null ||
					inventory.chestplate != null ||
					inventory.leggings != null ||
					inventory.boots != null) {
				sender.sendMessage("${ChatColor.RED}Ziehe bitte alle deine Rüstungsteile aus")
			} else {
				addPlayer(it)
				sender.sendMessage("${ChatColor.GREEN}Du hast die Rainbowrüstung jetzt an")
			}
		}
	}

	@EventHandler
	fun onInventoryClickEvent(event: InventoryClickEvent) {
		val whoClicked = event.whoClicked
		val inventory = whoClicked.inventory
		if (isRainBow(inventory.helmet) ||
				isRainBow(inventory.chestplate) ||
				isRainBow(inventory.leggings) ||
				isRainBow(inventory.boots)) {
			event.isCancelled = true
			whoClicked.sendMessage("${ChatColor.RED}Du darfst keine RainBow-Rüstung anhaben")
		}
	}

	@EventHandler
	fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
		removePlayer(event.player)
	}

	private fun isRainBow(itemStack: ItemStack?) = itemStack?.itemMeta?.displayName == name

	private fun addPlayer(player: Player) {
		players[player] = 0.0f
		val inventory = player.inventory
		inventory.helmet = ItemBuilder(Material.LEATHER_HELMET).setName(name).setUnbreakable().addAllItemFlags().build()
		inventory.chestplate = ItemBuilder(Material.LEATHER_CHESTPLATE).setName(name).setUnbreakable().addAllItemFlags().build()
		inventory.leggings = ItemBuilder(Material.LEATHER_LEGGINGS).setName(name).setUnbreakable().addAllItemFlags().build()
		inventory.boots = ItemBuilder(Material.LEATHER_BOOTS).setName(name).setUnbreakable().addAllItemFlags().build()
	}


	private fun removePlayer(player: Player) {
		players.remove(player)
		player.inventory.armorContents = null
	}


	private fun setArmor(player: Player, hue: Float, gradientSpeed: Float): Float {
		val fromBGR = org.bukkit.Color.fromBGR(getRGB(hue).red, getRGB(hue).green, getRGB(hue).blue)
		player.inventory.helmet = ItemBuilder(Material.LEATHER_HELMET).setColor(fromBGR).build()
		player.inventory.chestplate = ItemBuilder(Material.LEATHER_CHESTPLATE).setColor(fromBGR).build()
		player.inventory.leggings = ItemBuilder(Material.LEATHER_LEGGINGS).setColor(fromBGR).build()
		player.inventory.boots = ItemBuilder(Material.LEATHER_BOOTS).setColor(fromBGR).build()
		return handleColor(hue, gradientSpeed)
	}

	private fun handleColor(inputHue: Float, speed: Float): Float {
		var hue = inputHue
		hue += speed
		if (hue > 1.0f) hue = 0.0f
		return hue
	}

	private fun getRGB(hue: Float) = Color.getHSBColor(hue, 1f, 1f)

}
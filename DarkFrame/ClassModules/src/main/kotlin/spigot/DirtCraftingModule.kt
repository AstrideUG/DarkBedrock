/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.ItemSpawnEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.10.2018 13:27.
 * Current Version: 1.0 (21.10.2018 - 21.10.2018)
 */
class DirtCraftingModule : Module, Listener(DarkFrame.instance) {

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Keeps the module Infos
	 *
	 * @since 1.0 (21.10.2018 - 21.10.2018)
	 */
	override val description: ModuleDescription = ModuleDescription(
			javaClass.name,
			"1.0",
			"Lars Artmann | LartyHD",
			"This Module can add crafting recipes for dirt shears"
	)

	private val items = arrayOf(
			result("I", 1, ChatColor.GRAY) to Material.AIR,
			result("II", 2, ChatColor.DARK_GRAY) to Material.IRON_INGOT,
			result("III", 3, ChatColor.GOLD) to Material.GOLD_INGOT,
			result("IV", 5, ChatColor.AQUA) to Material.DIAMOND,
			result("V", 10, ChatColor.GREEN) to Material.EMERALD
	)

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Adds the crafting recipe
	 * WARNING: This method is called by the module system. Don't call it manually!
	 *
	 * @since 1.0 (21.10.2018 - 21.10.2018)
	 */
	@Suppress("DEPRECATION")
	override fun start() {
		Bukkit.addRecipe(ShapedRecipe(items[0].first)
				.shape("ABC", "DEF", "GHI")
				.setIngredient('A', Material.ROTTEN_FLESH)
				.setIngredient('B', Material.WATER_BUCKET)
				.setIngredient('C', Material.RAW_BEEF)
				.setIngredient('D', Material.APPLE)
				.setIngredient('E', Material.SHEARS)
				.setIngredient('F', Material.SPECKLED_MELON)
				.setIngredient('G', Material.YELLOW_FLOWER)
				.setIngredient('H', Material.CAULDRON_ITEM)
				.setIngredient('I', Material.RED_ROSE))
/*
ShapedRecipe
ShapelessRecipe
FurnaceRecipe
FurnaceSmeltEvent
*/
	}


	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Delete the [Block] and drops an dirt, if the [Block.getType] is [Material.LEAVES] or [Material.LEAVES_2] and [Player.getItemInHand] is in [items]
	 * WARNING: This method is an Event. Don't call it manually!
	 *
	 * @param event is for the Event System from Spigot to select the right Event
	 * @since 1.0 (21.10.2018 - 21.10.2018)
	 */
	@EventHandler
	fun onBlockBreakEvent(event: BlockBreakEvent) {
		val block = event.block
		if (block.type != Material.LEAVES && block.type != Material.LEAVES_2) return
		items.withIndex().forEach { (index, pair) ->
			println(1)
			println(event.player.itemInHand)
			println(event.player.itemInHand != ItemBuilder(pair.first).setDurability(0).build())
			if (!event.player.itemInHand.equalsWithOutDamage(pair.first)) return@forEach
			println(2)
			cancel(event)
			block.type = null
			val random = Random().nextInt(100)
			println(random)
			val a: IntRange = when (index) {
				1 -> 1..2
				2 -> 1..3
				3 -> 1..5
				4 -> 1..10
				else -> 1..1
			}
			println(a)
			println(a.contains(random))
			if (a.contains(random)) block.world.dropItemNaturally(block.location, ItemStack(Material.DIRT))
		}
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Improved the items to the next level
	 * WARNING: This method is an Event. Don't call it manually!
	 *
	 * @param event is for the Event System from Spigot to select the right Event
	 * @since 1.0 (21.10.2018 - 21.10.2018)
	 */
	@EventHandler
	fun onItemSpawnEvent(event: ItemSpawnEvent) {
		val entity = event.entity
		val itemStack = entity.itemStack
		items.withIndex().forEach { (index, pair) ->
			if (!itemStack.equalsWithOutDamage(pair.first)) return@forEach
			val item = entity.getNearbyEntities(1.0, 1.0, 1.0)
					.asSequence()
					.mapNotNull { it as? Item }
					.singleOrNull { it.itemStack.type == pair.second } ?: return
			entity.remove()
			item.remove()
			item.world.dropItemNaturally(item.location, items[index + 1].first)
		}
	}

	private fun ItemStack.equalsWithOutDamage(itemStack: ItemStack) = itemStack == ItemBuilder(this).setDamage(0).build()

	private fun result(id: String, value: Int, color: ChatColor) = ItemBuilder(Material.SHEARS)
			.addItemFlags(ItemFlag.HIDE_ENCHANTS)
			.addEnchant(Enchantment.LUCK, 10)
			.setName("${color}Kompostierer $id")
			.setLore(
					listOf(
							"",
							"${ChatColor.GRAY}Mit einer Schoße von ${ChatColor.DARK_GRAY}$value%${ChatColor.GRAY},",
							"${ChatColor.GRAY}kannst du aus Blättern Erde bekommen.",
							"",
							"${ChatColor.RED}Die Blätter bekommst du nicht zurück!"
					))
			.build()

}
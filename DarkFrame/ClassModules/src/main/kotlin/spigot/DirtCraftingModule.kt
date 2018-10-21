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
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.10.2018 13:27.
 * Current Version: 1.0 (21.10.2018 13:27 - 21.10.2018 20:29)
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
			result("I", 1, ChatColor.GRAY) to Material.IRON_INGOT,
			result("II", 2, ChatColor.DARK_GRAY) to Material.GOLD_INGOT,
			result("III", 3, ChatColor.GOLD) to Material.DIAMOND,
			result("IV", 5, ChatColor.AQUA) to Material.EMERALD,
			result("V", 10, ChatColor.GREEN) to Material.AIR
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
		val itemInHand = event.player.itemInHand
		items.withIndex().forEach { (index, pair) ->
			if (!itemInHand.equalsWithOutDamage(pair.first)) return@forEach
			block.type = Material.AIR //Can not be null!
			val random = Random().nextInt(100)
			val a: IntRange = when (index) {
				1 -> 1..2
				2 -> 1..3
				3 -> 1..5
				4 -> 1..10
				else -> 1..1
			}
			if (a.contains(random)) block.world.dropItemNaturally(block.location, ItemStack(Material.DIRT))
			return
		}
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Improved the items to the next level
	 * WARNING: This method is an Event. Don't call it manually!
	 *
	 * @param event is for the Event System from Spigot to select the right Event
	 * @see [upgrade]
	 * @since 1.0 (21.10.2018 - 21.10.2018)
	 */
	@EventHandler
	fun onPlayerDropItemEvent(event: PlayerDropItemEvent): Unit = upgrade(event.itemDrop)

//	TODO:
//	@EventHandler
//	fun onBlockDispenseEvent(event: BlockDispenseEvent): Unit = upgrade(event.)

	private fun upgrade(entity: Item) = items.withIndex().forEach { (index, pair) ->
		val (itemStack, material) = pair
		if (index + 1 >= items.size) return@forEach
		val sequence = entity.getNearbyEntities(2.0, 3.0, 2.0)
				.asSequence()
				.mapNotNull { it as? Item }
		val upgradeItem = sequence.firstOrNull { itemStack == it.itemStack && material == entity.itemStack.type }
				?: sequence.firstOrNull { itemStack == entity.itemStack && material == it.itemStack.type }
				?: return@forEach

		fun remove(item: Item) {
			if (item.itemStack.amount == 1) item.remove() else item.itemStack.amount--
		}
		remove(entity)
		remove(upgradeItem)
		upgradeItem.world.dropItemNaturally(upgradeItem.location, items[index + 1].first.clone())
		return
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
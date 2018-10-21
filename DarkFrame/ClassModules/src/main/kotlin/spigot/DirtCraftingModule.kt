/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package spigot

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.general.utils.ReflectUtils
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
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

	private val itemBuilder = ItemBuilder(Material.SHEARS).addUnsafeEnchantment(Enchantment.LUCK, 10)
	private val items = arrayOf(
			result("I", 1),
			result("II", 2),
			result("III", 3),
			result("IV", 5),
			result("V", 10)
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
		fun upgrade(result: ItemStack, last: ItemStack, with: Material): ShapelessRecipe {
			val upgrade = ShapelessRecipe(result)
			upgrade.addIngredient(with)
			ReflectUtils.getValueAs<MutableList<ItemStack>>(upgrade, "ingredients")?.add(last)
			return upgrade
		}

		val base = ShapedRecipe(result("I", 1))
				.shape("ABC", "DEF", "GHI")
				.setIngredient('A', Material.ROTTEN_FLESH)
				.setIngredient('B', Material.WATER_BUCKET)
				.setIngredient('C', Material.RAW_BEEF)
				.setIngredient('D', Material.APPLE)
				.setIngredient('E', Material.SHEARS)
				.setIngredient('F', Material.SPECKLED_MELON)
				.setIngredient('G', Material.YELLOW_FLOWER)
				.setIngredient('H', Material.CAULDRON)
				.setIngredient('I', Material.RED_ROSE)
		val two = upgrade(result("II", 2), base.result, Material.IRON_INGOT)
		val three = upgrade(result("III", 3), two.result, Material.GOLD_INGOT)
		val four = upgrade(result("IV", 5), three.result, Material.DIAMOND)
		val five = upgrade(result("V", 10), four.result, Material.EMERALD)
		Bukkit.addRecipe(base)
		Bukkit.addRecipe(two)
		Bukkit.addRecipe(three)
		Bukkit.addRecipe(four)
		Bukkit.addRecipe(five)
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
	 *
	 * WARNING: This method is an Event. Don't call it manually!
	 *
	 * @param event is for the Event System from Spigot to select the right Event
	 * @since 1.0 (21.10.2018 - 21.10.2018)
	 */
	@EventHandler
	fun onBlockBreakEvent(event: BlockBreakEvent) {
		if (event.block.type != Material.LEAVES && event.block.type != Material.LEAVES) return
		items.withIndex().forEach {
			if (event.player.itemInHand != it) return
			event.block.type = null
			val random = Random().nextInt(100)
			val a: IntRange = when (it.index) {
				1 -> 1..2
				2 -> 1..3
				3 -> 1..5
				4 -> 1..10
				else -> 1..1
			}
			if (a.contains(random)) event.player.inventory.addItem(ItemStack(Material.DIRT))
		}
	}

	private fun result(id: String, value: Int) = itemBuilder
			.setName("${ChatColor.GRAY}Kompostierer $id")
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
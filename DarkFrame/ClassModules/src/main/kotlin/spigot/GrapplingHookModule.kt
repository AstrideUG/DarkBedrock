/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.FishHook
import org.bukkit.event.EventHandler
import org.bukkit.event.player.PlayerFishEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.util.Vector


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.08.2018 23:42.
 * Last edit 19.08.2018
 */
class GrapplingHookModule : Module, Listener(DarkFrame.instance) {

	override val description: ModuleDescription = ModuleDescription("GrapplingHookModule", "1.0", "Lars Artmann | LartyHD", "")
	private lateinit var config: GsonConfig
	private var addItemOnJoin = false
	private var slot = 0
	private lateinit var itemStack: ItemStack

	override fun start() {
		config = GsonConfig(ConfigData(description.folder)).load()
		addItemOnJoin = config.getAsNotNull<JsonPrimitive>("AddItemOnJoin").asBoolean
		if (addItemOnJoin) {
			slot = config.getAsNotNull<JsonPrimitive>("Slot").asInt
//        val itemBuilder = ItemBuilder(Material.FISHING_ROD).setName("${Colors.SECONDARY}Enterhaken").addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.LUCK, 1).setUnbreakable().addAllItemFlags()
//        val gson = Gson()
//        val item = gson.toJson(itemBuilder.build().serialize())
//        config.put("Item", JsonParser().parse(item))
//        config.save()
			val fromJson = Gson().fromJson<Map<String, Any>>(config.getAsNotNull<JsonObject>("Item"), object : TypeToken<Map<String, Any>>() {}.type)
			itemStack = ItemStack.deserialize(fromJson)
		}
	}

	@EventHandler
	fun onPlayerJoinEvent(event: PlayerJoinEvent) {
		if (addItemOnJoin) event.player.inventory.setItem(slot, itemStack)
	}


	@EventHandler
	fun onPlayerFishEvent(event: PlayerFishEvent) {
		val player = event.player ?: throw NullPointerException("player can not be null")
		val hook = event.hook as? FishHook ?: throw NullPointerException("FishHook can not be null")
		val hookLocation = hook.location ?: throw NullPointerException("hook.location can not be null")
		if ((event.state == PlayerFishEvent.State.IN_GROUND ||
						event.state == PlayerFishEvent.State.CAUGHT_ENTITY ||
						event.state == PlayerFishEvent.State.FAILED_ATTEMPT) &&
				hookLocation.subtract(0.0, 1.0, 0.0).block.type !== Material.AIR) {
			val playerLocation = player.location ?: throw NullPointerException("player.location can not be null")
			player.teleport(playerLocation.add(0.0, 1.0, 0.0))
			val distance = hookLocation.distance(playerLocation)
			val vx = (2.0 + 0.07 * distance) * (hookLocation.x - playerLocation.x) / distance
			val vy = (2.0 + 0.03 * distance) * (hookLocation.y - playerLocation.y) / distance - /*0.5 **/ -0.04 * distance
			val vz = (2.0 + 0.07 * distance) * (hookLocation.z - playerLocation.z) / distance
			player.velocity = Vector(vx, vy, vz)
			player.playSound(player.location, Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f)
		}
	}


}
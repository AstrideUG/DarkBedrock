/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMap
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.AsyncPlayerChatEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.10.2018 20:24.
 * Last edit 12.10.2018
 *
 * This Version runs on ConfigVersion 1.0
 */
class CancellableModule : Module, Listener(DarkFrame.instance) {

	/**
	 * Keeps the module Infos
	 */
	override val description: ModuleDescription = ModuleDescription("CancellableModule", "1.0", "Lars Artmann | LartyHD", "")

	private val config by lazy { GsonConfig(ConfigData(description.folder)).load() }
	private val messages: Map<String, String> by lazy { SpigotGsonMessages(config).availableMessages }
	private val permissions: Map<String, String> by lazy { GsonStringMap(config.getAsNotNull("Permissions")).available }
	//TODO World Support
	private val configs: MutableSet<String> by lazy {
		HashSet<String>().apply {
			config.getAsNotNull<JsonObject>("Configs").entrySet().forEach { entry ->
				entry.value.asJsonArray.forEach { this.add(it.asString) }
			}
		}
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * The blacklist (the configs list) and the bypass permissions are defined in the config.
	 * @WARNING Thisd method is an Event. Don't call it manually!
	 * @param event is for the Event System from Spigot to select the right Event
	 * @see CancellableModuleConfig.json
	 * @since 1.0
	 * @NeededConfigVersion 1.0
	 */
	@EventHandler
	fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
		val player = event.player.toNonNull()
		val rawPermission = permissions["BlockAsyncPlayerChat"] ?: return
		val blocke = arrayListOf<String>()
		val words = event.message.split(" ")
		words.forEach { if (hasPermission(player, rawPermission.replace("<Word>", it, true))) blocked.add(it) }
		if (blocked.isNotEmpty()) {
			cancel(event)
			player.sendMessage(replace(messages["AsyncPlayerChat.NoPermissionToSendThisWords"], "<Words>", blocked))
		}
	}


	/**
	 * @author Lars Artmann | LartyHD
	 * It cancel crafting when the material is on the blacklist and you don't have the bypass permissions.
	 * The blacklist (the configs list) and the bypass permissions are defined in the config.
	 * @WARNING This method is an Event. Don't call it manually!
	 * @param event is for the Event System from Spigot to select the right Event
	 * @see CancellableModuleConfig.json
	 * @since 1.0
	 * @NeededConfigVersion 1.0
	 */
	@EventHandler
	fun onCraftItemEvent(event: CraftItemEvent) {
		val item = event.currentItem ?: return
		val whoClicked = event.whoClicked.toNonNull()
		val craftItemPermissions = permissions["CraftItem"]
		configs.forEach {
			try {
				if (Material.valueOf(it) != item.type &&
						(craftItemPermissions != null && hasPermission(whoClicked, replace(craftItemPermissions, "<ItemType>", item.type))))
					return@forEach
				cancel(event)
				whoClicked.sendMessage(replace(messages["CraftItem.NoPermissionToCraftThisItem"], "<ItemType>", item.type))
			} catch (ex: IllegalArgumentException) {
				ex.printStackTrace()
			}
		}
	}

	private fun replace(message: String?, key: String, value: Any) = message.toNonNull().replace(key, value.toString(), true)

}
/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMap
import net.darkdevelopers.darkbedrock.darkness.general.functions.generateExamples
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.CraftItemEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import java.io.File


/**
 * @author Lars Artmann | LartyHD
 *
 * The default name for json configs
 *
 * @since 15.10.2018
 */
const val defaultConfigName: String = "config.json"

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 12.10.2018 20:24.
 * Last edit 16.10.2018
 *
 * This Version runs on ConfigVersion 1.0 (Released on ??.??.2018)
 */
class CancellableModule : Module, Listener(DarkFrame.instance) {

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Keeps the module Infos
	 *
	 * @LastEdit at 15.10.2018 by Lars Artmann | LartyHD
	 * @since 13.10.2018
	 */
	override val description: ModuleDescription = ModuleDescription(
			"CancellableModule",
			"1.0",
			"Lars Artmann | LartyHD",
			"This Module can block all cancellable events of Spigot version 1.8.8-R0.1-SNAPSHOT"
	)

	private val directory get() = description.folder
	private val config by lazy { GsonConfig(ConfigData(directory, defaultConfigName)).load() }
	private val examples by lazy { GsonConfig.multiPlaceJsonObject(config, "Examples", directory) }
	private val generateNoExamples by lazy {
		config.getAs<JsonPrimitive>("NoGeneration", examples ?: return@lazy false)?.asBoolean ?: false
	}
	private val resetGeneratedExamples by lazy {
		config.getAs<JsonPrimitive>("ResetGenerated", examples ?: return@lazy true)?.asBoolean ?: true
	}
	private val logMessages: Map<String, Map<String, String>> by lazy {
		val result = mutableMapOf<String, MutableMap<String, String>>()
		GsonConfig.multiPlaceJsonObject(config, "LogMessages", directory)?.entrySet()?.forEach {
			result[it.key] = GsonStringMap(it.value.asJsonObject).available
		}
		result.getOrPut("CraftItem") { mutableMapOf() }.apply {
			this.putIfAbsent("StringIsNotAMaterial", "String is not a Material")
			this.putIfAbsent("TheJsonElementIsNotAString", "The JsonElement is not a String")
		}
		val transform = result.map {
			"Hallo"
		}
		return@lazy result.toMap()
	}
	private val messages: Map<String, String> by lazy { SpigotGsonMessages(config).availableMessages }
	private val permissions: Map<String, String>? by lazy {
		GsonStringMap(GsonConfig.multiPlaceJsonObject(config, "Permissions", directory) ?: return@lazy null).available
	}
	private val configs: Map<String, JsonObject> by lazy {
		HashMap<String, JsonObject>().apply {
			GsonConfig.multiPlaceJsonObject(config, "Configs", directory, false)!!.entrySet().forEach { this[it.key] = it.value.asJsonObject }
		}
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Generate example configs if is enabled {@see generateExamples}
	 *
	 * @see net.darkdevelopers.darkbedrock.darkness.general.functions.generateExamples(String, Boolean)
	 * @since 15.10.2018
	 */
	override fun load() {
		if (!generateNoExamples) DefaultConfigs().javaClass.generateExamples(directory.toString(), resetGeneratedExamples)
	}

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * The blacklist (the configs list) and the bypass permissions are defined in the config.
	 * WARNING: This method is an Event. Don't call it manually!
	 *
	 * @param event is for the Event System from Spigot to select the right Event
	 * @see CancellableModuleConfig.json
	 * @NeededConfigVersion 1.0
	 * @LastEdit at 15.10.2018 by Lars Artmann | LartyHD
	 * @since 1.0 (13.10.2018)
	 */
	@EventHandler
	fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
		val player = event.player.toNonNull()
		val rawPermission = permissions?.get("BlockAsyncPlayerChat") ?: return
		val blocke = arrayListOf<String>()
		val words = event.message.split(" ")
//		words.forEach { if (hasPermission(player, rawPermission.replace("<Word>", it, true))) blocked.add(it) }
//		if (blocked.isNotEmpty()) {
//			cancel(event)
//			player.sendMessage(replace(messages["AsyncPlayerChat.NoPermissionToSendThisWords"], "<Words>", blocked))
//		}
	}


	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * It cancel crafting when the material is on the blacklist and you don't have the bypass permissions.
	 * The blacklist (the configs list) and the bypass permissions are defined in the config.
	 * WARNING: This method is an Event. Don't call it manually!
	 *
	 * @param event is for the Event System from Spigot to select the right Event
	 * @see CancellableModuleConfig.json
	 * @NeededConfigVersion 1.0
	 * @LastEdit at 16.10.2018 by Lars Artmann | LartyHD
	 * @since 1.0 (13.10.2018)
	 */
	@EventHandler
	fun onCraftItemEvent(event: CraftItemEvent) {
		val name = "CraftItem"
		val item = event.currentItem ?: return
		val whoClicked = event.whoClicked.toNonNull()
		val craftItemPermissions = permissions?.get(name)
		val jsonObject = configs[name] ?: return
		config.getAs<JsonArray>("BlockedMaterials", jsonObject)?.forEach {
			fun warn(key: String) = System.err.println(logMessages[name]?.get(key))
			try {
				fun String.rep() = replace(this, "<ItemType>", item.type)
				if (
						Material.valueOf(it.asString) != item.type &&
						(craftItemPermissions != null && hasPermission(whoClicked, craftItemPermissions.rep()))
				) return@forEach
				cancel(event)
				whoClicked.sendMessage(messages["$name.NoPermissionToCraftThisItem"]?.rep())
			} catch (ex: IllegalArgumentException) {
				warn("StringIsNotAMaterial")
				ex.printStackTrace()
			} catch (ex: IllegalStateException) {
				warn("TheJsonElementIsNotAString")
				ex.printStackTrace()
			}
		}
	}

	private fun replace(message: String?, key: String, value: Any) = message.toNonNull().replace(key, value.toString(), true)

	@Suppress("unused", "FunctionName")
	private inner class DefaultConfigs {

		private fun example1_0() {
			val configPath = "AsyncPlayerChat${File.separator}$defaultConfigName"
			val globalConfigPath = "global.json"
			val worldsDirectoryName = "worlds"
			val worldsDirectory = File(worldsDirectoryName)
			var config = GsonConfig(ConfigData(directory, defaultConfigName)).load()
			val default = setOf("1SexyMap", "2SuperSexyMap", "3JoJoMap", "4IAmJoJoOfficial.org")
			val exampleWorldNames = if (examples == null) default else config.getAs<JsonArray>("WorldNames", examples!!)
					?.asSequence()
					?.map { it.asString.toNonNull() }
					?.toSet()
					?: default

			fun config(config: GsonConfig) = config
					.put("BlockAll", false)
					.put("Use", config.jsonObject {
						this.put("#possibilities (IgnoreCase)", "Allowed/Allow/White/Whitelist|Blocked/Block/Black/Blacklist|None/Nothing/Disable/Disabled/Off/null")
						this.put("Words", "Blocked")
						this.put("Characters", null)
						this.put("IgnoreCase", true)
					})
					.put("Blocked", config.jsonObject {
						this.put("Words", jsonArray {
							this.add("Huso".toJsonPrimitive())
							this.add("Hurensohn".toJsonPrimitive())
						})
						this.put("Characters", jsonArray { })
						this.put("Regex", jsonArray { })
					})
					.put("Allowed", config.jsonObject {
						this.put("Words", jsonArray { })
						this.put("Characters", jsonArray { })
						this.put("Regex", jsonArray { this.add("^[\\x00-\\xFF]+\$".toJsonPrimitive()) })
					})
					.put("Permissions", config.jsonObject {
						this.put("Prefix", "CancellableModule.AsyncPlayerChat.")
						this.put("Block", "%Prefix%Block")
						this.put("ByPass", config.jsonObject {
							this.put("ByPassPrefix", "%Prefix%ByPass.")
							this.put("BlockAll", "%ByPassPrefix%BlockAll")
							this.put("Blocked", config.jsonObject {
								this.put("ByPassBlockedPrefix", "%ByPassPrefix%Blocked.")
								this.put("Words", "%ByPassBlockedPrefix%Words.<Word>")
								this.put("Characters", "%ByPassBlockedPrefix%Characters.<Char>")
								this.put("Regex", "%ByPassBlockedPrefix%Regex.<Regex>")
							})
							this.put("Allowed", config.jsonObject {
								this.put("ByPassAllowedPrefix", "%ByPassPrefix%Blocked.")
								this.put("Words", "%ByPassAllowedPrefix%Words.<Word>")
								this.put("Characters", "%ByPassAllowedPrefix%Characters.<Char>")
								this.put("Regex", "%ByPassAllowedPrefix%Regex.<Regex>")
							})
						})
					})
					.save()

			config
					.put("GenerateNoExamples", false)
					.put("ResetGeneratedExamples", true)
					.put("Message", config.jsonObject {
						this.put("UseExternalFiles", true)
						this.put("language", "de_DE")
					})
					.put("LogMessages", "error-messages.json")
					.put("Configs", config.jsonObject {
						this.put("AsyncPlayerChat", config.jsonObject { this.put("ConfigPath", configPath) })
						this.put("CraftItem", config.jsonObject {
							this.put("BlockedMaterials", jsonArray { this.add("TNT".toJsonPrimitive()) })
						})
					})
					.put("Permissions", config.jsonObject {
						this.put("CraftItem", "CancellableModule.CraftItem.Bypass.<ItemType>")
						this.put("BlockBreak", "CancellableModule.BlockBreak.Bypass.<ItemType>")
					})
					.save()

			config = GsonConfig(ConfigData(directory, configPath)).load()
			config
					.put("GlobalConfigPath", globalConfigPath)
					.put("Worlds", config.jsonObject {
						this.put("Directory", worldsDirectoryName)
						this.put("ConfigName", defaultConfigName)
					})
					.save()
			config(GsonConfig(ConfigData(directory, globalConfigPath)).load())
			exampleWorldNames.forEach {
				val file = File("$worldsDirectory${File.separator}$it")
				file.mkdirs()
				if (file.isDirectory) config(GsonConfig(ConfigData(it, defaultConfigName)).load())
//			worldsDirectory.listFiles().forEach { if (it.isDirectory) config(GsonConfig(ConfigData(it, defaultConfigName)).load()) }
			}
			fun logMessages() {
//				val logMessages = config.getAs<JsonPrimitive>("LogMessages")?.asString ?: return
				val logMessagesConfigName = "error-messages.json"
				@Suppress("NAME_SHADOWING")
				val config = GsonConfig(ConfigData(directory, logMessagesConfigName)).load()
				config
						.put("CraftItem", config.jsonObject {
							this.put("StringIsNotAMaterial", "String is not a Material")
							this.put("TheJsonElementIsNotAString", "The JsonElement is not a String")
						})
						.save()
			}
			logMessages()
		}

		private fun String.toJsonPrimitive() = JsonPrimitive(this)
		private fun GsonConfig.jsonObject(block: GsonConfig.() -> Unit): JsonObject = this.copy(JsonObject()).apply { block() }.jsonObject
		private fun jsonArray(block: JsonArray.() -> Unit): JsonArray = JsonArray().apply { block() }

	}
}
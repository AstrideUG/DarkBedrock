/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonArray
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonStringMap
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.general.message.GsonMessages
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.CraftItemEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.08.2018 22:23.
 * Last edit 24.08.2018
 */
class BlockCraftingModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription("BlockCraftingModule", "1.0", "Lars Artmann | LartyHD", "")

    private lateinit var config: GsonConfig
    private lateinit var messages: Map<String, String>
    private lateinit var permissions: Map<String, String>
    private val types: MutableSet<Material> = HashSet()
    private var allowBypassPermission = false

    override fun load() {
        config = GsonConfig(ConfigData(description.folder)).load()
    }

    override fun start() {
        messages = GsonMessages(config).availableMessages
        allowBypassPermission = config.getAsNotNull<JsonPrimitive>("AllowBypassPermission").asBoolean
        if (allowBypassPermission) permissions = GsonStringMap(config.getAsNotNull("Permissions")).available
        config.getAsNotNull<JsonArray>("types").forEach { types.add(Material.valueOf(it.asString)) }
    }

    @EventHandler
    fun onCraftItemEvent(event: CraftItemEvent) {
        val item = event.currentItem ?: return
        val whoClicked = event.whoClicked.toNonNull()
        types.forEach {
            if (item.type == it) {
                if (allowBypassPermission && hasPermission(whoClicked, replaceItemType(permissions["Bypass"], item.type)))
                    return@forEach
                cancel(event)
                whoClicked.sendMessage(ChatColor.translateAlternateColorCodes('&', replaceItemType(messages["CanNotCraft"], item.type)))
                return@forEach
            }
        }
    }

    private fun replaceItemType(message: String?, type: Material) =
            message?.replace("<ItemType>", type.toString(), true).toString()

    /* private val types: MutableSet<Material> = HashSet()
     private lateinit var config: GsonConfig
     private lateinit var canNotCraftMessage: String
     private var allowBypassPermission = false
     private lateinit var bypassPermission: String

     override fun load() {
         config = GsonConfig(ConfigData(description.folder))
     }

     override fun start() {
         canNotCraftMessage = config.getAsNotNull<JsonPrimitive>("CanNotCraftMessage").asString
         allowBypassPermission = config.getAsNotNull<JsonPrimitive>("AllowBypassPermission").asBoolean
         if (allowBypassPermission)
             bypassPermission = config.getAsNotNull<JsonPrimitive>("BypassPermission").asString
         config.getAsNotNull<JsonArray>("types").forEach { types.add(Material.valueOf(it.asString)) }
     }

     @EventHandler
     fun onCraftItemEvent(event: CraftItemEvent) {
         val item = event.currentItem ?: return
         types.forEach {
             if (item.type == it) {
                 if (allowBypassPermission) {
                     if (hasPermission(event.whoClicked, bypassPermission)) return@forEach
                     if (bypassPermission.contains("<ItemType>", true))
                         if (hasPermission(event.whoClicked, bypassPermission.replace("<ItemType>", item.type.toString(), true)))
                             return@forEach
                 }
                 cancel(event)
                 event.whoClicked.sendMessage(canNotCraftMessage.replace("<ItemType>", item.type.toString(), true))
                 return@forEach
             }
         }
     }*/

}
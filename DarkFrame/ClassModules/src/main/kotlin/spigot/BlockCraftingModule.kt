/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonArray
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
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

    private val types: MutableSet<Material> = HashSet()
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
    }
}
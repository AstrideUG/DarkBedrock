/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 01.12.2018 20:56.
 * Current Version: 1.0 (01.12.2018 - 01.12.2018)
 */

import me.lucko.luckperms.api.Contexts
import me.lucko.luckperms.api.LuckPermsApi
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatModule : Module, Listener(DarkFrame.instance) {
    private var luckPermsApi: LuckPermsApi =
        Bukkit.getServicesManager().getRegistration(LuckPermsApi::class.java as Class<*>).provider as LuckPermsApi

    override val description: ModuleDescription = ModuleDescription(
        "ChatModule",
        "1.0",
        "DevSnox and Lars Artmann | LartyHD",
        "Definiert die Darstellung der Nachrichten!",
        true
    )

    @EventHandler(priority = EventPriority.MONITOR)
    fun onChat(event: AsyncPlayerChatEvent) {
        val player = event.player
        var message = event.message
        if (!player.hasPermission("skypvp.chat.color.all"))
            message = if (player.hasPermission("skypvp.chat.color"))
                ChatColor.translateAlternateColorCodes('&', message).replace("§((?i)[fk-or])".toRegex(), "")
            else message.replace("&", "")

        val group = luckPermsApi.getGroup(luckPermsApi.getUser(player.uniqueId)?.primaryGroup ?: "")
        val prefix = luckPermsApi.userManager.getUser(player.uniqueId)?.cachedData?.getMetaData(Contexts.allowAll())
            ?.prefix ?: ""

        StringBuilder().run {
            append(prefix).append("§8- ").append(prefix.substring(0, 2)).append(player.name).append(" §8» ")
            val name = group?.name
            when (name) {
                "owner", "admin" -> append("§4§l")
                "developer" -> append("§f§l")
                else -> if (player.hasPermission("skyhype.chat.team")) append("§a§l") else append("§7")
            }
            append(event.message)
            toString()
        }
        Bukkit.broadcastMessage(message)
        cancel(event)
    }
}

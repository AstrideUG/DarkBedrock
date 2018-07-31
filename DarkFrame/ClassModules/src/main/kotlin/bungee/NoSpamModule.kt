import net.darkdevelopers.darkbedrock.darkframe.bungee.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.bungee.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.connection.ProxiedPlayer
import net.md_5.bungee.api.event.ChatEvent
import net.md_5.bungee.event.EventHandler
import java.util.*
import kotlin.concurrent.thread

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.02.2018 15:00.
 * Last edit 07.07.2018
 */
class NoSpamModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("NoSpamModule", "1.0", "Lars Artmann | LartyHD", "This module blocks spam")

    private val lastMessage: MutableMap<UUID, String> = HashMap()
    private val delay: MutableSet<UUID> = HashSet()

    @EventHandler
    fun onChatEvent(event: ChatEvent) {
        if (event.isCancelled || event.isCommand) return
        val player = event.sender as? ProxiedPlayer ?: return
        val uuid = player.uniqueId ?: return
        when {
            delay.contains(uuid) -> {
                cancel(event)
                player.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Bitte schreibe nicht so schnell"))
            }
            lastMessage[uuid] != null && lastMessage[uuid].equals(event.message, ignoreCase = true) -> {
                cancel(event)
                player.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Bitte wieder hole dich nicht"))
            }
            else -> {
                lastMessage[uuid] = event.message
                delay.add(uuid)
                thread {
                    Thread.sleep(1500)
                    delay.remove(uuid)
                }
            }
        }
    }

}
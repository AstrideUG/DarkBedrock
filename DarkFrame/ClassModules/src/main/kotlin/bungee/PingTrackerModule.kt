import net.darkdevelopers.darkbedrock.darkframe.bungee.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.bungee.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.event.ProxyPingEvent
import net.md_5.bungee.event.EventHandler
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 15.08.2018
 */
class PingTrackerModule : Module, Listener(DarkFrame.instance) {

    override val description: ModuleDescription = ModuleDescription("PingTrackerModule", "1.0.1", "Lars Artmann | LartyHD", "This module tracks the ping requests")

    private val minutePings: MutableSet<String> = HashSet()
    private val hourPings: MutableSet<String> = HashSet()
    private val dayPings: MutableSet<String> = HashSet()

    init {
        logPing(TimeUnit.MINUTES.toMillis(1), "der letzten Minute", minutePings)
        logPing(TimeUnit.HOURS.toMillis(1), "der letzten Stunde", hourPings)
        logPing(TimeUnit.DAYS.toMillis(1), "der letzten 24 Stunden", dayPings)
    }

    @EventHandler
    fun onProxyPingEvent(event: ProxyPingEvent) {
        val ip = event.connection.address.hostString ?: return
        minutePings.add(ip)
        hourPings.add(ip)
        dayPings.add(ip)
    }

    private fun logPing(sleep: Long, time: String, pings: MutableSet<String>) = thread {
        try {
            while (true) {
                Thread.sleep(sleep)
                println("In $time ${when (pings.size) {
                    0 -> "wurden $IMPORTANT keine ${TEXT}Ping's"
                    1 -> "wurde $IMPORTANT ein ${TEXT}Ping"
                    else -> "wurden $IMPORTANT ${pings.size} ${TEXT}Ping's"
                }} dokumentiert")
                pings.clear()
            }
        } catch (ignored: InterruptedException) {
        }
    }

}
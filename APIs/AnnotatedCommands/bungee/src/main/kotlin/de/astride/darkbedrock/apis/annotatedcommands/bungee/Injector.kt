/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.bungee

import de.astride.darkbedrock.apis.annotatedcommands.api.SubCommand
import net.md_5.bungee.api.ProxyServer
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 22:57.
 * Current Version: 1.0 (25.11.2018 - 26.11.2018)
 */
object Injector {

    fun addPlayerToCast() = SubCommand.addMapper(ProxiedPlayer::class) {
        try {
            ProxyServer.getInstance().getPlayer(UUID.fromString(it))
        } catch (ex: IllegalArgumentException) {
            ProxyServer.getInstance().getPlayer(it)
        }
    }

}
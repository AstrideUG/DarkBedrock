/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.velocity

import com.velocitypowered.api.proxy.Player
import com.velocitypowered.api.proxy.ProxyServer
import de.astride.darkbedrock.apis.annotatedcommands.api.Implementation
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 23:05.
 * Current Version: 1.0 (25.11.2018 - 26.11.2018)
 */
object Injector {

    fun addPlayerToCast(server: ProxyServer) = Implementation.addMapper(Player::class) {
        try {
            server.getPlayer(UUID.fromString(it)).orElse(null)
        } catch (ex: IllegalArgumentException) {
            server.getPlayer(it).orElse(null)
        }
    }

}
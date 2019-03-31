/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Bukkit
import org.bukkit.OfflinePlayer
import org.bukkit.Server
import org.bukkit.entity.Player

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:47.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:47.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val server: Server = Bukkit.getServer()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:48.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val name: String = server.name

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:49.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val version: String = server.version

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:51.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val bukkitVersion: String = server.bukkitVersion

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:51.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val onlinePlayers: Collection<Player> = server.onlinePlayers

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:51.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val offlinePlayers: Array<OfflinePlayer> = server.offlinePlayers

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:53.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val maxPlayers: Int = server.maxPlayers

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:53.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val port: Int = server.port

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:53.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val viewDistance: Int = server.viewDistance

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:53.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val ip: String = server.ip

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:55.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val serverName: String = server.serverName

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 10:55.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
val serverId: String = server.serverId

//TODO: add all

















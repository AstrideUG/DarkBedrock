/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 31.08.2018 19:15.
 * Last edit 05.04.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 31.08.2018 19:15.
 * Last edit 21.03.2019
 */
@Deprecated("static problems")
var messages: MutableMap<String, String> = mutableMapOf()

@Deprecated("static problems", ReplaceWith("sendMessage(messages[name])"))
fun CommandSender.sendConfigurableMessage(name: String): Unit = sendMessage(messages[name])

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:39.
 * Current Version: 1.0 (21.03.2019 - 05.04.2019)
 */
fun String?.sendIfNotNull(sender: CommandSender) {
    this?.sendTo(sender)
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.04.2019 20:32.
 * Current Version: 1.0 (05.04.2019 - 05.04.2019)
 */
fun Array<String?>.sendIfNotNull(sender: CommandSender): Unit = mapNotNull { it }.sendTo(sender)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.04.2019 20:25.
 * Current Version: 1.0 (05.04.2019 - 05.04.2019)
 */
fun Collection<String?>.sendIfNotNull(sender: CommandSender): Unit = mapNotNull { it }.sendTo(sender)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:39.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun String.sendTo(sender: CommandSender): Unit = sender.sendMessage(this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.04.2019 20:29.
 * Current Version: 1.0 (05.04.2019 - 05.04.2019)
 */
fun Array<String?>.sendTo(sender: CommandSender): Unit = sender.sendMessage(this)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.04.2019 20:31.
 * Current Version: 1.0 (05.04.2019 - 05.04.2019)
 */
fun Collection<String?>.sendTo(sender: CommandSender): Unit = sender.sendMessage(toTypedArray())


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:34.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
var isPlayerFailedMessage: String = "The command is only for players"

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:35.
 * Current Version: 1.0 (21.03.2019 - 05.04.2019)
 */
inline fun CommandSender.isPlayer(lambda: (Player) -> Unit, isNoPlayerMessage: String = isPlayerFailedMessage): Unit =
    isPlayer(lambda) { sendMessage(isNoPlayerMessage) }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:35.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
inline fun CommandSender.isPlayer(onSuccess: (Player) -> Unit, onFail: () -> Unit): Unit =
    if (this is Player) onSuccess(this) else onFail()

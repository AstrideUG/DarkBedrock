/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.api.manager

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.12.2018 00:43.
 * Current Version: 1.0 (08.12.2018 - 11.12.2018)
 */
interface CommandManager {

    fun register(command: Command)

    fun register(group: Group, command: Command)

    fun unregister(command: Command)

    fun unregister(group: Group)

    fun unregisterAll()

    fun call(sender: Any, command: String): Pair<Boolean, String?>

}


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.12.2018 20:15.
 *
 * Represents a Group
 *
 * Current Version: 1.0 (11.12.2018 - 11.12.2018)
 */
inline class Group(val group: Any)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.12.2018 20:15.
 *
 * Represents a Command
 *
 * Current Version: 1.0 (11.12.2018 - 11.12.2018)
 */
inline class Command(val command: Any)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.12.2018 19:49.
 *
 * @see [CommandManager.unregister]
 *
 * Current Version: 1.0 (11.12.2018 - 11.12.2018)
 */
operator fun CommandManager.minus(command: Command) = unregister(command)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.12.2018 19:49.
 *
 * @see [CommandManager.register]
 *
 * Current Version: 1.0 (11.12.2018 - 11.12.2018)
 */
operator fun CommandManager.plus(command: Command) = register(command)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.12.2018 19:49.
 *
 * @see [CommandManager.register]
 *
 * Current Version: 1.0 (11.12.2018 - 11.12.2018)
 */
operator fun CommandManager.set(group: Group, command: Command) = register(group, command)
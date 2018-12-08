/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.api.manager

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.12.2018 00:43.
 * Current Version: 1.0 (08.12.2018 - 08.12.2018)
 */
interface CommandManager {

    operator fun plus(command: Any)

    operator fun minus(command: Any)

    operator fun set(group: Any, command: Any)

    fun unregister(group: Any)

    fun call(sender: Any, command: String): Pair<Boolean, String?>

}
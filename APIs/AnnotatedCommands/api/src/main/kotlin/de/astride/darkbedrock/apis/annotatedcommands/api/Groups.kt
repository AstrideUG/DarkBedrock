/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.api

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 02:05.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 01:26.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */
interface GroupedCommands {

    val group: Any
    val commands: CommandHolder

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 02:06.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */
typealias GroupedCommandHolder = MutableSet<GroupedCommands>
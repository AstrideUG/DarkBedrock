/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.api

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 01:45.
 * Current Version: 1.0 (13.12.2018 - 14.12.2018)
 */
typealias CommandHolder = MutableSet<Class<*>>

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 02:13.
 * Current Version: 1.0 (13.12.2018 - 14.12.2018)
 */
interface CommandManager {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 13.12.2018 23:59.
     * Current Version: 1.0 (13.12.2018 - 13.12.2018)
     */
    val argSeparator: String get() = " "

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 13.12.2018.
     * Current Version: 1.0 (13.12.2018 - 13.12.2018)
     */
    val commands: CommandHolder

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 13.12.2018.
     *
     * @throws IllegalArgumentException if [commandLine] is blank
     *
     * Current Version: 1.0 (13.12.2018 - 13.12.2018)
     */
    fun call(commandLine: String, sender: Any): CallResult

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 13.12.2018 03:57.
     * Current Version: 1.0 (13.12.2018 - 13.12.2018)
     */
    fun usage(commandLine: String): String

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 14.12.2018 00:03.
     * Current Version: 1.0 (14.12.2018 - 14.12.2018)
     */
    fun String.toArgs(): List<String> = this.split(argSeparator).filter { it.isNotBlank() }

}

@Suppress("KDocMissingDocumentation")
enum class CallResult {
    SUCCESS,
    FAILED,
    //    NOT_ACCEPTED,
    NOT_CAPTURED,
    NOT_FOUND
}
/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common

import de.astride.darkbedrock.apis.annotatedcommands.api.CommandOnly
import de.astride.darkbedrock.apis.annotatedcommands.api.Permission
import de.astride.darkbedrock.apis.annotatedcommands.api.SubCommand
import de.astride.darkbedrock.apis.annotatedcommands.api.scanner.AnnotationMethodScanner

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 23:52.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 23:54.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */
object CommandOnlyAMS : AnnotationMethodScanner<CommandOnly>(CommandOnly::class.java)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 23:55.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */
object SubCommandAMS : AnnotationMethodScanner<SubCommand>(SubCommand::class.java) {

    fun findMethod(clazz: Class<*>, args: List<String>) =
        registerWithAnnotation(clazz).entries.find subCommand@{ (_, annotation) ->
            val aArgs = annotation.args
            if (aArgs.size != args.size) return@subCommand false
            args.all { sArg ->
                aArgs.any { arg ->
                    val aliases = arg.values.map { it.aliases + it.value }
                    //TODO ADD Mapping Support
                    arg.isInput || aliases.any { alias -> sArg in alias }
                }
            }
        }?.key

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 23:55.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */
object PermissionAMS : AnnotationMethodScanner<Permission>(Permission::class.java)
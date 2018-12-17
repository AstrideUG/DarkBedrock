/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common.manager

import com.google.inject.Guice
import de.astride.darkbedrock.apis.annotatedcommands.api.CallResult
import de.astride.darkbedrock.apis.annotatedcommands.api.Command
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandHolder
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandManager
import de.astride.darkbedrock.apis.annotatedcommands.common.ImplementationAMS.findMethods
import de.astride.darkbedrock.apis.annotatedcommands.common.holder.AnnotationCommandHolder
import de.astride.darkbedrock.apis.annotatedcommands.common.inject.CommandModule
import java.lang.reflect.Method

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 02:30.
 * Current Version: 1.0 (13.12.2018 - 17.12.2018)
 */
class AnnotationCommandManager : CommandManager {

    companion object {

        @JvmName("invokeWithNull")
        private fun invoke(method: Method?, instance: Any, commandLine: String, searched: String): CallResult {
            return invoke(method ?: return CallResult.NOT_CAPTURED, instance, commandLine, searched)
        }

        private fun invoke(method: Method, instance: Any, commandLine: String, searched: String) = try {
            when (val result = method.invoke(instance)) {
                is CallResult -> result
                is Boolean -> if (!result) CallResult.FAILED else CallResult.SUCCESS_BOOLEAN
                is Unit -> CallResult.SUCCESS_UNIT
                else -> {
                    System.err.println("The \"$searched\" command returns \"$result\", which can not be processed. Only \"CallResult, Boolean and Unit / Void\" can be processed.")
                    CallResult.SUCCESS_UNKNOWN
                }
            }
        } catch (ex: Throwable) {
            println("Error in \"$commandLine\": ${ex.message}")
            println(ex.printStackTrace())
            CallResult.FAILED
        }

    }

    override val commands: CommandHolder = AnnotationCommandHolder(argSeparator)

    override fun call(commandLine: String, sender: Any): CallResult {
        val toArgs = commandLine.toArgs()
        val searched = toArgs[0]

        /** !! can used here because in the [AnnotationCommandHolder.add] function of [AnnotationCommandHolder] is a check */
        val map = commands.mapTo(mutableSetOf()) { it.getAnnotation(Command::class.java)!! to it }
        val (commandAnnotation, commandClass) = map.findCommand(searched) ?: return CallResult.NOT_FOUND

        val injector = Guice.createInjector(CommandModule(this, searched, commandAnnotation.name, sender))
        val instance = injector.getInstance(commandClass)

        return invoke(findMethods(commandClass, toArgs.drop(1)).firstOrNull()?.key, instance, commandLine, searched)
    }

    override fun usage(commandLine: String): String = "USAGE for $commandLine"


    private fun Iterable<Pair<Command, Class<*>>>.findCommand(searched: String) =
        find { it.first.names().find { name -> name.equals(searched, true) } != null }

    private fun Command.names() = aliases + (name.emptyToNull() ?: javaClass.name)


    //TODO: Add This to Kotlin Utils
    private fun String.emptyToNull() = if (this.isEmpty()) null else this


}

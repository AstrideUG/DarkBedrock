/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common.manager

import com.google.inject.Guice
import de.astride.darkbedrock.apis.annotatedcommands.api.CallResult
import de.astride.darkbedrock.apis.annotatedcommands.api.Command
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandHolder
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandManager
import de.astride.darkbedrock.apis.annotatedcommands.common.ImplementationAMS
import de.astride.darkbedrock.apis.annotatedcommands.common.ImplementationAMS.findMethod
import de.astride.darkbedrock.apis.annotatedcommands.common.holder.AnnotationCommandHolder
import de.astride.darkbedrock.apis.annotatedcommands.common.inject.CommandModule
import java.lang.reflect.Method

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 02:30.
 * Current Version: 1.0 (13.12.2018 - 18.12.2018)
 */
class AnnotationCommandManager : CommandManager {

    companion object {

        @JvmName("invokeWithNull")
        private fun invoke(
            method: Method?,
            instance: Any,
            commandLine: String,
            searched: String,
            args: List<String>
        ): CallResult {
            return invoke(method ?: return CallResult.NOT_CAPTURED, instance, commandLine, searched, args)
        }

        private fun invoke(
            method: Method,
            instance: Any,
            commandLine: String,
            searched: String,
            args: List<String>
        ) = try {
            when (val result = invoke(method, instance, args)) {
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

        /**
         * @author Lars Artmann | LartyHD
         * Created by Lars Artmann | LartyHD on 18.12.2018 10:53.
         * Current Version: 1.0 (18.12.2018 - 18.12.2018)
         */
        private fun invoke(method: Method, instance: Any, args: List<String>): Any? {
            val parameter = ImplementationAMS.getParameter(method, args) ?: return null
            return method.invoke(instance, *parameter)
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

        val args = toArgs.drop(1)
        val method = findMethod(commandClass, args)
        return invoke(method, instance, commandLine, searched, args)
    }

    override fun usage(commandLine: String): String = "USAGE for $commandLine"


    private fun Iterable<Pair<Command, Class<*>>>.findCommand(searched: String) =
        find { it.first.names().find { name -> name.equals(searched, true) } != null }

    private fun Command.names() = aliases + (name.emptyToNull() ?: javaClass.name)


    //TODO: Add This to Kotlin Utils
    private fun String.emptyToNull() = if (this.isEmpty()) null else this

    //TODO: Add This to Kotlin Utils
    private fun String.blankToNull() = if (this.isBlank()) null else this


}

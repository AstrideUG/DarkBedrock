/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common.manager

import com.google.inject.Guice
import de.astride.darkbedrock.apis.annotatedcommands.api.CallResult
import de.astride.darkbedrock.apis.annotatedcommands.api.Command
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandHolder
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandManager
import de.astride.darkbedrock.apis.annotatedcommands.common.CommandOnlyAMS
import de.astride.darkbedrock.apis.annotatedcommands.common.SubCommandAMS
import de.astride.darkbedrock.apis.annotatedcommands.common.inject.CommandModule
import java.lang.reflect.Method

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 02:30.
 * Current Version: 1.0 (13.12.2018 - 15.12.2018)
 */
class AnnotationCommandManager : CommandManager {

    companion object {

        //TODO: ADD MULTI CommandOnly support
        private fun commandOnly(instance: Any, commandLine: String, searched: String): CallResult =
            invoke(CommandOnlyAMS.register(instance.javaClass).firstOrNull(), instance, commandLine, searched)

        @JvmName("invokeWithNull")
        private fun invoke(method: Method?, instance: Any, commandLine: String, searched: String): CallResult {
            return invoke(method ?: return CallResult.NOT_CAPTURED, instance, commandLine, searched)
        }

        private fun invoke(method: Method, instance: Any, commandLine: String, searched: String) = try {
            when (val result = method.invoke(instance)) {
                is CallResult -> result
                is Boolean -> if (!result) CallResult.FAILED else CallResult.SUCCESS
                is Unit -> CallResult.SUCCESS
                else -> {
                    System.err.println("The \"$searched\" command returns \"$result\", which can not be processed. Only \"CallResult, Boolean and Unit / Void\" can be processed.")
                    CallResult.SUCCESS
                }
            }
        } catch (ex: Throwable) {
            println("Error in \"$commandLine\": ${ex.message}")
            println(ex.printStackTrace())
            CallResult.FAILED
        }

    }

    override val commands: CommandHolder = mutableSetOf()

    override fun call(commandLine: String, sender: Any): CallResult {
        val toArgs = commandLine.toArgs()
        if (toArgs.isEmpty()) throw IllegalArgumentException("\"commandLine\" can not be blank")
        val searched = toArgs[0]

        val map = /* !! can used here because in the [add] function is a check */
            commands.mapTo(mutableSetOf()) { it.getAnnotation(Command::class.java)!! to it }
        val (commandAnnotation, commandClass) = map.findCommand(searched) ?: return CallResult.NOT_FOUND

        val injector = Guice.createInjector(CommandModule(this, searched, commandAnnotation.name, sender))
        val instance = injector.getInstance(commandClass)

        val args = toArgs.drop(1)
        return if (args.isEmpty()) commandOnly(instance, commandLine, searched)
        else invoke(SubCommandAMS.findMethod(commandClass, args), instance, commandLine, searched)
    }

    override fun usage(commandLine: String): String = "USAGE for $commandLine"


    private fun Iterable<Pair<Command, Class<*>>>.findCommand(searched: String) =
        find { it.first.names().find { name -> name.equals(searched, true) } != null }

    //    private fun Iterable<Command>.findCommand(searched: String) =
    //        find { it.names().find { name -> name.equals(searched, true) } != null }
    //    private fun Iterable<Command>.allNames() = map { it.names() }
    private fun Command.names() = aliases + (name.emptyToNull() ?: javaClass.name)


    //TODO: Add This to Kotlin Utils
    private fun String.emptyToNull() = if (this.isEmpty()) null else this


}

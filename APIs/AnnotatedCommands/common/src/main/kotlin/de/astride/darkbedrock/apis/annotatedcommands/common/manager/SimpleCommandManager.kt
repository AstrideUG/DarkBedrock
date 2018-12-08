/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common.manager

import de.astride.darkbedrock.apis.annotatedcommands.api.Command
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandOnly
import de.astride.darkbedrock.apis.annotatedcommands.api.SendUsageIfThrownException
import de.astride.darkbedrock.apis.annotatedcommands.api.SubCommand
import de.astride.darkbedrock.apis.annotatedcommands.api.manager.CommandManager
import java.lang.reflect.Method
import kotlin.reflect.KClass
import kotlin.reflect.jvm.kotlinFunction


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.12.2018 04:04.
 * Current Version: 1.0 (08.12.2018 - 08.12.2018)
 */
private typealias LCommand = Any

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.12.2018 04:04.
 * Current Version: 1.0 (08.12.2018 - 08.12.2018)
 */
private typealias Group = Any


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 08.12.2018 00:50.
 * Current Version: 1.0 (08.12.2018 - 08.12.2018)
 */
class SimpleCommandManager : CommandManager {

    private val commandsWithGroup = mutableMapOf<LCommand, Group>() //Command, Group
    private val anon = object : Group() {
        override fun toString(): String = "<anon>"
    }

    override fun plus(command: LCommand) = set(command, anon)

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 08.12.2018 03:01.
     *
     * @throws IllegalArgumentException when [command] has no [Command] annotation
     *
     * Current Version: 1.0 (08.12.2018 - 08.12.2018)
     */
    override fun set(group: Group, command: LCommand) {
        if (command.hasAnnotation(Command::class)) throw IllegalArgumentException("Command ($command) has no [de.astride.darkbedrock.apis.annotatedcommands.api.Command] annotation")
        commandsWithGroup[command] = group
    }

    override fun unregister(group: Group) = commandsWithGroup.replaceAll { _, localGroup -> localGroup === group }

    override operator fun minus(command: LCommand) {
        commandsWithGroup -= command
    }

    override fun call(sender: Any, command: String): Pair<Boolean, String?> {
        val args = command.split(" ").filter { it.isNotBlank() }
        if (args.isEmpty()) throw IllegalArgumentException("\"command\" can not be blank")

        val commands = args[0].commands()
        val methods = commands.javaClass.methods

        //TODO Permission check

        if (args.size == 1) {
            //TODO MAKE IT BETTER NOW!
            val onlyCommand = methods.filter { it.hasAnnotation(CommandOnly::class) }
            return if (onlyCommand.isEmpty()) true to usage() else {
                var boolean = false
                onlyCommand.forEach {
                    if (sendUsageIfThrownException(it) {
                            try {
                                val function = it.kotlinFunction as Function0<*>
                                function()
                            } catch (ex: ClassCastException) {
                                System.err.println("Functions with CommandOnly annotation, must have no args (Method: $it)")
                            }
                        }) boolean = true
                }
                boolean to usage()
            }
        } else {

            val annotations = methods.map { it.toAnnotation(SubCommand::class) }
            annotations.forEach {
                //TODO
            }

            val subCommands = methods.filter { it.hasAnnotation(SubCommand::class) }
            subCommands.forEach {
                //TODO
            }

        }
        return false to null
    }

    private fun sendUsageIfThrownException(method: Method, block: () -> Unit) = try {
        block()
        false
    } catch (ex: Exception) {
        if (method.hasAnnotation(SendUsageIfThrownException::class)) true else throw ex
    }

    private fun usage() = "TODO"

    private fun Any.hasAnnotation(clazz: KClass<*>) = javaClass.isAnnotationPresent(clazz.java as Class<Annotation>?)
    private fun <A : Annotation> Any.toAnnotation(clazz: KClass<A>) = javaClass.getAnnotation(clazz.java as Class<A>?)
    private fun String.commands() = commandsWithGroup.map { it.key }.filter { this in it.toAnnotation(Command::class) }
    private operator fun Command.contains(value: String) = value in aliases + name

}
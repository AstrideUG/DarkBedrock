///*
// * © Copyright - Lars Artmann aka. LartyHD 2018.
// */
//
//package de.astride.darkbedrock.apis.annotatedcommands.common.manager
//
//import de.astride.darkbedrock.apis.annotatedcommands.api.CommandOnly
//import de.astride.darkbedrock.apis.annotatedcommands.api.SendUsageIfThrownException
//import de.astride.darkbedrock.apis.annotatedcommands.api.SubCommand
//import de.astride.darkbedrock.apis.annotatedcommands.api.manager.Command
//import de.astride.darkbedrock.apis.annotatedcommands.api.manager.CommandManager
//import de.astride.darkbedrock.apis.annotatedcommands.api.manager.Group
//import java.lang.reflect.Method
//import kotlin.reflect.KClass
//
//
///**
// * @author Lars Artmann | LartyHD
// * Created by Lars Artmann | LartyHD on 08.12.2018 00:50.
// * Current Version: 1.0 (08.12.2018 - 11.12.2018)
// */
//class SimpleCommandManager : CommandManager {
//
//    private val commandsWithGroup = mutableMapOf<Command, Group>() //Command, Group
//    private val commands = mutableMapOf<Group, MutableSet<Command>>()
//    private val anon = object : Group() {
//        override fun toString(): String = "<anon>"
//    }
//
//
//    override fun register(command: Command) = register(command, anon)
//
//    override fun register(group: Group, command: Command) {
//        if (command.hasAnnotation(Command::class)) throw IllegalArgumentException("Command ($command) has no [de.astride.darkbedrock.apis.annotatedcommands.api.Command] annotation")
//        val commandsByGroup = commands.putIfAbsent(group, mutableSetOf())!!
//
//        commands[group]?.add(command)
//        val groupEntry = commands[group]
//        groupEntry + command
//    }
//
//    override fun unregister(command: Command) {
//        commandsWithGroup -= command
//        commands.entries().forEach { (group, p1) -> commands.remove(group, p1) }
//    }
//
//    override fun unregisterAll() = commandsWithGroup.clear()
//
//    override fun unregisterAll(group: Group) = commandsWithGroup.replaceAll { _, localGroup -> localGroup === group }
//
//    override fun call(sender: Any, command: String): Pair<Boolean, String?> {
//        val toArgs = command.split(" ").filter { it.isNotBlank() }
//        if (toArgs.isEmpty()) throw IllegalArgumentException("\"command\" can not be blank")
//
//        val commands = toArgs[0].commands()
//        val methods = commands.javaClass.methods
//
//        //TODO Permission check
//
//        if (toArgs.size == 1) {
//            //TODO MAKE IT BETTER NOW!
//            val onlyCommand = methods.filter { it.hasAnnotation(CommandOnly::class) }
//            return if (onlyCommand.isEmpty()) true to usage() else {
//                var boolean = false
//                onlyCommand.forEach {
//                    if (sendUsageIfThrownException(it) {
//                            try {
//                                val function = it.kotlinFunction as Function0<*>
//                                function()
//                            } catch (ex: ClassCastException) {
//                                System.err.println("Functions with CommandOnly annotation, must have no toArgs (Method: $it)")
//                            }
//                        }) boolean = true
//                }
//                boolean to usage()
//            }
//        } else {
//
//            val annotations = methods.map { it.toAnnotation(SubCommand::class) }
//            annotations.forEach {
//                //TODO
//            }
//
//            val subCommands = methods.filter { it.hasAnnotation(SubCommand::class) }
//            subCommands.forEach {
//                //TODO
//            }
//
//        }
//        return false to null
//    }
//
//    private fun sendUsageIfThrownException(method: Method, block: () -> Unit) = try {
//        block()
//        false
//    } catch (ex: Exception) {
//        if (method.hasAnnotation(SendUsageIfThrownException::class)) true else throw ex
//    }
//
//    private fun usage() = "TODO"
//
//    private fun Any.hasAnnotation(clazz: KClass<*>) = javaClass.isAnnotationPresent(clazz.java as Class<Annotation>?)
//    private fun <A : Annotation> Any.toAnnotation(clazz: KClass<A>) = javaClass.getAnnotation(clazz.java as Class<A>?)
//    private fun String.commands() =
//        commandsWithGroup.map { it.key }.filter { this in it.toAnnotation(de.astride.darkbedrock.apis.annotatedcommands.api.Command::class) }
//
//    private operator fun de.astride.darkbedrock.apis.annotatedcommands.api.Command.contains(value: String) =
//        value in aliases + name
//
//}
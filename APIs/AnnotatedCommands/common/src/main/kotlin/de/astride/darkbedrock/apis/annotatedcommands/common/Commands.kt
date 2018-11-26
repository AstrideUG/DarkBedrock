/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common

//import de.lartyhd.skyblock.planets.Planets
//import org.bukkit.Bukkit
//import org.bukkit.event.EventPriority
//import org.bukkit.event.Listener
//import org.bukkit.event.player.PlayerCommandPreprocessEvent
//import org.bukkit.plugin.EventExecutor
//import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.11.2018 22:12.
 * Current Version: 1.0 (19.11.2018 - 20.11.2018)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 00:45.
 *
 *  This is a simple instance of a [Listener] for [registerEventExecutor]
 *
 * Current Version: 1.0 (19.11.2018 - 26.11.2018)
 */
//private val singleListener = object : Listener {}
//
//fun registerEventExecutor(eventExecutor: EventExecutor) = Bukkit.getPluginManager().registerEvent(
//		PlayerCommandPreprocessEvent::class.java,
//		singleListener,
//		EventPriority.NORMAL,
//		eventExecutor,
//		JavaPlugin.getPlugin(Planets::class.java)
//)
//
//fun checkClassforSubCommands(clazz: Class<*>, event: PlayerCommandPreprocessEvent) = clazz.declaredMethods.forEach { method ->
//
//	fun String.toNotBlankArgs() = this.toLowerCase().split(" ").filter { it.isNotBlank() }
//
//	//TODO Add Check is function is private
////	if (function.visibility == null || function.visibility != KVisibility.PRIVATE) return@forEach
//
//	val function = method.kotlinFunction ?: return@forEach
//	val subCommand = function.findAnnotation<SubCommand>()?.args ?: return@forEach
////	val subCommand = method.getAnnotation(SubCommand::class.java)?.args ?: return@forEach
//
////	val args = subCommand.flatMap { arg -> arg.value.toNotBlankArgs().map { it to arg.isInput } }
//	val commandArgs = event.message.toNotBlankArgs()
//
////	val cancel: Boolean
////	if (args.size != commandArgs.size) {
////		if (args.all { !it.second }) {
////			if (args.map { it.first } == commandArgs)
////				cancel = false
////			else {
////
////			}
////		} else {
////
////		}
////	} else {
////
////	}
//
//
////	event.isCancelled = cancel
//}

/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot.commands

import net.darkdevelopers.darkbedrock.darkframe.spigot.config.commands.CancellablesCommandConfig
import net.darkdevelopers.darkbedrock.darkframe.spigot.functions.formatForCancellable
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.cancellableSetterMethods
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.commands.extensions.register
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendIfNotNull
import org.bukkit.command.TabCompleter
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.kotlin.cli.common.toBooleanLenient

/**
 * Created on 25.06.2019 03:42.
 * @author Lars Artmann | LartyHD
 */
object CancellablesCommand {

    fun setup(
        plugin: JavaPlugin,
        values: Map<String, Any?>
    ) {
        CancellablesCommandConfig(values).register(
            plugin,
            tabCompleter = TabCompleter { _, _, _, args ->
                when (args.size) {
                    1 -> cancellableSetterMethods.map { it.name.formatForCancellable() }.filter {
                        it.regionMatches(0, args[0], 0, args[0].length, true)
                    }
                    2 -> listOf("true", "false")
                    else -> emptyList<String>()
                }
            }) { sender, args, commandConfig ->


            val value = args[1].toBooleanLenient()
            if (value != null) {
                val method =
                    cancellableSetterMethods.find { it.name.formatForCancellable().equals(args.first(), true) }
                if (method != null) {
                    fun String?.replaceAndSendIfNotNull() =
                        this?.replace("@method@", method.name.formatForCancellable())
                            ?.replace("@value@", value.toString())
                            .sendIfNotNull(sender)

                    commandConfig.messageSuccess.replaceAndSendIfNotNull()
                    method.invoke(null, value)
                    commandConfig.messageSuccessfully.replaceAndSendIfNotNull()
                } else sendUseMessage(sender)
            } else commandConfig.messageValueMustBe.sendIfNotNull(sender)
        }
    }

}
/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot.config.commands

import net.darkdevelopers.darkbedrock.darkframe.spigot.functions.formatForCancellable
import net.darkdevelopers.darkbedrock.darkness.general.configs.default
import net.darkdevelopers.darkbedrock.darkness.general.configs.getValue
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.cancellableSetterMethods
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.commands.AbstractCommandConfig

/**
 * Created on 25.06.2019 02:03.
 * @author Lars Artmann | LartyHD
 */
class CancellablesCommandConfig(values: Map<String, Any?>) : AbstractCommandConfig(values) {

    override val commandName by values.default { "Cancellables" }
    override val permission by values.default { "darkframe.commands.cancellables" }
    override val usage by values.default {
        cancellableSetterMethods.joinToString("|") { it.name.formatForCancellable() + " <Value>" }
    }
    override val minLength: Int by values.default { 2 }
    override val maxLength: Int by values.default { 2 }

    val messageValueMustBe: String? by values.default { "<Vaule> must be true, false, on, off, yes, no, y or n" }
    val messageSuccess: String? by values.default { "@method@ will be changed to @value@" }
    val messageSuccessfully: String? by values.default { "@method@ have been changed to @value@" }

}
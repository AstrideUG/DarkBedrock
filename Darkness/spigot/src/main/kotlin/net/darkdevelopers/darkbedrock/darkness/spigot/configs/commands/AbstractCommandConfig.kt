/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.configs.commands

import net.darkdevelopers.darkbedrock.darkness.general.configs.default
import net.darkdevelopers.darkbedrock.darkness.general.configs.getValue

/**
 * Created on 25.06.2019 02:23.
 * @author Lars Artmann | LartyHD
 */
@Suppress("unused")
abstract class AbstractCommandConfig(values: Map<String, Any?>) : CommandConfig {

    override val permission by values.default { "" }
    override val permissionMessage by values.default { "" }
    override val usage by values.default { "" }
    override val minLength by values.default { 0 }
    override val maxLength by values.default { 0 }
    override val aliases by values.default { setOf<String>() }

}
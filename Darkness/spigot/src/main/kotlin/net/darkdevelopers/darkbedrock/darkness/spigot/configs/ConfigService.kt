/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.configs

import net.darkdevelopers.darkbedrock.darkness.general.configs.default
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 01:20.
 * Last edit 05.06.2019
 */
open class ConfigService(values: Map<String, Any?>) {
    private val commandStart by values.default { "/" }
    private val commandUseMessageSuffix by values.default { "$commandStart@command-name@ $TEXT@usage@" }
    val commandUseMessagePrefix by values.default { "${Messages.PREFIX}${TEXT}Nutze: $IMPORTANT" }
    val commandUseMessageSingle by values.default { "$commandUseMessagePrefix$commandUseMessageSuffix" }
    val commandUseMessageLine by values.default { "$TEXT- $IMPORTANT$commandUseMessageSuffix" }
    val commandUseMessageRun by values.default { "$commandStart@commandName@ @usage@" }
    val commandUseMessageHover by values.default { "${TEXT}Klicke um den Command vorzuschlagen" }
    val defaultHasPermission by values.default { "§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error." }

}

var configService: ConfigService = ConfigService(mapOf())


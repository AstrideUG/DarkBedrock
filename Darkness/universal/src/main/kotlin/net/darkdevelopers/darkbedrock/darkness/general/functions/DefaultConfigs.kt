/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.10.2018 09:45.
 * Last edit 15.10.2018
 */

/**
 * @author Lars Artmann | LartyHD
 *
 * Call all functions of the class that starts with "example" and parameter count is 0
 *
 * @param folder to add the examples folder with the examples
 * @param restGeneratedExamples If {@code false} and the file exists, the sample files will not be renewed
 *
 * @since 1.0
 * @since 15.10.2018
 */
fun Class<*>.generateExamples(folder: String, restGeneratedExamples: Boolean = true): Unit =
    this.javaClass.declaredMethods.forEach {
        if (!it.name.startsWith("example") || it.parameterCount != 0/*1 || it.parameters[1].type == GsonConfig::class.java*/) return
        val id = it.name.substring(7)
        val configData = ConfigData("$folder${File.separator}examples${File.separator}$id", "config.json", false)
        if (restGeneratedExamples || !configData.exists()) {
            val jsonObject =
                GsonService.loadAsJsonObject(configData)
            jsonObject.addProperty("ConfigVersion", id.replace('_', '.'))
            it(jsonObject)
        }
    }
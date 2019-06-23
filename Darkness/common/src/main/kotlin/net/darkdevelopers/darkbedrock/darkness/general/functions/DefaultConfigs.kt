/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import com.google.gson.JsonObject
import java.io.File.separator

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.10.2018 09:45.
 * Last edit 04.06.2019
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
 * Last edit 04.06.2019
 */
fun Class<*>.generateExamples(folder: String, restGeneratedExamples: Boolean = true): Unit = declaredMethods.forEach {
    if (!it.name.startsWith("example") || it.parameterCount != 0/*1 || it.parameters[1].type == GsonConfig::class.java*/) return
    val id = it.name.substring(7)

    val configData = "$folder${separator}examples$separator$id".toConfigData("config")
    if (restGeneratedExamples || !configData.exists()) {
        val jsonObject = configData.load<JsonObject>()
        jsonObject.addProperty("ConfigVersion", id.replace('_', '.'))
        it(jsonObject)
    }
}
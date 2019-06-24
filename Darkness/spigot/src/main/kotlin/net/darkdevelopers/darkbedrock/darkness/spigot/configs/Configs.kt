/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.configs

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.functions.load
import net.darkdevelopers.darkbedrock.darkness.general.functions.save
import net.darkdevelopers.darkbedrock.darkness.general.functions.toJsonObject
import net.darkdevelopers.darkbedrock.darkness.general.functions.toMap
import net.darkdevelopers.darkbedrock.darkness.spigot.global.Classes
import java.lang.reflect.Modifier

/*
 * Created on 24.06.2019 00:06.
 * @author Lars Artmann | LartyHD
 */

lateinit var messages: Messages

fun ConfigData.loadCancellable() {

    val loaded = load<JsonObject>().toMap()

    val methods = Classes.cancellableKt.declaredMethods.filter {
        it.name.startsWith("set") && it.parameterCount == 1 && Modifier.isStatic(it.modifiers)
    }
    val map = methods.map { method ->
        val getter = methods.find { it.name == "g${method.name.drop(1)}" }
        method.name to getter?.invoke(null)
    }.toMap() + loaded

    map.forEach { (s, any) ->
        if (any !is Boolean) return@forEach
        val setter = (methods.find { it.name == s } ?: return@forEach)
        setter.invoke(null, any)
    }

    save(map.map { it.key to it.value }.toMap().toJsonObject(true))

}


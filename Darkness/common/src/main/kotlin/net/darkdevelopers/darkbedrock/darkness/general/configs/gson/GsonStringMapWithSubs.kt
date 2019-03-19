/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.functions.asString


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.08.2018 00:25.
 * Last edit 18.03.2019
 */
class GsonStringMapWithSubs(jsonObject: JsonObject) : GsonStringMap(jsonObject) {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 18.03.2019 23:33.
     * Current Version: 1.0 (18.03.2019 - 18.03.2019)
     */
    override val available: MutableMap<String, String?> = jsonObject.loop1("").toMutableMap()

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 18.03.2019 23:32.
     * Current Version: 1.0 (18.03.2019 - 18.03.2019)
     */
    override val availableOnInit: Map<String, String?> = available.toMap()

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 18.03.2019 23:17.
 * Current Version: 1.0 (18.03.2019 - 18.03.2019)
 */
private fun Map<String, Any?>.loop(prefix: String): Map<String, Any?> = mapNotNull { (key, value) ->

    val a = if (value is JsonElement)
        if (value is JsonObject)
            loop("$prefix$key.")
        else
            value.asString()
    else ""
    "$prefix$key" to a
}.toMap()


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.03.2019 00:04.
 * Current Version: 1.0 (19.03.2019 - 19.03.2019)
 */
private fun JsonObject.loop1(prefix: String): Map<String, String?> =
    entrySet().map { it.toPair() }.toMap().loop1(prefix)

private fun Map<String, JsonElement>.loop1(prefix: String): Map<String, String?> =
    mapNotNull { (key, element) -> if (element !is JsonObject) prefix + key to element.asString() else null }.toMap()

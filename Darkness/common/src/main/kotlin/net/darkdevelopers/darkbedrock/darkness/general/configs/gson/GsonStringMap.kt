/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.functions.asString

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.08.2018 00:37.
 * Last edit 18.03.2019
 */
open class GsonStringMap(jsonObject: JsonObject) {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 18.03.2019 21:40.
     * Current Version: 1.0 (18.03.2019 - 18.03.2019)
     */
    open val available: Map<String, String?> = jsonObject.toStringMap()

//    /**
//     * @author Lars Artmann | LartyHD
//     * Created by Lars Artmann | LartyHD on 18.03.2019 22:33.
//     * Current Version: 1.0 (18.03.2019 - 18.03.2019)
//     */
//    @Suppress("LeakingThis")
//    open val availableOnInit: Map<String, String?> = available.toMap()


}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 18.03.2019 22:43.
 * Current Version: 1.0 (18.03.2019 - 18.03.2019)
 */
private fun JsonObject.toStringMap(): Map<String, String?> = entrySet().mapNotNull { (key, element) ->
    try {
        key to element.asString()
    } catch (e: Exception) {
        null
    }
}.toMap()
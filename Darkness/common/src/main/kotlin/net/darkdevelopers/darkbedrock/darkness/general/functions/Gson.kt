/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import com.google.gson.JsonElement

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 18.03.2019 21:40.
 * Current Version: 1.0 (18.03.2019 - 18.03.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 18.03.2019 21:41.
 * Current Version: 1.0 (18.03.2019 - 18.03.2019)
 */
fun JsonElement.asString(): String? = if (isJsonNull) null else asString
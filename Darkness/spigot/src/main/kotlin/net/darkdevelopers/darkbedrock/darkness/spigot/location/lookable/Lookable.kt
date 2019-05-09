package net.darkdevelopers.darkbedrock.darkness.spigot.location.lookable

import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataLookable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 12:59.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
interface Lookable {
    val yaw: Float
    val pitch: Float
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 14:50.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun Lookable.copy(
    yaw: Float = this.yaw,
    pitch: Float = this.pitch
): Lookable = DataLookable(yaw, pitch)
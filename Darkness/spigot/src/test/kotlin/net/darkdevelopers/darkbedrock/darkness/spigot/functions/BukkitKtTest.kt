/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.Location
import org.mockito.Mockito
import kotlin.test.Test

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.03.2019 11:35.
 * Current Version: 1.0 (30.03.2019 - 30.03.2019)
 */
class BukkitKtTest {

    @Test
    fun randomLook() {

        val mock = Mockito.mock(Location::class.java)

        val list = mutableListOf<Location>().apply { for (i in 0..1000) add(mock.randomLook()) }
        println(list.map { it.yaw })
        assert(list.any { it.yaw == 0f }) { "no -0f yaw" }
        assert(list.any { it.yaw == 90f }) { "no 90f yaw" }
        assert(list.any { it.yaw == -90f }) { "no -90f yaw" }
        assert(list.any { it.yaw == 180f }) { "no 180f yaw" }

    }
}
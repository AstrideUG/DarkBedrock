/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map

import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.locationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.location.inmutable.extensions.lookableLocationOf
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.inmutable.extensions.to.toVector3
import net.darkdevelopers.darkbedrock.darkness.spigot.region.Region
import org.junit.Test

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 19:59.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
class GameMapKtTest {

    @Test
    fun `check serialization & deserialization`() {

        //given
        val world = "TestWorld"
        val spawn = lookableLocationOf(world, 10.5, 100.0, 7.5, 90f, 180f)
        val hologram = locationOf(world, 101.0.toVector3())
        val region = Region.of(world, 0.0.toVector3(), 1000.0.toVector3())
        val gameMap = DataGameMap("Test", spawn, hologram, region, null)

        val check = mapOf(
            "name" to "Test",
            "spawn" to mapOf(
                "world" to "TestWorld",
                "x" to 10.5,
                "y" to 100.0,
                "z" to 7.5,
                "yaw" to 90f,
                "pitch" to 180f
            ),
            "hologram" to mapOf(
                "world" to "TestWorld",
                "x" to 101.0,
                "y" to 101.0,
                "z" to 101.0
            ),
            "region" to mapOf(
                "world" to "TestWorld",
                "pos2" to mapOf(
                    "x" to 1000.0,
                    "y" to 1000.0,
                    "z" to 1000.0
                )
            )
        )

        //when
        val generated = gameMap.toMap()

        //then
        println(generated.toString())
        println(check.toString())
        assert(generated.toString() == check.toString())
        assert(generated.toGameMap() == check.toGameMap())

    }
}
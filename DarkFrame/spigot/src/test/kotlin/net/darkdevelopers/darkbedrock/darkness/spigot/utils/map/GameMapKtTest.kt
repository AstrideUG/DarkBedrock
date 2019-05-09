package net.darkdevelopers.darkbedrock.darkness.spigot.utils.map

import net.darkdevelopers.darkbedrock.darkness.spigot.location.data.DataLocation
import net.darkdevelopers.darkbedrock.darkness.spigot.location.vector.toVector3D
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
        val spawn = DataLocation(world, 10.5, 100.0, 7.5, 90f, 180f)
        val hologram = DataLocation(world, 101.toVector3D())
        val region = Region.of(world, 0.toVector3D(), 1000.toVector3D())
        val gameMap = DataGameMap("Test", spawn, hologram, region)

        val check = mapOf(
            "name" to "Test",
            "spawn" to mapOf(
                "world" to "TestWorld",
                "x" to 10.5,
                "y" to 100.0,
                "z" to 7.5,
                "yaw" to 90.0,
                "pitch" to 180.0
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
        assert(generated.toString() == check.toString())
        assert(generated.toGameMap() == check.toGameMap())

    }
}
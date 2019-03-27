/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import org.junit.jupiter.api.Test
import kotlin.random.Random

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 04:39.
 * Current Version: 1.0 (21.03.2019 - 27.03.2019)
 */
class InventoryUtilsKtTest {

    @Test
    fun `check line == 9`(): Unit = assert(line == 9)

    @Test
    fun `getInventorySize check random`() {
//        for (i in 1..10000) {
        val input = Random.nextInt()
        val out = input / line
        `check getInventorySize`(
            input,
            if (out <= 0) 0 else if (out <= 1) 1 else if (out <= 2) 2 else if (out <= 3) 3 else if (out <= 4) 4 else if (out <= 5) 5 else 54
        )
//        }
    }

    @Test
    fun `getInventorySize check higher`(): Unit = `check getInventorySize`(72, 54)

    @Test
    fun `getInventorySize check positive`(): Unit = `check getInventorySize`(44, 45)

    @Test
    fun `getInventorySize check same`(): Unit = `check getInventorySize`(9, 9)

    @Test
    fun `getInventorySize check one`(): Unit = `check getInventorySize`(1, 9)

    @Test
    fun `getInventorySize check zero`(): Unit = `check getInventorySize`(0, 0)

    @Test
    fun `getInventorySize check negative`(): Unit = `check getInventorySize`(-10, 0)

    private fun `check getInventorySize`(input: Int, check: Int) {

        //When
        val inventorySize = getInventorySize(input)

        //Then
        assert(inventorySize == check)

    }

}
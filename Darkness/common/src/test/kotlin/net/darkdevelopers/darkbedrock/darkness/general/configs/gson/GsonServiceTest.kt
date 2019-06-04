/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.functions.save
import org.junit.Test
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 18.03.2019 22:16.
 * Current Version: 1.0 (18.03.2019 - 18.03.2019)
 */
class GsonServiceTest {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 18.03.2019 22:16.
     * Current Version: 1.0 (18.03.2019 - 10.05.2019)
     */
    @Test
    fun save() {
        val file = File("save-test.json")
        val max = 1000
        JsonObject().apply { for (i in 1..max) addProperty("Test$i", "Hallo World $i") }.save(file)
        assert(file.readLines().size == max + 2)
        file.deleteOnExit()
    }

}
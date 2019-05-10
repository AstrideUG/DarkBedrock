/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.gson

import com.google.gson.JsonObject
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
     * Current Version: 1.0 (18.03.2019 - 18.03.2019)
     */
    @Test
    fun save() {
        GsonService.save(File("save-test.json"), JsonObject().apply {
            for (i in 1..1000) addProperty("Test$i", "Hallo World $i")
        })
    }

}
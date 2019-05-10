package net.darkdevelopers.darkbedrock

import org.junit.Test
import java.io.File
import java.io.File.separator

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 10.05.2019 17:36.
 * Current Version: 1.0 (10.05.2019 - 10.05.2019)
 */
class VersionTest {

    @Test
    fun `version has 2 dots`() {

        val resource =
            File("build${separator}resources${separator}main${separator}plugin.yml").toPath().toAbsolutePath()
        val messages = /*resource.reader().readText()*/resource.toFile().readLines()

        var succsess = false

        messages.forEach { message ->
            if (!message.startsWith("version:")) return@forEach
            val version = message.replaceFirst("version:", "").trim().split('.').filter { it.isNotBlank() }
            assert(version.size == 3)
            succsess = true
        }

        assert(succsess)

    }

}

/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:55.
 * Last edit 04.06.2019
 */

private const val defaultSuffix = ".json"

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:52.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */
fun String.toConfigData(
    file: String,
    prefix: String = "",
    suffix: String = defaultSuffix,
    create: Boolean = true
): ConfigData = File(this).toConfigData(file, prefix, suffix, create)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:43.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */
fun File.toConfigData(
    file: String,
    prefix: String = "",
    suffix: String = defaultSuffix,
    create: Boolean = true
): ConfigData = file.toConfigData(this, prefix, suffix, create)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:42.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */
fun String.toConfigData(
    directory: File,
    prefix: String = "",
    suffix: String = defaultSuffix,
    create: Boolean = true
): ConfigData = ConfigData(directory, "$prefix$this$suffix", create)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2019 23:56.
 * Current Version: 1.0 (04.06.2019 - 04.06.2019)
 */

fun String.save(file: File) {
    val temp = File("$file.temp")
    FileWriter(temp).also {
        it.write(this)
        it.flush()
        it.close()
    }
    Files.move(temp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
}

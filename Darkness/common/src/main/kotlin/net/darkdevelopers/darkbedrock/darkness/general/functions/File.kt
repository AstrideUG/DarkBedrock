/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("FileUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.nio.file.StandardCopyOption

/*
 * Created on 04.06.2019 23:55.
 * @author Lars Artmann | LartyHD
 */

private const val defaultSuffix = ".json"

fun String.toConfigData(
    file: String,
    prefix: String = "",
    suffix: String = defaultSuffix,
    create: Boolean = true
): ConfigData = File(this).toConfigData(file, prefix, suffix, create)

fun File.toConfigData(
    file: String,
    prefix: String = "",
    suffix: String = defaultSuffix,
    create: Boolean = true
): ConfigData = file.toConfigData(this, prefix, suffix, create)

fun String.toConfigData(
    directory: File,
    prefix: String = "",
    suffix: String = defaultSuffix,
    create: Boolean = true
): ConfigData = ConfigData(directory, "$prefix$this$suffix", create)

fun String.save(file: File) {
    val temp = File("$file.temp")
    FileWriter(temp).also {
        it.write(this)
        it.flush()
        it.close()
    }
    Files.move(temp.toPath(), file.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE)
}

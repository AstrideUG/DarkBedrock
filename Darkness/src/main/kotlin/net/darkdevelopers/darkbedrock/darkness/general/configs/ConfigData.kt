package net.darkdevelopers.darkbedrock.darkness.general.configs

import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:42.
 * Last edit 02.06.2018
 */
@Suppress("MemberVisibilityCanBePrivate", "CanBeParameter")
data class ConfigData(val directory: File, val fileName: String) {

    val file: File = File(directory, fileName)

    constructor(directory: String, fileName: String) : this(File(directory), fileName)

    init {
        createFoldersIfNotExists(directory)
        createFileIfNotExists(file)
    }

    companion object {
        fun createFileIfNotExists(folder: File) = createFileIfNotExists(folder, {}, {})

        fun createFileIfNotExists(file: File, onSuccess: () -> Unit, onFail: () -> Unit) = if (!file.exists())
            if (file.createNewFile())
                onSuccess()
            else
                onFail()
        else onSuccess()

        fun createFoldersIfNotExists(folder: File) = createFoldersIfNotExists(folder, {}, {})

        fun createFoldersIfNotExists(folder: File, onSuccess: () -> Unit, onFail: () -> Unit) = if (!folder.exists())
            if (folder.mkdirs())
                onSuccess()
            else
                onFail()
        else onSuccess()
    }
}
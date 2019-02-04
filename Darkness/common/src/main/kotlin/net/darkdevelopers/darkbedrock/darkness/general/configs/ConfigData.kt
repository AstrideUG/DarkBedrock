/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs

import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData.Companion.createIfNotExists
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:42.
 * Last edit 20.10.2018
 */
@Suppress("MemberVisibilityCanBePrivate")
data class ConfigData(
    val directory: File,
    val fileName: String,
    /**
     * @author Lars Artmann | LartyHD
     *
     * If 'true', create folders and file if not exists on init
     *
     * @see createIfNotExists(File, File)
     * @since 15.10.2018
     */
    val create: Boolean = true
) {

    val file: File = File(directory, fileName)

    constructor(directory: String, fileName: String, create: Boolean = true) : this(File(directory), fileName, create)
    @Deprecated("Ends erroneously with .json", ReplaceWith("ConfigData(directory, \"config.json\")"))
    constructor(directory: File) : this(directory, "config.json")

    @Suppress("DEPRECATION")
    @Deprecated("Use a Deprecated constructor", ReplaceWith("ConfigData(directory, \"config.json\")"))
    constructor(directory: String) : this(File(directory))

    init {
        if (create) createIfNotExists(directory, file)
    }

    /**
     * @author Lars Artmann | LartyHD
     *
     * Check if this {@see file} exists
     *
     * @since 15.10.2018
     */
    fun exists(): Boolean = file.exists()

    companion object {

        /**
         * @author Lars Artmann | LartyHD
         *
         * Create folders and file if not exists on init
         * Calls the functions {@see createFoldersIfNotExists(directory)} and {@see createFileIfNotExists(file)}
         *
         * @since 15.10.2018
         */
        fun createIfNotExists(directory: File, file: File) {
            createFoldersIfNotExists(directory)
            createFileIfNotExists(file)
        }

        /**
         * @author Lars Artmann | LartyHD
         *
         * Check if {@see directory} and {@see file} exists
         *
         * @since 15.10.2018
         */
        fun exists(directory: File, file: File): Boolean = directory.exists() && file.exists()

        fun createFileIfNotExists(file: File): Unit = createFileIfNotExists(file, {}, {})

        fun createFileIfNotExists(file: File, available: () -> Unit, onFail: () -> Unit): Unit = if (!file.exists())
            if (file.createNewFile())
                available()
            else
                onFail()
        else available()

        fun createFoldersIfNotExists(directory: File): Unit = createFoldersIfNotExists(directory, {}, {})

        fun createFoldersIfNotExists(directory: File, available: () -> Unit, onFail: () -> Unit): Unit =
            if (!directory.exists())
                if (directory.mkdirs())
                    available()
                else
                    onFail()
            else available()

    }
}
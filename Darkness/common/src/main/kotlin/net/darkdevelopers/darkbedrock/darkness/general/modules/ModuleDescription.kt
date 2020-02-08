/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules

import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:14.
 * Last edit 16.04.2018
 */
data class ModuleDescription(
    val name: String,
    val version: String,
    val author: String,
    val description: String,
    val async: Boolean = false
) {
    lateinit var folder: File
}
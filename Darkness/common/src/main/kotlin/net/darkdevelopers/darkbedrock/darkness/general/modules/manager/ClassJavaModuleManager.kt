/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules.manager

import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2018 11:46.
 * Last edit 13.05.2019
 */
class ClassJavaModuleManager(folder: File) {
    val classModuleManager = ClassModuleManager(File("$folder${File.separator}class"))
    val javaModuleManager = JavaModuleManager(File("$folder${File.separator}jar"))
}
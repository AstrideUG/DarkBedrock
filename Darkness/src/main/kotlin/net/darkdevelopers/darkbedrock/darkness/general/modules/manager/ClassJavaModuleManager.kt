package net.darkdevelopers.darkbedrock.darkness.general.modules.manager

import java.io.File
import java.lang.reflect.Field

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.06.2018 11:46.
 * Last edit 04.06.2018
 */
class ClassJavaModuleManager(folder: File, lambdas: Array<(Field) -> Unit> = arrayOf()) {
    val classModuleManager = ClassModuleManager(File("$folder${File.separator}class"), lambdas)
    val javaModuleManager = JavaModuleManager(File("$folder${File.separator}jar"), lambdas)
}
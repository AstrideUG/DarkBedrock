/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules.manager

import net.darkdevelopers.darkbedrock.darkness.general.modules.loader.ModuleClassLoader
import java.io.File
import java.io.IOException
import java.util.*
import java.util.jar.JarFile

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:26.
 * Last edit 13.05.2019
 */
class JavaModuleManager(folder: File) : ModuleManager(folder) {
    private val modulesToLoad: MutableMap<String, String> = mutableMapOf()
    private val properties: String = "module.properties"

    init {
        run()
    }

    override fun detectModules() = createFolders({
        addModules(folder)
        if (modulesToLoad.isEmpty())
            println("No class modules in the folder")
        else
            println("Found class modules: $modulesToLoad")


    }, { throw IOException("The folder java modules could not be created") })

    override fun loadModules() {
        for (module in modulesToLoad) {
            val folder = File("$folder${File.separator}${module.key}")
            val loader = ModuleClassLoader(arrayOf(folder.toURI().toURL()), javaClass.classLoader)
            loader.addToClassloaders()
            loadModule(folder, module.value, loader)
        }
        modulesToLoad.clear()
    }

    private fun addModules(file: File) {
        if (file.isFile) {
            val name = file.name
            if (name.endsWith(".jar")) {
                try {
                    val loadModuleProperties = loadModuleProperties(file)
                        ?: throw NullPointerException("module.properties can not be null")
                    if (name in modulesToLoad)
                        System.err.println("Two modules named \"$name\" were found in the java module folder")
                    else
                        this.modulesToLoad[name] = loadModuleProperties.getProperty("main")
                } catch (ex: Throwable) {
                    println("Could not load java module with gameName $name")
                    ex.printStackTrace()
                }
            }
        } else for (listFile in file.listFiles()) addModules(listFile)
    }

    @Suppress("DEPRECATION")
    private fun loadModuleProperties(file: File): Properties? {
        val jarFile = JarFile(file)
        try {
            jarFile.use {
                val moduleYML = jarFile.getJarEntry(properties)
                    ?: throw NullPointerException("Java module must have a $properties")
                return Properties().apply { load(jarFile.getInputStream(moduleYML)) }
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return null
    }
}


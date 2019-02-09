/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules.manager

import java.io.File
import java.io.IOException
import java.lang.reflect.Field

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:26.
 * Last edit 19.04.2018 (03.02.2019)
 */
class JavaModuleManager(folder: File, lambdas: Array<(Field) -> Unit> = arrayOf()) : ModuleManager(folder, lambdas) {
    private val modulesToLoad: MutableMap<String, String> = HashMap()

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
        for (module in modulesToLoad) loadModule(File("$folder${module.key}"), module.value)
        modulesToLoad.clear()
    }

    private fun addModules(file: File) {
        if (file.isFile) {
            val name = file.name
            if (name.endsWith(".jar")) {
                try {
                    //TODO: FIX IT
//                    val loadModuleProperties = loadModuleProperties(file)
//                        ?: throw NullPointerException("module.properties can not be null")
//                    if (modulesToLoad.contains(name))
//                        System.err.println("Two modules named \"$name\" were found in the java module folder")
//                    else
//                        this.modulesToLoad[name] = loadModuleProperties.getString("main")
                } catch (ex: Throwable) {
                    println("Could not load java module with gameName $name")
                    ex.printStackTrace()
                }
            }
        } else for (listFile in file.listFiles()) addModules(listFile)
    }

    //TODO: FIX IT
//    @Suppress("DEPRECATION")
//    private fun loadModuleProperties(file: File): YamlConfiguration? {
//        val jarFile = JarFile(file)
//        try {
//            val properties = "module.properties"
//            val moduleYML = jarFile.getJarEntry(properties)
//                ?: throw NullPointerException("Java module must have a $properties")
//            val config = YamlConfiguration()
//            config.load(jarFile.getInputStream(moduleYML))
//            return config
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        } finally {
//            jarFile.close()
//        }
//        return null
//    }
}


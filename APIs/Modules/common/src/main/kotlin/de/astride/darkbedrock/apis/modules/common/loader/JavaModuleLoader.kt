/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.modules.common.loader

import java.io.File


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:26.
 * Last edit 06.12.2018
 */
class JavaModuleLoader(directory: File) : ModuleLoader(directory) {

    val modulesToLoad = mutableMapOf<String, String>()
    override val detectedModules get() = modulesToLoad.keys
    override val type: String = javaClass.simpleName.drop("ModuleLoader".length)

    override fun loadModules() {
        modulesToLoad.forEach { loadModule(directory, it.value) }
        modulesToLoad.clear()
    }

    /*companion object {
        */
    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 06.12.2018 00:12.
     * Current Version: 1.0 (06.12.2018 - 06.12.2018)
     *//*
        const val descriptionFile: String = "module-info.json"
    }

    override fun load(source: Path): ModuleDescription = loadSerializedDescription(source.toFile()).let { description ->
        ModuleClassLoader(arrayOf(source.toUri().toURL())).let { loader ->
            loader.addToClassloaders()
            description.toClassModuleDescription(loader.loadClass(description.main))
        }
    }

    private fun loadSerializedDescription(file: File): SerializedModuleDescription = JarFile(file).use { jarFile ->
        val entry = jarFile.getJarEntry(descriptionFile)
            ?: throw InvalidModuleException("Did not find a valid $descriptionFile.")
        val inputStream = jarFile.getInputStream(entry)
        InputStreamReader(inputStream).use { return Gson().fromJson(it, SerializedModuleDescription::class.java) }
    }*/
}


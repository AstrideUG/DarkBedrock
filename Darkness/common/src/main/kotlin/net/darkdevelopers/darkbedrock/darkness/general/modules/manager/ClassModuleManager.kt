/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules.manager

import java.io.File
import java.io.IOException


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:26.
 * Last edit 13.05.2019
 */
class ClassModuleManager(folder: File) : ModuleManager(folder) {
    private val modulesToLoad: MutableSet<String> = mutableSetOf()

    init {
        run()
    }

    override fun detectModules() = createFolders({
        addModules(folder)
        if (modulesToLoad.isEmpty())
            println("No class modules in the folder")
        else
            println("Found class modules: $modulesToLoad")
    }, { throw IOException("The folder class modules could not be created") })

    override fun loadModules() {
        for (module in modulesToLoad) loadModule(folder, module)
        modulesToLoad.clear()
    }

    private fun addModules(file: File) {
        if (file.isFile) {
            var name = file.name
            if (name.endsWith(".class") && !name.contains('$')) {
                name = name.substring(0, name.length - 6)
                if (modulesToLoad.contains(name))
                    System.err.println("Two modules named \"$name\" were found in the class module folder")
                else
                    modulesToLoad.add(name)
            }
        } else for (listFile in file.listFiles()) addModules(listFile)
    }

}


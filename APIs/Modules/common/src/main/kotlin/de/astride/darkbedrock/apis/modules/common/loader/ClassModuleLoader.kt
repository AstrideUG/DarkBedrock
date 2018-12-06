/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.modules.common.loader

import de.astride.darkbedrock.apis.modules.api.events.ModulesLoadedEvent
import java.io.File


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:26.
 * Last edit 06.12.2018
 */
class ClassModuleLoader(directory: File) : ModuleLoader(directory) {

    override val detectedModules = mutableSetOf<String>()
    override val type = javaClass.simpleName.drop("ModuleLoader".length)

    override fun loadModules() {
        detectedModules.forEach { loadModule(directory, it) }
        eventManager.fireAndForget(ModulesLoadedEvent(detectedModules))
        detectedModules.clear()
    }

}


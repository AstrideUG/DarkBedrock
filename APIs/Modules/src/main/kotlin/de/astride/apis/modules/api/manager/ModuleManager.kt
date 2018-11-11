/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules.api.manager

import de.astride.apis.modules.api.interfaces.ModuleContainer
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.11.2018 12:21.
 * Current Version: 1.0 (05.11.2018 - 05.11.2018)
 */
interface ModuleManager {

	fun getModule(instance: Any): ModuleContainer?

	fun getModule(id: String): ModuleContainer?

	fun getModules(): Collection<ModuleContainer>

	fun isLoaded(id: String): Boolean

	fun addToClasspath(module: Any, path: Path)

}
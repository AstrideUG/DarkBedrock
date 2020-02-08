/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.api.manager

import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleContainer
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
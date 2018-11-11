/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.modules.base.plugin.loader

import de.astride.apis.modules.api.interfaces.ModuleContainer
import de.astride.apis.modules.api.interfaces.ModuleDescription
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 22:58.
 *
 * This interface is used for loading modules.
 *
 * Current Version: 1.0 (06.11.2018 - 06.11.2018)
 */
interface ModuleLoader {

	fun loadModule(source: Path): ModuleDescription

	fun createModule(module: ModuleDescription): ModuleContainer

}

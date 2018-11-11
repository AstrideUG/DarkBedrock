/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules.base

import de.astride.apis.modules.api.interfaces.ModuleContainer
import de.astride.apis.modules.api.interfaces.ModuleDescription

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 22:59.
 * Current Version: 1.0 (06.11.2018 - 07.11.2018)
 */
data class BaseModuleContainer(override val description: ModuleDescription, override val instance: Any?) : ModuleContainer
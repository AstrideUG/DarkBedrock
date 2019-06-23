/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.common.implementations

import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleContainer
import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleDescription

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 22:59.
 * Current Version: 1.0 (06.11.2018 - 05.12.2018)
 */
data class SimpleModuleContainer(
    override val description: ModuleDescription,
    override val instance: Any
) : ModuleContainer
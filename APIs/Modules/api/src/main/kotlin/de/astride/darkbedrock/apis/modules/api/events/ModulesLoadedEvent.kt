/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.modules.api.events

import de.astride.darkbedrock.apis.modules.api.util.NamedClass

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 15:28.
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
data class ModulesLoadedEvent(val detectedModules: Set<String>) : NamedClass()
/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.modules.common.description

import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleDescription
import de.astride.darkbedrock.apis.modules.api.meta.ModuleDependency
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 00:04.
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
class ClassModuleDescription(
    override val id: String,
    override val name: String,
    override val version: String,
    override val description: String,
    override val url: String,
    override val authors: List<String>,
    override val dependencies: Collection<ModuleDependency>,
    override val source: Path,
    val clazz: Class<*>
) : ModuleDescription {

    init {
        if (id.isBlank()) throw IllegalStateException("id is blank")
        if (name.isBlank()) throw IllegalStateException("name is blank")
    }

}
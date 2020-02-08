/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.common.description

import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleDescription
import de.astride.darkbedrock.apis.modules.api.meta.ModuleDependency
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.12.2018 23:53.
 * Current Version: 1.0 (05.12.2018 - 05.12.2018)
 */
data class SerializedModuleDescription(
    override val id: String,
    override val name: String,
    override val version: String,
    override val description: String,
    override val url: String,
    override val authors: List<String>,
    override val dependencies: Collection<ModuleDependency>,
    override val source: Path,
    val main: String
) : ModuleDescription {

    init {
        if (id.isBlank()) throw IllegalStateException("id is blank")
        if (name.isBlank()) throw IllegalStateException("name is blank")
    }

    fun toClassModuleDescription(clazz: Class<*>): ClassModuleDescription =
        ClassModuleDescription(id, name, version, description, url, authors, dependencies, source, clazz)

}
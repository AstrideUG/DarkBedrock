/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.modules.base.plugin.loader.java

import de.astride.apis.modules.api.meta.ModuleDependency
import de.astride.apis.modules.base.plugin.loader.BaseModuleDescription
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 22:39.
 * Current Version: 1.0 (06.11.2018 - 06.11.2018)
 */
class ClassBaseModuleDescription(
		id: String,
		name: String,
		version: String,
		description: String,
		url: String,
		authors: List<String>,
		dependencies: Collection<ModuleDependency>,
		source: Path,
		val mainClass: Class<*>
) : BaseModuleDescription(
		id,
		name,
		version,
		description,
		url,
		authors,
		dependencies,
		source
)

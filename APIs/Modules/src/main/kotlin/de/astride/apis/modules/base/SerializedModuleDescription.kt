/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.modules.base

import de.astride.apis.modules.api.annotations.Module
import de.astride.apis.modules.api.meta.SimpleVersion
import de.astride.apis.modules.api.meta.Version
import java.util.*

class SerializedModuleDescription private constructor(
		val id: String,
		val name: String,
		val version: Version,
		val description: String,
		val url: String,
		val authors: List<String>,
		val dependencies: List<Dependency>,
		val main: String
) {

	init {
		infix fun String.tryInit(name: String) {
			if (this.isBlank()) throw IllegalStateException("$name is blank")
		}
		id tryInit "id"
		name tryInit "name"
		version.asString() tryInit "version"
		description tryInit "description"
		url tryInit "url"
		main tryInit "main"
	}

	override fun hashCode(): Int = Objects.hash(
			id,
			name,
			version,
			description,
			url,
			authors,
			dependencies,
			main
	)

	data class Dependency(val id: String, val version: Version, val optional: Boolean)

	companion object {

		internal fun from(module: Module, qualifiedName: String) = SerializedModuleDescription(
				module.id,
				module.name,
				SimpleVersion.byString(module.version),
				module.description,
				module.url,
				module.authors.filter { it.isNotBlank() }.toList(),
				ArrayList<Dependency>().apply {
					module.dependencies.forEach {
						this.add(Dependency(
								it.id,
								SimpleVersion.byString(it.version),
								it.optional
						))
					}
				},
				qualifiedName
		)

	}
}

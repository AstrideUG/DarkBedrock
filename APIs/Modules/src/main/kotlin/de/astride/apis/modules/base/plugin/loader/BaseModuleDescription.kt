/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules.base.plugin.loader

import com.google.common.base.MoreObjects
import de.astride.apis.modules.api.interfaces.ModuleDescription
import de.astride.apis.modules.api.meta.ModuleDependency
import java.nio.file.Path
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 22:46.
 * Current Version: 1.0 (06.11.2018 - 06.11.2018)
 */
open class BaseModuleDescription(
		override val id: String,
		override val name: String,
		override val version: String,
		override val description: String,
		override val url: String,
		override val authors: List<String>,
		override val dependencies: Collection<ModuleDependency>,
		override val source: Path?
) : ModuleDescription {

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is BaseModuleDescription) return false

		if (id != other.id) return false
		if (name != other.name) return false
		if (version != other.version) return false
		if (description != other.description) return false
		if (url != other.url) return false
		if (authors != other.authors) return false
		if (dependencies != other.dependencies) return false
		if (source != other.source) return false

		return true
	}

	override fun hashCode(): Int = Objects.hash(id, name, version, description, url, authors, source)

	override fun toString(): String = MoreObjects
			.toStringHelper(this)
			.add("id", id)
			.add("name", name)
			.add("version", version)
			.add("description", description)
			.add("url", url)
			.add("authors", authors)
			.add("dependencies", dependencies)
			.add("source", source)
			.toString()

}
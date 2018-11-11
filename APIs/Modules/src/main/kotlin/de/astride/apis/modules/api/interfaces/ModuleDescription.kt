/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules.api.interfaces

import de.astride.apis.modules.api.meta.ModuleDependency
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.11.2018 12:07.
 * Current Version: 1.0 (05.11.2018 - 07.11.2018)
 */
interface ModuleDescription {

	/**
	 * Gets the qualified ID of the [de.astride.apis.modules.annotations.Module] within this container.
	 *
	 * @return the module ID
	 * @see de.astride.apis.modules.annotations.Module.id
	 */
	val id: String

	/**
	 * Gets the name of the [de.astride.apis.modules.annotations.Module] within this container.
	 *
	 * @return an `null` with the module name, may be empty
	 * @see de.astride.apis.modules.annotations.Module.name
	 */
	val name: String

	/**
	 * Gets the version of the [de.astride.apis.modules.annotations.Module] within this container.
	 *
	 * @return an `null` with the module version, may be empty
	 * @see de.astride.apis.modules.annotations.Module.version
	 */
	val version: String

	/**
	 * Gets the description of the [de.astride.apis.modules.annotations.Module] within this container.
	 *
	 * @return an `null` with the module description, may be empty
	 * @see de.astride.apis.modules.annotations.Module.description
	 */
	val description: String

	/**
	 * Gets the url or website of the [de.astride.apis.modules.annotations.Module] within this container.
	 *
	 * @return an `null` with the module url, may be empty
	 * @see de.astride.apis.modules.annotations.Module.url
	 */
	val url: String

	/**
	 * Gets the authors of the [de.astride.apis.modules.annotations.Module] within this container.
	 *
	 * @return the module authors, may be empty
	 * @see de.astride.apis.modules.annotations.Module.authors
	 */
	val authors: List<String>

	/**
	 * Gets a [Collection] of all dependencies of the [de.astride.apis.modules.annotations.Module] within this container.
	 *
	 * @return the module dependencies, can be empty
	 * @see de.astride.apis.modules.annotations.Module.dependencies
	 */
	val dependencies: Collection<ModuleDependency>

	fun getDependency(id: String): ModuleDependency? = dependencies.singleOrNull { it.id == id }

	/**
	 * Returns the source the module was loaded from.
	 *
	 * @return the source the module was loaded from or `null` if unknown
	 */
	val source: Path?

}
/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.modules.base.plugin.loader

import com.google.gson.Gson
import com.google.inject.Guice
import de.astride.apis.modules.api.exceptions.InvalidModuleException
import de.astride.apis.modules.api.interfaces.ModuleContainer
import de.astride.apis.modules.api.interfaces.ModuleDescription
import de.astride.apis.modules.api.meta.ModuleDependency
import de.astride.apis.modules.base.BaseModuleContainer
import de.astride.apis.modules.base.SerializedModuleDescription
import de.astride.apis.modules.base.plugin.ModuleClassLoader
import de.astride.apis.modules.base.plugin.loader.java.BaseModuleModule
import de.astride.apis.modules.base.plugin.loader.java.ClassBaseModuleDescription
import java.io.BufferedInputStream
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Path
import java.util.*
import java.util.jar.JarEntry
import java.util.jar.JarInputStream

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 23:40.
 * Current Version: 1.0 (06.11.2018 - 09.11.2018)
 */
class JavaModuleLoader(private val baseDirectory: Path) : ModuleLoader {

	private fun toDependencyMeta(dependency: SerializedModuleDescription.Dependency): ModuleDependency = ModuleDependency(
			dependency.id,
			dependency.version,
			dependency.optional
	)

	override fun loadModule(source: Path): ModuleDescription {
		val serialized = getSerializedModuleInfo(source)
				?: throw InvalidModuleException("Did not find a valid velocity-info.json.")
		val loader = ModuleClassLoader(arrayOf(source.toUri().toURL()))
		loader.addToClassloaders()
		val mainClass = loader.loadClass(serialized.main)
		return createDescription(serialized, source, mainClass)
	}

	override fun createModule(description: ModuleDescription): ModuleContainer {
		val javaDescription = description as? ClassBaseModuleDescription
				?: throw IllegalArgumentException("Description provided isn't of the Java plugin loader")
		javaDescription.source ?: throw IllegalArgumentException("No path in plugin description")
		val injector = Guice.createInjector(BaseModuleModule(javaDescription, this.baseDirectory))
		val instance = injector.getInstance(javaDescription.mainClass)
				?: throw IllegalStateException("Got nothing from injector for plugin " + javaDescription.id)
		return BaseModuleContainer(description, instance)
	}

	private fun getSerializedModuleInfo(source: Path): SerializedModuleDescription? =
			JarInputStream(BufferedInputStream(Files.newInputStream(source))).use { inputStream ->
				var entry: JarEntry?
				do {
					entry = inputStream.nextJarEntry
					if (entry.name == "module-info.json") InputStreamReader(inputStream).use {
						return Gson().fromJson(it, SerializedModuleDescription::class.java)
					}
				} while (entry != null)
				return null
			}

	private fun createDescription(
			description: SerializedModuleDescription,
			source: Path,
			mainClass: Class<*>
	): BaseModuleDescription = ClassBaseModuleDescription(
			description.id,
			description.name,
			description.version.asString(),
			description.description,
			description.url,
			description.authors,
			HashSet<ModuleDependency>().apply { description.dependencies.forEach { this@apply.add(toDependencyMeta(it)) } },
			source,
			mainClass
	)
}

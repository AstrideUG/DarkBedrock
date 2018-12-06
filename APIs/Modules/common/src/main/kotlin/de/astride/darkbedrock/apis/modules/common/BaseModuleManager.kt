/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 *//*

package de.astride.darkbedrock.apis.modules.common

import com.google.common.base.Preconditions.checkArgument
import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleContainer
import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleDescription
import de.astride.darkbedrock.apis.modules.api.manager.ModuleManager
import de.astride.darkbedrock.apis.modules.common.loader.JavaModuleLoader
import de.astride.darkbedrock.apis.modules.common.util.ModuleDependencyUtils
import java.nio.file.DirectoryStream
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

*/
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 23:34.
 * Current Version: 1.0 (06.11.2018 - 09.11.2018)
 *//*

class BaseModuleManager : ModuleManager {
	companion object {
		private val logger = LogManager.getLogger(BaseModuleManager::class.java)
	}

	private val modules = HashMap<String, ModuleContainer>()
	private val moduleInstances = IdentityHashMap<Any, ModuleContainer>()

	override fun getModule(instance: Any): ModuleContainer? = if (instance is ModuleContainer) instance else moduleInstances[instance]

	override fun getModule(id: String): ModuleContainer? = modules[id]

	override fun getModules(): Collection<ModuleContainer> = Collections.unmodifiableCollection(modules.values)

	override fun isLoaded(id: String): Boolean = modules.containsKey(id) && moduleInstances.asSequence().singleOrNull { it.value.description.id == id } != null

	override fun addToClasspath(module: Any, path: Path) {
		checkArgument(moduleInstances.containsKey(module), "module is not loaded")
		val moduleClassLoader = module.javaClass.classLoader as? ModuleClassLoader
				?: throw UnsupportedOperationException("Operation is not supported on non ModuleClassLoader.")
		moduleClassLoader.addPath(path)
	}

	fun loadModules(directory: Path) {
		checkArgument(directory.toFile().isDirectory, "provided path isn't a directory")
		Files.newDirectoryStream(directory) { it.toFile().isFile }.apply {
			val loader = JavaModuleLoader(directory)
			loadModules(this@apply).forEach { (fileType, list) ->
				if (list.isEmpty()) return@forEach
				ModuleDependencyUtils.sortCandidates(list).forEach moduleLoad@{ description ->
					var dependencyError = false
					description.dependencies.filter {
						!it.optional &&
								!isLoaded(it.id) &&
								it.version < (modules[it.id]?.description?.version ?: return@filter false)
					}.forEach {
						logger.error("")
						logger.error("Can't load $fileType module ${description.id} due to missing dependency ${it.id}")
						logger.error("Installed version: ${description.version}")
						logger.error("Requested version: ${it.version}")
						logger.error("")
						dependencyError = true
					}
					if (!dependencyError) try {
						registerModule(loader.createModule(description))
					} catch (throwable: Throwable) {
						logger.error("Can't create module ${description.id}", throwable)
					}
				}
			}
		}
	}

	private fun loadModules(stream: DirectoryStream<Path>) = mutableMapOf<String, List<ModuleDescription>>().apply map@{
		val string = stream.toString()
		when {
			string.endsWith(".jar") -> this@map[JarFileTypeLoader::class.java.name] = JarFileTypeLoader().loadModule()
			string.endsWith(".class") -> this@map[ClassFileTypeLoader::class.java.name] = ClassFileTypeLoader().loadModule()
			string.endsWith(".java") -> this@map[JavaFileTypeLoader::class.java.name] = JavaFileTypeLoader().loadModule()
			string.endsWith(".kt") -> this@map[KtFileTypeLoader::class.java.name] = KtFileTypeLoader().loadModule()
			else -> logger.debug("Can not be include module by file: $string")
		}

	}.toMap()

	private fun registerModule(module: ModuleContainer) {
		modules[module.description.id] = module
		moduleInstances[module.instance ?: return] = module
	}

	*/
/**
	 * @author Lars Artmann | LartyHD
	 * Created by Lars Artmann | LartyHD on 09.11.2018 21:51.
	 * Current Version: 1.0 (09.11.2018 - 09.11.2018)
 *//*

	private interface FileTypeLoader {

		fun loadModule(): List<ModuleDescription>

		fun loadModule(
				fileType: FileTypeLoader,
				stream: DirectoryStream<Path>,
				lambda: (Path) -> ModuleDescription
		) = mutableListOf<ModuleDescription>().apply {
			stream.use {
				it.forEach { path ->
					try {
						this@apply.add(lambda(path))
					} catch (ex: Exception) {
						logger.error("Unable to load ${fileType.type()} module $path", ex)
					}
				}
			}
		}

		fun type(): String = javaClass.simpleName.substring(0, javaClass.simpleName.length - "FileTypeLoader".length).toUpperCase()

	}

	*/
/**
	 * @author Lars Artmann | LartyHD
	 * Created by Lars Artmann | LartyHD on 09.11.2018 22:07.
	 * Current Version: 1.0 (09.11.2018 - 09.11.2018)
 *//*

	private class JarFileTypeLoader : FileTypeLoader {

		override fun loadModule(loader: JavaModuleLoader, stream: DirectoryStream<Path>): List<ModuleDescription> {
			return loadModule(this, stream) { loader.loadModule(it) }
		}

	}

	*/
/**
	 * @author Lars Artmann | LartyHD
	 * Created by Lars Artmann | LartyHD on 09.11.2018 22:08.
	 * Current Version: 1.0 (09.11.2018 - 09.11.2018)
 *//*

	private class ClassFileTypeLoader : FileTypeLoader {

		override fun loadModule(): List<ModuleDescription> {
			return listOf()
		}

	}

	*/
/**
	 * @author Lars Artmann | LartyHD
	 * Created by Lars Artmann | LartyHD on 09.11.2018 22:08.
	 * Current Version: 1.0 (09.11.2018 - 09.11.2018)
 *//*

	private class JavaFileTypeLoader : FileTypeLoader {

		override fun loadModule(): List<ModuleDescription> {
			return listOf()
		}

	}

	*/
/**
	 * @author Lars Artmann | LartyHD
	 * Created by Lars Artmann | LartyHD on 09.11.2018 22:08.
	 * Current Version: 1.0 (09.11.2018 - 09.11.2018)
 *//*

	private class KtFileTypeLoader : FileTypeLoader {

		override fun loadModule(): List<ModuleDescription> {
			return listOf()
		}

	}

}
*/

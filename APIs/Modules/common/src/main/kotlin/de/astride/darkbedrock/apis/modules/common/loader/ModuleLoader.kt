/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.modules.common.loader

import com.google.inject.Guice
import de.astride.darkbedrock.apis.events.api.EventManager
import de.astride.darkbedrock.apis.events.common.SimpleEventManager
import de.astride.darkbedrock.apis.modules.api.annotations.Module
import de.astride.darkbedrock.apis.modules.api.events.ModuleLoadedEvent
import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleContainer
import de.astride.darkbedrock.apis.modules.api.meta.ModuleDependency
import de.astride.darkbedrock.apis.modules.api.meta.SimpleVersion
import de.astride.darkbedrock.apis.modules.common.description.ClassModuleDescription
import de.astride.darkbedrock.apis.modules.common.implementations.SimpleModuleContainer
import de.astride.darkbedrock.apis.modules.common.inject.InjectorModule
import java.io.File
import java.net.URLClassLoader
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:26.
 * Last edit 19.12.2018
 */
abstract class ModuleLoader(val directory: File) {

    companion object {
        //        val logger: Logger = LoggerFactory.getLogger(ModuleLoader::class.java)

        /**
         * @author Lars Artmann | LartyHD
         * Created by Lars Artmann | LartyHD on 05.12.2018 23:43.
         *
         * @throws IllegalArgumentException is injector.getInstance(description.mainClass) returns null
         *
         * Current Version: 1.0 (05.12.2018 - 19.12.2018)
         */
        internal fun create(description: ClassModuleDescription, eventManager: EventManager): ModuleContainer {
            val injector = Guice.createInjector(InjectorModule(description, description.source, eventManager))
            val instance = injector.getInstance(description.clazz)
                ?: throw IllegalStateException("Got nothing from injector for module " + description.id)
            return SimpleModuleContainer(description, instance)
        }
    }

    protected abstract val detectedModules: MutableSet<String>
    protected abstract val type: String
    protected val eventManager: EventManager = SimpleEventManager()
    private val modules: MutableSet<ModuleContainer> = HashSet()

    abstract fun loadModules()

    fun detectModules() = createFolders({
        addModules(directory)
        if (detectedModules.isEmpty()) /*logger.info*/ println("No modules in the directory: $directory by type $type")
        else /*logger.info*/ println("Found modules in the directory: $directory: $detectedModules by type $type")
    }, { /*logger.error*/System.err.println("The directory \"$directory\" could not be created") })

    protected fun loadModule(folder: File, clazz: String) {
        try {
            val loader = URLClassLoader(arrayOf(folder.toURI().toURL()), javaClass.classLoader)
            val rawClass = checkModule(loader.loadClass(clazz) ?: return)
            val module = rawClass.getDeclaredAnnotation(Module::class.java)
            module.moduleLog("Loading...")
            val description = ClassModuleDescription(
                module.id,
                module.name,
                module.version,
                module.description,
                module.url,
                module.authors.toList(),
                module.dependencies.map {
                    ModuleDependency(it.id, SimpleVersion.byString(it.version), it.optional)
                },
                folder.toPath().resolve(module.name),
                rawClass
            )
            val moduleContainer = create(description, eventManager)
            eventManager.register(moduleContainer.instance, moduleContainer.instance)
            modules.add(moduleContainer)
            /*logger.info*/println(
                """

                Module infos:
                ID: ${description.id}
                Name: ${description.name}
                Version: ${description.version}
                Description: ${description.description}
                URL: ${description.url}
                Authors: ${description.authors}
                Dependencies: ${description.dependencies}
                Source: ${description.source}
                Class: ${description.clazz}

                """.trimIndent()
            )
            module.moduleLog("Loaded")
            eventManager.fireAndForget(ModuleLoadedEvent(moduleContainer))
        } catch (ex: Throwable) {
            ex.printStackTrace()
        }
    }

    private fun addModules(file: File) {
        val name = file.name
        if (file.isFile) {
            if (name.endsWith(".${type.toLowerCase()}") && !name.contains('$')) {
                val module = name.dropLast(type.length + 1)
                if (detectedModules.contains(module))
                /*logger.error*/ System.err.println("Two \"$type\" modules with the name \"$module\" were found in directory \"$directory\"")
                else {
                    detectedModules.add(module)
                    /*logger.info*/println("Added \"$module\" to \"detectedModules\" by \"$type\" in directory \"$directory\"")
                }
            }
        } else file.listFiles().forEach { addModules(it) }
    }

    private fun Module.moduleLog(executed: String) =
            /*logger.info*/println("$executed module $name (ID: $name) version $version by ${Arrays.toString(authors)}")

    private fun createFolders(onSuccess: () -> Unit, onFail: () -> Unit) = if (!directory.exists())
        if (directory.mkdirs()) onSuccess() else onFail()
    else onSuccess()

    fun getModule(name: String): Any? = modules.find { it.toModule().name.toLowerCase() == name.toLowerCase() }

    private fun Any.toModule(): Module = this.javaClass.getDeclaredAnnotation(Module::class.java)

    private fun checkModule(clazz: Class<*>): Class<*> =
        if (!clazz.isAnnotationPresent(Module::class.java)) throw IllegalStateException("Module must have Module Annotation") else clazz

}


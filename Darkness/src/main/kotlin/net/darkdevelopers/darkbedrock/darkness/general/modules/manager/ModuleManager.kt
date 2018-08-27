/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules.manager

import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import org.bukkit.plugin.InvalidPluginException
import java.io.File
import java.lang.reflect.Field
import java.net.URLClassLoader
import kotlin.concurrent.thread


@Suppress("MemberVisibilityCanBePrivate")
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:26.
 * Last edit 19.04.2018
 */
abstract class ModuleManager(val folder: File, val lambdas: Array<(Field) -> Unit> = arrayOf()) {
    val modules: MutableSet<Module> = HashSet()

    protected fun run() {
        detectModules()
        loadModules()
        addToShutdownHook()
        startModules()
    }

    protected abstract fun detectModules()

    protected abstract fun loadModules()

    protected fun loadModule(folder: File, clazz: String) {
        try {
            val loader = URLClassLoader(arrayOf(folder.toURI().toURL()), javaClass.classLoader)
            val rawClass = loader.loadClass(clazz) ?: return
            val module = isModule(rawClass)
//            module.declaredFields.forEach {
//                if (it.isAnnotationPresent(Injector::class.java)) {
//                    it.isAccessible = true
//                    lambdas.forEach { lambda -> lambda(it) }
//                }
//            }
            val moduleClass = module.newInstance()
            moduleClass.description.folder = File("$folder${File.separator}$clazz")
            call(moduleClass, "Loaded") { it.load() }
            modules.add(moduleClass)
        } catch (ex: Throwable) {
            ex.printStackTrace()
        }
    }

    private fun isModule(clazz: Class<*>): Class<out Module> = try {
        clazz.asSubclass(Module::class.java)
    } catch (ex: ClassCastException) {
        throw InvalidPluginException("main class `$clazz' does not extend Module", ex)
    }

    protected fun startModules() = call("Started") { it.start() }

    protected fun addToShutdownHook() = Runtime.getRuntime().addShutdownHook(Thread { callSync("Stopped") { it.stop() } })

    protected fun call(executed: String, lambda: (Module) -> Unit) {
        for (module in modules) call(module, executed, lambda)
    }

    protected fun callSync(executed: String, lambda: (Module) -> Unit) {
        for (module in modules) {
            lambda(module)
            printExecutedMessage(module, executed)
        }
    }

    protected fun call(module: Module, executed: String, lambda: (Module) -> Unit) {
        if (module.description.async) thread { lambda(module) } else lambda(module)
        printExecutedMessage(module, executed)
    }

    protected fun printExecutedMessage(module: Module, executed: String) {
        val description = module.description
        println("$executed module${if (module.description.async) " asynchronous " else " "}${description.name} version ${description.version} by ${description.author}")
    }

    protected fun createFolders(onSuccess: () -> Unit, onFail: () -> Unit) {
        if (!folder.exists())
            if (folder.mkdirs())
                onSuccess()
            else
                onFail()
        else onSuccess()
    }

    fun getModule(name: String): Module? {
        modules.forEach { if (it.description.name.toLowerCase() == name.toLowerCase()) return it }
        return null
    }

}


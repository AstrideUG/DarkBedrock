/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules.loader

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.05.2019 18:13.
 *
 * from Velocity (https://github.com/VelocityPowered/Velocity/blob/bc70c76aec68a83431d6d1500d2d31a1c15f9963/proxy/src/main/java/com/velocitypowered/proxy/plugin/ModuleClassLoader.java)
 *
 * Current Version: 1.0 (13.05.2019 - 13.05.2019)
 */

import java.net.MalformedURLException
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path
import java.util.concurrent.CopyOnWriteArraySet

class ModuleClassLoader(urls: Array<URL>, classLoader: ClassLoader) : URLClassLoader(urls, classLoader) {

    fun addToClassloaders(): Boolean = loaders.add(this)

    internal fun addPath(path: Path): Unit = try {
        addURL(path.toUri().toURL())
    } catch (ex: MalformedURLException) {
        throw AssertionError(ex)
    }

    override fun close() {
        loaders.remove(this)
        super.close()
    }

    override fun loadClass(name: String, resolve: Boolean): Class<*> = loadClass0(name, resolve, true)

    private fun loadClass0(name: String, resolve: Boolean, checkOther: Boolean): Class<*> {
        try {
            return super.loadClass(name, resolve)
        } catch (ignored: ClassNotFoundException) {
            // Ignored: we'll try others
        }

        if (checkOther)
            for (loader in loaders)
                if (loader !== this) try {
                    return loader.loadClass0(name, resolve, false)
                } catch (ignored: ClassNotFoundException) {
                    // We're trying others, safe to ignore
                }

        throw ClassNotFoundException(name)
    }

    companion object {

        private val loaders = CopyOnWriteArraySet<ModuleClassLoader>()

        init {
            ClassLoader.registerAsParallelCapable()
        }
    }
}
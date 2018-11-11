/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.modules.base.plugin

import java.net.MalformedURLException
import java.net.URL
import java.net.URLClassLoader
import java.nio.file.Path
import java.util.concurrent.CopyOnWriteArraySet

class ModuleClassLoader(urls: Array<URL>) : URLClassLoader(urls) {

	fun addToClassloaders() {
		loaders.add(this)
	}

	internal fun addPath(path: Path) {
		try {
			addURL(path.toUri().toURL())
		} catch (e: MalformedURLException) {
			throw AssertionError(e)
		}
	}

	@Throws(ClassNotFoundException::class)
	override fun loadClass(name: String, resolve: Boolean): Class<*> = loadClass0(name, resolve, true)

	@Throws(ClassNotFoundException::class)
	private fun loadClass0(name: String, resolve: Boolean, checkOther: Boolean): Class<*> {
		try {
			return super.loadClass(name, resolve)
		} catch (ignored: ClassNotFoundException) {
			// Ignored: we'll try others
		}

		if (checkOther) {
			loaders.filter { it !== this }.forEach {
				try {
					return it.loadClass0(name, resolve, false)
				} catch (ignored: ClassNotFoundException) {
					// We're trying others, safe to ignore
				}
			}
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

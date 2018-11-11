/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules.base

import de.astride.apis.modules.api.annotations.Module

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.09.2018 01:20.
 * Last edit 05.11.2018
 */
class Modules {

	init {


	}

	fun isModuleAnnotationPresent(clazz: Class<*>) = clazz.isAnnotationPresent(Module::class.java)

}

/**
 * @author Lars Artmann | LartyHD
 *
 * Stops the Server when a [Throwable] is caught
 *
 * @since 05.11.2018
 */
fun security(action: () -> Unit): Unit = try {
	action()
} catch (throwable: Throwable) {
	throwable.printStackTrace()
	System.err.println()
	System.err.println("For security reasons, the server is shutdown!")
	System.err.println()
}

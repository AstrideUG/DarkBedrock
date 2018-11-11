/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.08.2018 21:03.
 * Last edit 17.10.2018
 */

@Deprecated("Use printInitInfo(Class<*>)", ReplaceWith("printInitInfo(javaClass)", "net.darkdevelopers.darkbedrock.darkness.general.functions.printInitInfo"))
fun Any.printInitInfo(): Unit = net.darkdevelopers.darkbedrock.darkness.general.functions.printInitInfo(javaClass)

/**
 * @author Lars Artmann | LartyHD
 *
 * Prints init infos
 *
 * @LastEdit 17.10.2018
 * @since 30.08.2018
 */
fun printInitInfo(javaClass: Class<*>) {
	val name = javaClass.name.split('.')
	val subClasses = name[name.size - 2].split('$')
	val subClass = if (subClasses.size > 1) " of \"${subClasses[subClasses.size - 1]}\" " else " "
	val extends = if (javaClass.isMemberClass) "sub class of ${javaClass.superclass}" else ""
	val type = when {
		javaClass.isEnum -> "enum"
		javaClass.isInterface -> "interface"
		javaClass.isAnnotation -> "annotation"
		javaClass.isAnonymousClass -> "anonymous class"
		else -> "class"
	}
	println("Init the $type \"${javaClass.simpleName}\"$subClass$extends")
}

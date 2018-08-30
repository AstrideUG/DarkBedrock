/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 30.08.2018 21:03.
 * Last edit 30.08.2018
 */
fun Any.printInitInfo() {
    val name = javaClass.name.split('.')
    val subClasses = name[name.size - 1].split('$')
    val subClass = if (subClasses.size > 1) " of \"${subClasses[subClasses.size - 1]}\" " else " "
    val extends = if (javaClass.isMemberClass) "sub class of ${javaClass.superclass}" else ""
    println("Init the class \"${javaClass.simpleName}\"$subClass$extends")
}

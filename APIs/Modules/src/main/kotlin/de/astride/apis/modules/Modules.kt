/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules

import de.astride.apis.modules.annotations.Module

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.09.2018 01:20.
 * Last edit 06.09.2018
 */
class Modules {

    init {
    }

    fun isModuleAnnotationPresent(clazz: Class<*>) = clazz.isAnnotationPresent(Module::class.java)

}
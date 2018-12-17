/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.api.scanner

import java.lang.reflect.Method

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 00:28.
 * Current Version: 1.0 (13.12.2018 - 15.12.2018)
 */
open class AnnotationMethodScanner<A : Annotation>(private val annotation: Class<A>) : MethodScanner<Class<*>> {

    override fun register(any: Class<*>, method: Method): Boolean = method.isAnnotationPresent(annotation)

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 15.12.2018 06:12.
     * Current Version: 1.0 (15.12.2018 - 15.12.2018)
     */
    fun registerWithAnnotation(clazz: Class<*>): Map<Method, A> =
        register(clazz).associateWith { it.getAnnotation(annotation) }

}


/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.api.scanner

import java.lang.reflect.Method

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 00:19.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */
interface MethodScanner<C : Any> {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 13.12.2018 00:23.
     * Current Version: 1.0 (13.12.2018 - 13.12.2018)
     */
    fun register(any: C, method: Method): Boolean

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 13.12.2018 23:43.
     *
     * @return a [List] with all methods of the class which return the [register] method `true`
     *
     * Current Version: 1.0 (13.12.2018 - 13.12.2018)
     */
    fun register(any: C): List<Method> = any.javaClass.methods.filter { register(any, it) }

}
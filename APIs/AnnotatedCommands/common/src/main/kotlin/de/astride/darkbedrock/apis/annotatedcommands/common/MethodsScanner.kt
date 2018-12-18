/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common

import de.astride.darkbedrock.apis.annotatedcommands.api.Implementation
import de.astride.darkbedrock.apis.annotatedcommands.api.Permission
import de.astride.darkbedrock.apis.annotatedcommands.api.scanner.AnnotationMethodScanner
import java.lang.reflect.Method

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 23:52.
 * Current Version: 1.0 (13.12.2018 - 18.12.2018)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 23:55.
 * Current Version: 1.0 (13.12.2018 - 18.12.2018
 */
object ImplementationAMS : AnnotationMethodScanner<Implementation>(Implementation::class.java) {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 17.12.2018 09:02.
     * Current Version: 1.0 (17.12.2018 - 17.12.2018)
     */
    fun findMethods(clazz: Class<*>, args: List<String>): List<Pair<Method, Implementation>> =
        registerWithAnnotation(clazz).entries.filter { isAcceptable(args, it.key, it.value) }.map { it.toPair() }

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 17.12.2018.
     * Current Version: 1.0 (17.12.2018 - 17.12.2018)
     */
//    @Deprecated(
//        "Use findMethods", ReplaceWith(
//            "findMethods(clazz, args).firstOrNull()?.key",
//            "de.astride.darkbedrock.apis.annotatedcommands.common.ImplementationAMS.findMethods"
//        )
//    )
    fun findMethod(clazz: Class<*>, args: List<String>): Method? = findMethods(clazz, args).firstOrNull()?.first

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 17.12.2018 09:04.
     * Current Version: 1.0 (17.12.2018 - 18.12.2018)
     */
    private fun isAcceptable(args: List<String>, method: Method, implementation: Implementation): Boolean {
        val aArgs = implementation.args
        if (aArgs.size != args.size) return false
        if (args.isEmpty()) return method.parameterCount == 0
        return args.all { sArg ->

            //TODO ADD Mapping Support
            val result = getParameter(method, args)
            result ?: return@all false

            aArgs.any { arg ->
                val aliases = arg.values.map { it.aliases + it.value }
                arg.isInput || aliases.any { sArg in it }
            }
        }
    }

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 18.12.2018 04:36.
     * Current Version: 1.0 (18.12.2018 - 18.12.2018)
     */
    fun getParameter(method: Method, args: List<String>): Array<*>? {
        val parameters = method.parameters
        return when {
            method.parameterCount == 0 -> emptyArray<String>()
            parameters[0].type == Array<String>::class.java -> args.toTypedArray()
            method.parameterCount == args.size -> {
                val output = mutableListOf<Any>()
                var index = -1
                parameters.all {
                    val sArg = args[index++]
                    val mapped = try {
                        val type = it.type
                        try {
                            val function = Implementation.MAPS.entries.find { (clazz, _) ->
                                clazz == type
                            }?.value ?: throw NullPointerException()
                            function(sArg)
                        } catch (throwable: Throwable) {
                            type.cast(sArg)
                        }
                    } catch (throwable: Throwable) {
                        null
                    }
                    if (mapped != null) output.add(mapped) else false
                }
                output.toTypedArray()
            }
            else -> null
        }
    }

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 23:55.
 * Current Version: 1.0 (13.12.2018 - 13.12.2018)
 */
object PermissionAMS : AnnotationMethodScanner<Permission>(Permission::class.java)
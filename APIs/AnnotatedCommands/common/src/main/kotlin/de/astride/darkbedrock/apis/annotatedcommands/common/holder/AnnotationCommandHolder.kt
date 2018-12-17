/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common.holder

import de.astride.darkbedrock.apis.annotatedcommands.api.*
import de.astride.darkbedrock.apis.annotatedcommands.common.ImplementationAMS
import java.lang.reflect.Method

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 13.12.2018 04:14.
 * Current Version: 1.0 (13.12.2018 - 15.12.2018)
 */
class AnnotationCommandHolder(private val argSeparator: String) : HashSet<Class<*>>(), CommandHolder {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 13.12.2018 04:04.
     *
     * @throws IllegalArgumentException if [element] has no [Command] [Annotation]
     * @throws IllegalArgumentException If [element] has a [Method] with [Annotation] [Implementation] and this has an [Arg] with a [Value] by finding [argSeparator]
     *
     * In German:
     * @throws IllegalArgumentException Wenn [element] eine [Method] hat mit der [Annotation] [Implementation] und diese ein [Arg], mit einem [Value] hat, indem [argSeparator] gefunden wurde
     *
     * Current Version: 1.0 (13.12.2018 - 15.12.2018)
     */
    override fun add(element: Class<*>): Boolean {
        if (!element.javaClass.isAnnotationPresent(Command::class.java))
            throw IllegalArgumentException("Command ($element) has no [de.astride.darkbedrock.apis.annotatedcommands.api.Command] annotation")

        ImplementationAMS.registerWithAnnotation(element).checkValues()

        return super.add(element)
    }

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 15.12.2018 07:49.
     * Current Version: 1.0 (15.12.2018 - 15.12.2018)
     */
    private fun Map<Method, Implementation>.checkValues() = entries.forEach { (method, annotation) ->
        annotation.args.forEach { arg ->
            arg.values.forEach {
                if (argSeparator in it.aliases + it.value)
                    throw IllegalArgumentException("Found \"$argSeparator\" in \"$it\", in \"$annotation\", in \"$arg\", by \"$method\"")
            }
        }
    }

}
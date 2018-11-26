/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.api

import java.math.BigDecimal
import java.math.BigInteger
import java.util.*
import kotlin.reflect.KClass

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 19.11.2018 22:12.
 *
 * Moved on the 20.11.2018 at 02:15 from Commands.kt to CommandsAnnotations.kt
 *
 * Current Version: 1.0 (19.11.2018 - 25.11.2018)
 */
//TODO ADD intelligentCompletion
//TODO ADD isLastVarArgs

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 01:49.
 *
 * @param name If this is blank, then the name is the name of the class
 *
 * Current Version: 1.0 (20.11.2018 - 20.11.2018)
 */
@Target(AnnotationTarget.CLASS)
annotation class Command(
    val name: String = "",
    val aliases: Array<String> = []
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 18:27.
 *
 * WARNING: This [Annotation] may only be used once per [Command]
 * WARNING: [Function] or field must be static and return [String]
 *
 * Current Version: 1.0 (25.11.2018 - 25.11.2018)
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.FIELD)
annotation class CommandName

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 18:29.
 *
 * WARNING: [Function] must be return [Iterable]
 *
 * Current Version: 1.0 (25.11.2018 - 25.11.2018)
 */
@Target(AnnotationTarget.FUNCTION)
annotation class CommandAddAliases


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 01:55.
 * Current Version: 1.0 (20.11.2018 - 25.11.2018)
 */
@Target(AnnotationTarget.FUNCTION)
annotation class CommandOnly

/**
 * @author Lars Artmann | LartyHD
 *
 * This [Annotation] identifies a [Function] as [SubCommand]
 *
 * @param args can not be empty!
 * @throws IllegalArgumentException if [args] is empty
 *
 * Current Version: 1.0 (19.11.2018 - 25.11.2018)
 */
@Target(AnnotationTarget.FUNCTION)
annotation class SubCommand(val args: Array<Arg>) {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 25.11.2018 19:53.
     * Current Version: 1.0 (25.11.2018 - 25.11.2018)
     */
    companion object {

        /**
         * @author Lars Artmann | LartyHD
         * Created by Lars Artmann | LartyHD on 25.11.2018 19:53.
         * Current Version: 1.0 (25.11.2018 - 25.11.2018)
         */
        @JvmField
        val CASTS = HashMap<KClass<*>, (String) -> Any>()

        /**
         * @author Lars Artmann | LartyHD
         * Created by Lars Artmann | LartyHD on 25.11.2018 22:22.
         * Current Version: 1.0 (25.11.2018 - 25.11.2018)
         */
        init {
            SubCommand
                .addCast(Boolean::class) { it.toBoolean() }
                .addCast(Byte::class) { it.toByte() }
                .addCast(Short::class) { it.toShort() }
                .addCast(Int::class) { it.toInt() }
                .addCast(Long::class) { it.toLong() }
                .addCast(BigInteger::class) { it.toBigInteger() }
                .addCast(Float::class) { it.toFloat() }
                .addCast(Double::class) { it.toDouble() }
                .addCast(BigDecimal::class) { it.toBigDecimal() }
                .addCast(UByte::class) { it.toUByte() }
                .addCast(UShort::class) { it.toUShort() }
                .addCast(UInt::class) { it.toUInt() }
                .addCast(ULong::class) { it.toULong() }
                .addCast(UUID::class) { UUID.fromString(it) }
        }

        /**
         * @author Lars Artmann | LartyHD
         * Created by Lars Artmann | LartyHD on 25.11.2018 22:05.
         * Current Version: 1.0 (25.11.2018 - 25.11.2018)
         */
        fun <O : Any> addCast(input: KClass<O>, block: (String) -> O): Companion {
            CASTS[input] = block
            return this
        }

    }

}

/**
 * @author Lars Artmann | LartyHD
 *
 * @param values can not be empty!
 * @param isInput if it is `true` the arg(s) is not constant
 *
 * Current Version: 1.0 (19.11.2018 - 19.11.2018)
 */
annotation class Arg(
    val values: Array<Value>,
    val isInput: Boolean = false
)

/**
 * @author Lars Artmann | LartyHD
 * Current Version: 1.0 (20.11.2018 - 20.11.2018)
 */
annotation class Value(
    val value: String,
    val aliases: Array<String> = []
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 01:52.
 *
 * This [Annotation] contains the permission values for commands ([Command], [CommandOnly], [SubCommand])
 *
 * @param value possible variables "<CommandName>, <ArgN>, <Args>"
 * @param value If it is empty, it will be ignored
 *
 * Current Version: 1.0 (20.11.2018 - 25.11.2018)
 */
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION)
annotation class Permission(val value: String = "<Args>")

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 07:35.
 * Current Version: 1.0 (20.11.2018 - 20.11.2018)
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class Name

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 07:35.
 * Current Version: 1.0 (20.11.2018 - 20.11.2018)
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class Label(val inputCase: Boolean = false)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 07:35.
 * Current Version: 1.0 (20.11.2018 - 20.11.2018)
 */
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FIELD)
annotation class Sender(val failMessage: String = "You are not the requested sender!")

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 08:24.
 * Current Version: 1.0 (20.11.2018 - 20.11.2018)
 */
@Target(AnnotationTarget.FUNCTION)
annotation class SendUsageIfThrownException

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 10:03.
 * Current Version: 1.0 (20.11.2018 - 20.11.2018)
 */
@Target(AnnotationTarget.FUNCTION) //FUNCTION must be Static
annotation class FailMessage(val type: Type) {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 20.11.2018 10:10.
     * Current Version: 1.0 (20.11.2018 - 20.11.2018)
     */
    enum class Type { CAST_TO_SENDER }

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.11.2018 00:40.
 *
 * [Function]s with this [Annotation] must return [Boolean]
 *
 * Current Version: 1.0 (22.11.2018 - 22.11.2018)
 */
@Target(AnnotationTarget.FUNCTION)
annotation class ExternalPermissionCheck(val `for`: Array<String>)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 22.11.2018 00:46.
 * Current Version: 1.0 (22.11.2018 - 22.11.2018)
 */
@Target(AnnotationTarget.FUNCTION)
annotation class FunctionID(val value: String)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 18:56.
 * Current Version: 1.0 (25.11.2018 - 25.11.2018)
 */
@Target(AnnotationTarget.CLASS)
annotation class PermissionsAddon(
    val prefix: String = "",
    val suffix: String = ""
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.11.2018 19:05.
 *
 * WARNING: [value] can not be negative
 *
 * Current Version: 1.0 (25.11.2018 - 25.11.2018)
 */
@Target(AnnotationTarget.FUNCTION)
annotation class UseArgsFrom(val value: Int)

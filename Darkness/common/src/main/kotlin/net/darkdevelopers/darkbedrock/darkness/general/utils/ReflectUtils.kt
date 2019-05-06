/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.general.utils

import java.lang.reflect.Field
import java.lang.reflect.Modifier

object ReflectUtils {
    fun setValue(any: Any, name: String, value: Any): Unit? = try {
        getField(any.javaClass, name)?.set(any, value)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    @Deprecated("Use getValue(Class<*>, Any, String)", ReplaceWith("getValue(any.javaClass, any, name)"))
    fun getValue(any: Any, name: String): Any? = try {
        getField(any.javaClass, name)?.get(any)
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }

    fun getValue(clazz: Class<*>, any: Any, name: String): Any? = try {
        getField(clazz, name)?.get(any)
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }

    @Suppress("UNCHECKED_CAST")
    fun <O : Any?> getValueAs(any: Any, name: String): O? = getValue(any.javaClass, any, name) as? O

    @Suppress("MemberVisibilityCanBePrivate")
    fun getField(clazz: Class<*>, name: String): Field? = try {
        val field = clazz.getAccessibleField(name)
        if (Modifier.isFinal(field.modifiers))
            getField(Field::class.java, "modifiers")?.set(field, field.modifiers and Modifier.FINAL.inv())
        field
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }

}

fun Any.setValue(name: String, value: Any): Unit =
    printException { javaClass.getModifiableField(name)?.set(this, value) }

@Suppress("UNCHECKED_CAST")
fun <O : Any?> Any.getValueAs(name: String): O? = this.getValue(name) as? O

fun Any.getValue(name: String): Any? {
    printException { return javaClass.getModifiableField(name)?.get(this) }
    return null
}

fun <T : Any> Class<T>.getModifiableField(name: String): Field? {
    printException {
        val field = javaClass.getAccessibleField(name)
        if (Modifier.isFinal(field.modifiers))
            Field::class.java.getField("modifiers")?.set(field, field.modifiers and Modifier.FINAL.inv())
        return field
    }
    return null
}

private fun <T : Any> Class<T>.getAccessibleField(name: String): Field =
    getDeclaredField(name).apply { isAccessible = true }

private inline fun printException(lambda: () -> Unit) = try {
    lambda()
} catch (ex: Exception) {
    ex.printStackTrace()
}


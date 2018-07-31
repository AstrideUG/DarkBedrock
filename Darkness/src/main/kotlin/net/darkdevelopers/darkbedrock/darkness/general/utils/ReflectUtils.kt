/*
 * © Copyright - DarkBlocks.net | Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.general.utils

import java.lang.reflect.Field
import java.lang.reflect.Modifier

object ReflectUtils {
    fun setValue(any: Any, name: String, value: Any) = try {
        getField(any.javaClass, name)?.set(any, value)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    fun getValue(any: Any, name: String) = try {
        getField(any.javaClass, name)?.get(any)
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }

    @Suppress("UNCHECKED_CAST")
    fun <O : Any?> getValueAs(any: Any, name: String) = getValue(any, name) as? O

    @Suppress("MemberVisibilityCanBePrivate")
    fun getField(clazz: Class<*>, name: String): Field? = try {
        val field = getAccessibleField(clazz, name)
        if (Modifier.isFinal(field.modifiers))
            getField(Field::class.java, "modifiers")?.set(field, field.modifiers and Modifier.FINAL.inv())
        field
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }

    private fun getAccessibleField(clazz: Class<*>, name: String): Field {
        val field = clazz.getDeclaredField(name)
        field.isAccessible = true
        return field
    }
}


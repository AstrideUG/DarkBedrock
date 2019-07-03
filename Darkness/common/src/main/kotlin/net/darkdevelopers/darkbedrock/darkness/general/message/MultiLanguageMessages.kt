/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

/**
 * Created on 03.07.2019 01:37.
 * @author Lars Artmann | LartyHD
 */
import java.text.MessageFormat
import java.util.*

open class MultiLanguageMessages {

    private val messageFormatCache = mutableMapOf<String, MessageFormat>()

    @Suppress("unused")
    fun msg(locale: Locale, string: String, vararg objects: Any): String = msg(getBundle(locale), string, objects)

    @Suppress("MemberVisibilityCanBePrivate")
    fun msg(bundle: ResourceBundle, string: String, vararg objects: Any): String =
        if (objects.isEmpty()) bundle.getString(string) else format(bundle, string, *objects)

//    @Suppress("unused")
//    private fun format(locale: Locale, string: String, vararg objects: Any): String =
//        format(getBundle(locale), string, *objects)

    protected fun format(bundle: ResourceBundle, string: String, vararg objects: Any): String {
        val format = bundle.getString(string)
        val messageFormat =
            messageFormatCache[format] ?: MessageFormat(format).apply { messageFormatCache[format] = this }
        return messageFormat.format(objects)
    }

    private fun getBundle(locale: Locale): ResourceBundle = ResourceBundle.getBundle("messages", locale)

}
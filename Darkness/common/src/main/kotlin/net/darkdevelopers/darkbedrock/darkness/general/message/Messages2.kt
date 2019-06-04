/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.JsonObject
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.functions.castedInstanceAndNotNull
import net.darkdevelopers.darkbedrock.darkness.general.functions.check
import net.darkdevelopers.darkbedrock.darkness.general.functions.toMap
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 17.10.2018 07:52.
 * Last edit 05.06.2019
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
class Messages2(unsafeInput: Map<String, Any?>, defaultOutput: Map<String, Map<String, String>>) {

    companion object {

        /**
         * @author Lars Artmann | LartyHD
         *
         * The default language key
         *
         * @since 20.10.2018
         */
        const val defaultLanguageKey: String = "en_US"

        /**
         * @author Lars Artmann | LartyHD
         * @return a [Messages2] by [GsonConfig#mapByJson(JsonElement)]
         * @since 20.10.2018
         */
        fun byJson(jsonObject: JsonObject, defaultOutput: Map<String, Map<String, String>>): Messages2 =
            Messages2(jsonObject.toMap(), defaultOutput)

        /**
         * @author Lars Artmann | LartyHD
         *
         * Create [MutableMap<String, V>] changed in [function] and {@return [Map<String, V>]}
         *
         * @since 17.10.2018
         */
        fun <V : Any?> of(function: MutableMap<String, V>.() -> Unit): Map<String, V> =
            net.darkdevelopers.darkbedrock.darkness.general.functions.of(function)

    }

    /**
     * @author Lars Artmann | LartyHD
     *
     * The Map with all values
     *
     * @since 17.10.2018
     */
    val input: Map<String, Any?> = unsafeInput.run {
        fun Map<String, Any?>.checker(): Map<String, Any?> {
            val replaceValuesWithKey = GsonConfig.replaceValuesWithKey(this)
            replaceValuesWithKey.forEach {
                if (it.value.javaClass.kotlin.isInstance(Map::class.java.kotlin)) {
                    val checker =
                        it.javaClass.kotlin.castedInstanceAndNotNull(mapOf<String, Any?>())?.check()?.checker()
                    if (checker != null) return checker
                }
            }
            return replaceValuesWithKey
        }
        this.check().checker()
    }

    /**
     * @author Lars Artmann | LartyHD
     *
     * The [separator] [String] for sub groups
     *
     * @since 17.10.2018
     */
    val separator: String = run {
        val separator = input["separator"] as? String
        if (separator == null || separator == "\\") "." else separator
    }
    /**
     * @author Lars Artmann | LartyHD
     *
     * This language will be default for all user
     *
     * @since 20.10.2018
     */
    val languageKey: String = input["language"] as? String ?: defaultLanguageKey
    /**
     * @author Lars Artmann | LartyHD
     *
     * A [Map<String, Map<String, String>>] of the [languages] with all messages in [Map<String, String>]
     *
     * @since 18.10.2018
     */
    @Suppress("UNCHECKED_CAST")
//	TODO: Check if is the best way
    val languages: Map<String, Map<String, String>> = run {
        val input = input["languages"] as? Map<String, Any> ?: emptyMap()
        of {
            fun Any.forAllMaps() {
                fun Any.maps() = (this as? Map<String, Any?>)?.filter { it.value as? Map<String, Any?> != null }

                this.maps()?.forEach { key, value ->
                    fun Map<String, Any?>.getAllStringsAndPutIn(map: MutableMap<String, String> = mutableMapOf()): Map<String, String> {
                        map.putAll(this.filter { it.value is String }.map { it.key to it.value.toString() }.toMap())
                        return map
                    }

                    val allMaps = value?.maps() ?: return@forEach
                    //TODO: Add separator
                    this@of[key] = allMaps.getAllStringsAndPutIn()
                    allMaps.forAllMaps()
                }
            }
            input.forAllMaps()
        }
    }
    /**
     * @author Lars Artmann | LartyHD
     *
     * This language will be default for all user
     *
     * @since 20.10.2018
     */
    val language: Map<String, String>? = languages[languageKey]
        ?: defaultOutput[languageKey]
        ?: languages[defaultLanguageKey]
        ?: defaultOutput[defaultLanguageKey].toNonNull("language / defaultOutput[defaultLanguageKey]")

    private fun <K, V> Map<K, V>.check() = this.check(
        String::class.java.kotlin,
        String::class.java.kotlin,
        Map::class.java.kotlin,
        arrayOf<Any?>().javaClass.kotlin,
        Double::class.java.kotlin,
        Float::class.java.kotlin,
        Long::class.java.kotlin,
        Int::class.java.kotlin,
        Short::class.java.kotlin,
        Byte::class.java.kotlin,
        Boolean::class.java.kotlin
    )
}



/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs

/**
 * Created on 21.07.2019 03:36.
 * @author Lars Artmann | LartyHD
 */
open class Config(values: Map<String, Any?>) {
    val textFromUrlDefaultTimeoutInMillis by values.default { 1000 }
}
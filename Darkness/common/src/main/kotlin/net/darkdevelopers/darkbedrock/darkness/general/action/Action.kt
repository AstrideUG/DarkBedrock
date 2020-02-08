/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.action

/**
 * Created on 30.04.2019 00:03.
 * @author Lars Artmann | LartyHD
 */
interface Action {

    val group: Any
    val code: (Map<String, Any?>) -> Unit

}
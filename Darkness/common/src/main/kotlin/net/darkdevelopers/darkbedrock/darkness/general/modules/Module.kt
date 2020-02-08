/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.modules

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.04.2018 01:03.
 * Last edit 17.04.2018
 */
interface Module {

    val description: ModuleDescription

    fun load() {}

    fun start() {}

    fun stop() {}

}
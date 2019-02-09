/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:07.
 * Last edit 02.06.2018
 */
interface Config {

    fun load(): Config

    fun save(): Config

    fun <O : Any?> getAs(key: String): O?

    fun <I : Any?> put(key: String, value: I): Config

}
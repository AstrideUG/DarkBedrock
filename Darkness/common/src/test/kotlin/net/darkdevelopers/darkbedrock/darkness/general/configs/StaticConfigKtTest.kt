/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs

import org.junit.Test

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.05.2019 23:45.
 * Last edit 29.05.2019
 */
class StaticConfigKtTest {

    @Test
    fun getDefaultMappings() {
        assert("breakingHandler".replace("[A-Z]".toRegex()) { "-${it.value.toLowerCase()}" } == "breaking-handler")
    }
}
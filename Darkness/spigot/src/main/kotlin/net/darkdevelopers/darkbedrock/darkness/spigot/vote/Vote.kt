/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.vote

import java.util.*

/**
 * Created by LartyHD on 04.01.2018  15:20.
 * Last edit 06.07.2018
 */
data class Vote(val name: String) {
    val voter: MutableSet<String> = HashSet()
}

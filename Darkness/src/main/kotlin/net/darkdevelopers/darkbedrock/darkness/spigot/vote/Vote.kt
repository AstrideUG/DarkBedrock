/*
 * © Copyright - Lars Artmann | LartyHD 2018.
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

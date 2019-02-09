/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.vote.manager

import net.darkdevelopers.darkbedrock.darkness.spigot.vote.listener.VoteListener
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * Created by LartyHD on 04.01.2018 16:53.
 * Last edit 07.07.2018
 */
class VoteManager(javaPlugin: JavaPlugin, maps: Set<String>) {
    private val voteMaps: MutableSet<String> = HashSet()
    private val listener: VoteListener

    init {
        if (maps.size < 3) throw IndexOutOfBoundsException("Not enough maps available (at least 3)")
        addDifferentRandomMapsToVoteMaps(Random(), maps.toList())
        listener = VoteListener(javaPlugin, maps, voteMaps.toTypedArray())
    }

    private fun addDifferentRandomMapsToVoteMaps(random: Random, mapNames: List<String>) {
        if (voteMaps.size == 3) return
        val size = mapNames.size
        voteMaps.apply {
            add(mapNames[random.nextInt(size)])
            add(mapNames[random.nextInt(size)])
            add(mapNames[random.nextInt(size)])
        }
        addDifferentRandomMapsToVoteMaps(random, mapNames)
    }

}

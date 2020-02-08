/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs

import net.darkdevelopers.darkbedrock.darkness.general.functions.toSecondNotNull

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 29.05.2019 13:46.
 * Last edit 29.05.2019
 */
@Suppress("UNCHECKED_CAST")
private class DefaultMap<K, V : Any, D : V>(
    private val map: Map<K, V?>,
    private val default: D?,
    private val mapped: Map<K, D> = map.mapNotNull { (it.key to it.value as? D).toSecondNotNull() }.toMap()
) : Map<K, D> by mapped {
    override fun get(key: K): D? = mapped[key] ?: default
}

infix fun <V : Any, D : V> Map<String, V?>.default(default: () -> D?): Map<String, D> = DefaultMap(this, default())
/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 16:08.
 * Last edit 05.06.2019
 */
@JvmName("toEntryMutableList")
fun <K, V> Map<K, List<V>>.toEntryMutable(): Map<K, MutableList<V>> = map { it.key to it.value.toMutableList() }.toMap()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 16:13.
 * Current Version: 1.0 (05.06.2019 - 05.06.2019)
 */
@JvmName("toEntryMutableSet")
fun <K, V> Map<K, Set<V>>.toEntryMutable(): Map<K, MutableSet<V>> = map { it.key to it.value.toMutableSet() }.toMap()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 16:13.
 * Current Version: 1.0 (05.06.2019 - 05.06.2019)
 */
@JvmName("toEntryMutableMap")
fun <K, MK, MV> Map<K, Map<MK, MV>>.toEntryMutable(): Map<K, MutableMap<MK, MV>> =
    map { it.key to it.value.toMutableMap() }.toMap()
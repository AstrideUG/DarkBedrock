/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

@file:JvmName("MapUtils")

package net.darkdevelopers.darkbedrock.darkness.general.functions

@JvmName("toEntryMutableList")
fun <K, V> Map<K, List<V>>.toEntryMutable(): Map<K, MutableList<V>> = map { it.key to it.value.toMutableList() }.toMap()

@JvmName("toEntryMutableSet")
fun <K, V> Map<K, Set<V>>.toEntryMutable(): Map<K, MutableSet<V>> = map { it.key to it.value.toMutableSet() }.toMap()

@JvmName("toEntryMutableMap")
fun <K, MK, MV> Map<K, Map<MK, MV>>.toEntryMutable(): Map<K, MutableMap<MK, MV>> =
    map { it.key to it.value.toMutableMap() }.toMap()
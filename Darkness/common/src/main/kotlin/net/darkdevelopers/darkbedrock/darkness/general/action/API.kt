/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.action

/**
 * Created on 30.04.2019 00:05.
 * @author Lars Artmann | LartyHD
 */

private val actions: MutableMap<String, MutableCollection<Action>> = mutableMapOf()

fun String.callAction(vars: Map<String, Any?>) {

    val separator = '.'
    val tokens = split(separator)
    var last = ""
    val all = tokens.map { last + separator + it.apply { last = this } }.mapNotNull { actions[this.toLowerCase()] }

    val map = vars + mapOf("id" to this.toLowerCase())
    all.forEach { it.forEach { action -> action.code(map) } }

}

fun String.consume(group: Any, block: (Map<String, Any?>) -> Unit) {
    actions.getOrPut(this.toLowerCase()) { mutableListOf() } += DataAction(group, block)
}

/**
 * if [group] is `null` all actions with is type will be removed
 */
@Suppress("unused")
fun String.unregister(group: Any? = null) {
    if (group == null) actions -= this else actions[this]?.removeIf { it.group === group }
}

/**
 * Remove all from this group
 */
fun Any.unregister(): Unit = actions.forEach { (_, actions) -> actions.removeIf { it.group === this } }
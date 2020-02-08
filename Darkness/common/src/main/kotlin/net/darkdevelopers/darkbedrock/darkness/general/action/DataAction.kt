/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.action

data class DataAction(override val group: Any, override val code: (Map<String, Any?>) -> Unit) : Action
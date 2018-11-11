/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.events

/**
 * Importance of the [EventHandler]. When executing an Event, the handlers
 * are called in order of their Priority.
 */
object EventPriority {
	const val LOWEST: Byte = -64
	const val LOW: Byte = -32
	const val NORMAL: Byte = 0
	const val HIGH: Byte = 32
	const val HIGHEST: Byte = 64
}

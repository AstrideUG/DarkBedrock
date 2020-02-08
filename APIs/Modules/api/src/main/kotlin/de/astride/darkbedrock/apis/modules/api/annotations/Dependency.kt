/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.api.annotations

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.11.2018 12:00.
 * Current Version: 1.0 (05.11.2018 - 05.11.2018)
 */
annotation class Dependency(
		val id: String,
		val version: String,
		val optional: Boolean = false
)
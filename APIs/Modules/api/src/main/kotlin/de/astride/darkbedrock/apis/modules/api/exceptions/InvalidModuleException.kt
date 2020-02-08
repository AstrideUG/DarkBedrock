/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.api.exceptions

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 23:11.
 * Current Version: 1.0 (06.11.2018 - 06.11.2018)
 */
class InvalidModuleException : Exception {

	constructor() : super()

	constructor(message: String) : super(message)

	constructor(message: String, cause: Throwable) : super(message, cause)

	constructor(cause: Throwable) : super(cause)
}

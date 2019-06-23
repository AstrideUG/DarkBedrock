/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.api.annotations

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.09.2018 01:28.
 * Current Version: 1.0 (06.09.2018 - 05.11.2018)
 */
@Target(AnnotationTarget.CLASS)
annotation class Module(
    val id: String,
    val name: String,
    val version: String,
    val authors: Array<String>,
    val description: String = "",
    val url: String = "",
    val dependencies: Array<Dependency> = []
)
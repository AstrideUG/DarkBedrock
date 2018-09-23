/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules.annotations

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.09.2018 01:28.
 * Last edit 06.09.2018
 */
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
annotation class Module(
        val name: String,
        val version: String,
        val author: String,
        val description: String,
        val async: Boolean = false
)
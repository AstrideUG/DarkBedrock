/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.modules.api.annotations

import com.google.inject.BindingAnnotation

/**
 * This annotation requests that Velocity inject a [java.nio.file.Path] instance with a
 * plugin-specific data directory.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@BindingAnnotation
annotation class DataDirectory

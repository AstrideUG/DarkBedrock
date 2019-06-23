/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.api.annotations

import com.google.inject.BindingAnnotation

/**
 * This annotation requests that Velocity inject a [java.nio.file.Path] instance with a
 * plugin-specific data directory.
 */
@Target(AnnotationTarget.FIELD, AnnotationTarget.VALUE_PARAMETER)
@BindingAnnotation
annotation class DataDirectory

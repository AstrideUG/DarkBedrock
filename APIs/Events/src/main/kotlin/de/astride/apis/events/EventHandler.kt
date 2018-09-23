/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.events

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
annotation class EventHandler(
        /**
         * Define the priority of the event handler.
         *
         *
         * Event handlers are called in order of priority:
         *
         *  1. LOWEST
         *  2. LOW
         *  3. NORMAL
         *  4. HIGH
         *  5. HIGHEST
         *
         */
        val priority: Byte = EventPriority.NORMAL
)

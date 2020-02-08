/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.events.api


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 07:00.
 *
 * Indicates an event that has a result attached to it.
 *
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
interface ResultedEvent<R : ResultedEvent.Result> {

    var result: R

    /**
     * Represents a result for an event.
     */
    interface Result {

        /**
         * Returns whether or not the event is allowed to proceed. Plugins may choose to skip denied
         * events, and the proxy will respect the result of this method.
         *
         * @return whether or not the event is allowed to proceed
         */
        val isAllowed: Boolean
    }

    /**
     * A generic "allowed/denied" result.
     */
    class GenericResult private constructor(override val isAllowed: Boolean) : Result {

        override fun toString(): String = if (isAllowed) "allowed" else "denied"

        companion object {

            private val ALLOWED = GenericResult(true)
            private val DENIED = GenericResult(false)

            fun allowed(): GenericResult = ALLOWED

            fun denied(): GenericResult = DENIED
        }
    }

    /**
     * Represents an "allowed/denied" result with a reason allowed for denial.
     */
    class ReasonResult private constructor(override val isAllowed: Boolean, private val reason: String?) : Result {

        override fun toString(): String = when {
            isAllowed -> "allowed"
            reason != null -> "denied: $reason"
            else -> "denied"
        }

        companion object {

            private val ALLOWED = ReasonResult(true, null)

            fun allowed(): ReasonResult = ALLOWED

            fun denied(reason: String): ReasonResult = ReasonResult(false, reason)
        }
    }
}
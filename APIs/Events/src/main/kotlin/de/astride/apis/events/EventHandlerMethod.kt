/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.apis.events

import java.lang.reflect.Method

data class EventHandlerMethod(val listener: Any, val method: Method? = null) {

    operator fun invoke(event: Any) = method!!.invoke(listener, event)

}

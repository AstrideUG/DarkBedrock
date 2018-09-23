/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.events

import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import java.text.MessageFormat
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import java.util.logging.Level
import java.util.logging.Logger

class EventBus @JvmOverloads constructor(logger: Logger? = null) {
    private val byListenerAndPriority = HashMap<Class<*>, Map<Byte, Map<Any, Array<Method>>>>()
    private val byEventBaked = ConcurrentHashMap<Class<*>, Array<EventHandlerMethod>>()
    private val lock = ReentrantLock()
    private val logger: Logger = logger ?: Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)

    fun post(event: Any) {
        val handlers = this.byEventBaked[event.javaClass]
        if (handlers != null) for (method in handlers) {
            try {
                method.invoke(event)
            } catch (ex: IllegalAccessException) {
                throw Error("Method became inaccessible: $event", ex)
            } catch (ex: IllegalArgumentException) {
                throw Error("Method rejected target/argument: $event", ex)
            } catch (ex: InvocationTargetException) {
                this.logger.log(Level.WARNING, MessageFormat.format("Error dispatching event {0} to listener {1}", event, method.listener), ex.cause)
            }

        }
    }

    private fun findHandlers(listener: Any): Map<Class<*>, Map<Byte, Set<Method>>> {
        val handler = HashMap<Class<*>, Map<Byte, Set<Method>>>()
        for (m in listener.javaClass.declaredMethods) {
            val annotation = m.getAnnotation(EventHandler::class.java)
            if (annotation != null) {
                val params = m.parameterTypes
                if (params.size != 1) {
                    this.logger.log(Level.INFO, "Method {0} in class {1} annotated with {2} does not have single argument", arrayOf(m, listener.javaClass, annotation))
                    continue
                }
//                var prioritiesMap: MutableMap<Byte, Set<Method>>? = handler[params[0]]
//                if (prioritiesMap == null) {
//                    prioritiesMap = HashMap()
//                    handler[params[0]] = prioritiesMap
//                }
//                var priority: MutableSet<Method>? = prioritiesMap[annotation.priority()]
//                if (priority == null) {
//                    priority = HashSet()
//                    prioritiesMap[annotation.priority()] = priority
//                }
//                priority.add(m)
            }
        }
        return handler
    }

    fun register(listener: Any) {
        val handler = findHandlers(listener)
        this.lock.lock()
        try {
            for ((key, value) in handler) {
//                var prioritiesMap: MutableMap<Byte, Map<Any, Array<Method>>>? = this.byListenerAndPriority[key]
//                if (prioritiesMap == null) {
//                    prioritiesMap = HashMap()
//                    this.byListenerAndPriority[key] = prioritiesMap
//                }
//                for ((key1, value1) in value) {
//                    var currentPriorityMap: MutableMap<Any, Array<Method>>? = prioritiesMap[key1]
//                    if (currentPriorityMap == null) {
//                        currentPriorityMap = HashMap()
//                        prioritiesMap[key1] = currentPriorityMap
//                    }
//                    val baked = arrayOfNulls<Method>(value1.size)
//                    currentPriorityMap[listener] = value1.toTypedArray()
//                }
//                bakeHandlers(key)
            }
        } finally {
            this.lock.unlock()
        }
    }

    fun unregister(listener: Any) {
        val handler = findHandlers(listener)
        this.lock.lock()
        try {
            for ((key, value) in handler) {
                val prioritiesMap = this.byListenerAndPriority[key]
                if (prioritiesMap != null) {
                    for (priority in value.keys) {
//                        val currentPriority = prioritiesMap[priority]
//                        if (currentPriority != null) {
//                            currentPriority.remove(listener)
//                            if (currentPriority.isEmpty()) {
//                                prioritiesMap.remove(priority)
//                            }
//                        }
                    }
                    if (prioritiesMap.isEmpty()) {
                        this.byListenerAndPriority.remove(key)
                    }
                }
                bakeHandlers(key)
            }
        } finally {
            this.lock.unlock()
        }
    }

    /**
     * Shouldn't be called without first locking the writeLock; intended for use
     * only inside [register(Object)][.register] or
     * [unregister(Object)][.unregister].
     */
    private fun bakeHandlers(eventClass: Class<*>) {
        val handlersByPriority = this.byListenerAndPriority[eventClass]
        if (handlersByPriority != null) {
            val handlersList = ArrayList<EventHandlerMethod>(handlersByPriority.size * 2)
            // Either I'm really tired, or the only way we can iterate between Byte.MIN_VALUE and Byte.MAX_VALUE inclusively,
            // with only a byte on the stack is by using a do {} while() format loop.
            var value = java.lang.Byte.MIN_VALUE
            do {
                val handlersByListener = handlersByPriority[value]
                if (handlersByListener != null) {
                    for ((key, value1) in handlersByListener) {
                        for (method in value1) {
                            val ehm = EventHandlerMethod(key, method)
                            handlersList.add(ehm)
                        }
                    }
                }
            } while (value++ < java.lang.Byte.MAX_VALUE)
            this.byEventBaked[eventClass] = handlersList.toTypedArray()
        } else {
            this.byEventBaked.remove(eventClass)
        }
    }
}

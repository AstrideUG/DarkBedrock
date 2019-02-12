/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.events.common

//import org.apache.logging.log4j.LogManager
import com.google.common.collect.Multimaps
import com.google.common.util.concurrent.ThreadFactoryBuilder
import de.astride.darkbedrock.apis.events.api.EventHandler
import de.astride.darkbedrock.apis.events.api.EventManager
import de.astride.darkbedrock.apis.events.api.Subscribe
import net.kyori.event.EventSubscriber
import net.kyori.event.SimpleEventBus
import net.kyori.event.method.MethodScanner
import net.kyori.event.method.MethodSubscriptionAdapter
import net.kyori.event.method.SimpleMethodSubscriptionAdapter
import net.kyori.event.method.asm.ASMEventExecutorFactory
import org.slf4j.LoggerFactory
import java.lang.reflect.Method
import java.util.*
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.function.Predicate


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 07:13.
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */

class SimpleEventManager : EventManager {

    private val registeredListenersByGroup = Multimaps.synchronizedListMultimap(
        Multimaps.newListMultimap(IdentityHashMap<Any, Collection<Any>>()) { ArrayList() }
    )
    private val registeredHandlersByGroup = Multimaps.synchronizedListMultimap(
        Multimaps.newListMultimap(IdentityHashMap<Any, Collection<Any>>()) { ArrayList() }
    )
    private val bus: SimpleEventBus<Any>
    private val methodAdapter: MethodSubscriptionAdapter<Any>
    private val service: ExecutorService

    init {
        bus = object : SimpleEventBus<Any>(Any::class.java) {
            override fun shouldPost(event: Any, subscriber: EventSubscriber<*>): Boolean = true
        }
        methodAdapter = SimpleMethodSubscriptionAdapter(
            bus, ASMEventExecutorFactory(javaClass.classLoader), VelocityMethodScanner()
        )
        service = Executors.newCachedThreadPool(
//            Runtime.getRuntime().availableProcessors(),
            ThreadFactoryBuilder().setNameFormat("Velocity Event Executor - #%d").setDaemon(true).build()
        )
    }

    override fun register(group: Any, listener: Any) {
//        if (group === listener && registeredListenersByGroup.containsEntry(group, group))
//            throw IllegalArgumentException("The group main instance is automatically registered.")
//
        listener.javaClass.declaredMethods.forEach {
            if (it.isAnnotationPresent(com.google.common.eventbus.Subscribe::class.java))
                logger.error("Method ${listener.javaClass.name}#${it.name} has a Guava @Subscribe annotation. Use the Events @Subscribe annotation instead.")
        }

        registeredListenersByGroup.put(group, listener)
        methodAdapter.register(listener)
    }

    override fun <E> register(group: Any, eventClass: Class<E>, postOrder: Int, handler: EventHandler<E>) =
        bus.register(eventClass, KyoriToVelocityHandler(handler, postOrder))

    override fun <E : Any> fire(event: E): CompletableFuture<E> {
        if (!bus.hasSubscribers(event.javaClass)) return CompletableFuture.completedFuture(event)

        val eventFuture = CompletableFuture<E>()
        service.execute {
            val result = bus.post(event)
            if (!result.exceptions().isEmpty()) {
                logger.error("Some errors occurred whilst posting event $event.")
                var i = 0
                result.exceptions().forEach { _, throwable -> logger.error("#${i++}: \n", throwable) }
            }
            eventFuture.complete(event)
        }
        return eventFuture
    }

    private fun unregisterHandler(handler: EventHandler<*>) =
        bus.unregister(Predicate { it is KyoriToVelocityHandler<*> && it.handler === handler })

    override fun unregisterListeners(group: Any) {
        val listeners = registeredListenersByGroup.removeAll(group)
        listeners.forEach(methodAdapter::unregister)
        val handlers = registeredHandlersByGroup.removeAll(group) as List<EventHandler<Any>>
        handlers.forEach(this::unregisterHandler)
    }

    override fun unregisterListener(group: Any, listener: Any) {
        registeredListenersByGroup.remove(group, listener)
        methodAdapter.unregister(listener)
    }

    override fun <E> unregister(group: Any, handler: EventHandler<E>) {
        registeredHandlersByGroup.remove(group, handler)
        unregisterHandler(handler)
    }

    @Throws(InterruptedException::class)
    fun shutdown(): Boolean {
        service.shutdown()
        return service.awaitTermination(10, TimeUnit.SECONDS)
    }

    private class VelocityMethodScanner : MethodScanner<Any> {

        override fun shouldRegister(listener: Any, method: Method): Boolean =
            method.isAnnotationPresent(Subscribe::class.java)

        override fun postOrder(listener: Any, method: Method): Int = method.getAnnotation(Subscribe::class.java).order

        override fun consumeCancelledEvents(listener: Any, method: Method): Boolean = true
    }

    private class KyoriToVelocityHandler<E>(
        val handler: EventHandler<E>,
        private val postOrder: Int
    ) : EventSubscriber<E> {

        override operator fun invoke(event: E) = handler.execute(event)

        override fun postOrder(): Int = postOrder
    }

    companion object {

        private val logger = LoggerFactory.getLogger(SimpleEventManager::class.java)

    }
}
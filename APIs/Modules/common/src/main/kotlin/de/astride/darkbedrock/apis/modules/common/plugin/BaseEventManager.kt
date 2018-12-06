///*
// * © Copyright - Lars Artmann aka. LartyHD 2018.
// */
//package de.astride.darkbedrock.apis.modules.common.plugin
//
//import com.google.common.base.Preconditions
//import com.google.common.base.Supplier
//import com.google.common.collect.Multimaps
//import com.google.common.util.concurrent.ThreadFactoryBuilder
//import com.sun.webkit.plugin.PluginManager
//import java.lang.reflect.Method
//import java.util.*
//import java.util.concurrent.CompletableFuture
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//import java.util.concurrent.TimeUnit
//
//class BaseEventManager(private val pluginManager: PluginManager) : EventManager {
//    private val registeredListenersByPlugin =
//        Multimaps.synchronizedListMultimap(Multimaps.newListMultimap(IdentityHashMap<Any, Collection<Any>>()) { ArrayList() })
//	private val registeredHandlersByPlugin = Multimaps
//			.synchronizedListMultimap<Any, EventHandler<*>>(Multimaps.newListMultimap<Any, Any>(IdentityHashMap<Any, Any>(), Supplier<List<Any>> { ArrayList() }))
//	private val bus: SimpleEventBus<Any>
//	private val methodAdapter: MethodSubscriptionAdapter<Any>
//	private val service: ExecutorService
//
//	init {
//		val cl = ModuleClassLoader(arrayOfNulls(0))
//		cl.addToClassloaders()
//		this.bus = SimpleEventBus(Any::class.java)
//		this.methodAdapter = SimpleMethodSubscriptionAdapter(this.bus,
//				ASMEventExecutorFactory(cl),
//				VelocityMethodScanner())
//		this.service = Executors
//            .newFixedThreadPool(
//                Runtime.getRuntime().availableProcessors(), ThreadFactoryBuilder()
//						.setNameFormat("Velocity Event Executor - #%d").setDaemon(true).build())
//	}
//
//	private fun ensurePlugin(plugin: Any) {
//		Preconditions.checkArgument(this.pluginManager.fromInstance(plugin).isPresent(),
//				"Specified plugin is not loaded")
//	}
//
//	fun register(plugin: Any, listener: Any) {
//		ensurePlugin(plugin)
//		Preconditions.checkNotNull(listener, "listener")
//		if (plugin === listener && this.registeredListenersByPlugin.containsEntry(plugin, plugin)) {
//			throw IllegalArgumentException(
//					"Trying to register the plugin main instance. Velocity already takes care of this for you.")
//		}
//		this.registeredListenersByPlugin.put(plugin, listener)
//		this.methodAdapter.register(listener)
//	}
//
//	fun <E> register(plugin: Any, eventClass: Class<E>, postOrder: PostOrder,
//					 handler: EventHandler<E>) {
//		ensurePlugin(plugin)
//		this.bus.register(eventClass, KyoriToVelocityHandler<E>(handler, postOrder))
//	}
//
//	fun <E> fire(event: E?): CompletableFuture<E> {
//		if (event == null) {
//			throw NullPointerException("event")
//		}
//		if (!this.bus.hasSubscribers(event.javaClass)) {
//			// Optimization: nobody's listening.
//			return CompletableFuture.completedFuture(event)
//		}
//		val runEvent = {
//			val result = this.bus.post(event)
//			if (!result.exceptions().isEmpty()) {
//				logger.error("Some errors occurred whilst posting event {}.", event)
//				var i = 0
//				for (exception in result.exceptions().values()) {
//					logger.error("#{}: \n", ++i, exception)
//				}
//			}
//		}
//		val eventFuture = CompletableFuture<E>()
//		this.service.execute {
//			runEvent.run()
//			eventFuture.complete(event)
//		}
//		return eventFuture
//	}
//
//	fun unregisterListeners(plugin: Any) {
//		ensurePlugin(plugin)
//		val listeners = this.registeredListenersByPlugin.removeAll(plugin)
//		listeners.forEach(Consumer<Any> { this.methodAdapter.unregister() })
//		val handlers = this.registeredHandlersByPlugin.removeAll(plugin)
//		handlers
//				.forEach { handler -> this.bus.unregister(KyoriToVelocityHandler<E>(handler, PostOrder.LAST)) }
//	}
//
//	fun unregisterListener(plugin: Any, listener: Any) {
//		ensurePlugin(plugin)
//		Preconditions.checkNotNull(listener, "listener")
//		this.registeredListenersByPlugin.remove(plugin, listener)
//		this.methodAdapter.unregister(listener)
//	}
//
//	fun <E> unregister(plugin: Any, handler: EventHandler<E>) {
//		ensurePlugin(plugin)
//		Preconditions.checkNotNull<Any>(handler, "listener")
//		this.registeredHandlersByPlugin.remove(plugin, handler)
//		this.bus.unregister(KyoriToVelocityHandler<E>(handler, PostOrder.LAST))
//	}
//
//	@Throws(InterruptedException::class)
//	fun shutdown(): Boolean {
//		this.service.shutdown()
//		return this.service.awaitTermination(10, TimeUnit.SECONDS)
//	}
//
//	private class VelocityMethodScanner : MethodScanner<Any> {
//		fun shouldRegister(listener: Any, method: Method): Boolean {
//			return method.isAnnotationPresent(Subscribe::class.java)
//		}
//
//		fun postOrder(listener: Any, method: Method): Int {
//			val annotation = method.getAnnotation(Subscribe::class.java!!) ?: throw IllegalStateException(
//					"Trying to determine post order for listener without @Subscribe annotation")
//			return annotation.order().ordinal()
//		}
//
//		fun consumeCancelledEvents(listener: Any, method: Method): Boolean {
//			return true
//		}
//	}
//
//	private class KyoriToVelocityHandler<E> private constructor(val handler: EventHandler<E>, postOrder: PostOrder) : EventSubscriber<E> {
//		private val postOrder: Int
//
//		init {
//			this.postOrder = postOrder.ordinal()
//		}
//
//		operator fun invoke(event: E) {
//			this.handler.execute(event)
//		}
//
//		fun postOrder(): Int {
//			return this.postOrder
//		}
//
//		override fun equals(o: Any?): Boolean {
//			if (this === o) {
//				return true
//			}
//			if (o == null || javaClass != o.javaClass) {
//				return false
//			}
//			val that = o as KyoriToVelocityHandler<*>?
//			return this.handler == that!!.handler
//		}
//
//		override fun hashCode(): Int {
//			return Objects.hash(this.handler)
//		}
//	}
//
//	companion object {
//		private val logger = LogManager.getLogger(BaseEventManager::class.java)
//	}
//}

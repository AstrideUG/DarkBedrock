package net.darkdevelopers.darkbedrock.darkness.spigot.listener.cancellable


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:55.
 * Last edit 02.06.2018 (15.02.2019 added deprecated)
 */
@Suppress("DEPRECATION")
@Deprecated("Use cancellable.cancel()")
interface DefaultCancellable : Cancellable {

    @Deprecated(
        "Use cancellable.cancel()",
        ReplaceWith("cancellable.cancel()", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel")
    )
    override fun cancel(cancellable: org.bukkit.event.Cancellable) = cancel(cancellable, true)

    @Deprecated(
        "Use cancellable.cancel(boolean)",
        ReplaceWith("cancellable.cancel(boolean)", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel")
    )
    override fun cancel(cancellable: org.bukkit.event.Cancellable, boolean: Boolean) {
        cancellable.isCancelled = boolean
    }

}
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.cancellable

import org.bukkit.event.Cancellable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:55.
 * Last edit 02.06.2018 (15.02.2019 added deprecated)
 */
@Deprecated("Use cancellable.cancel()")
interface Cancellable {

    @Deprecated(
        "Use cancellable.cancel()",
        ReplaceWith("cancellable.cancel()", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel")
    )
    fun cancel(cancellable: Cancellable)

    @Deprecated(
        "Use cancellable.cancel(boolean)",
        ReplaceWith("cancellable.cancel(boolean)", "net.darkdevelopers.darkbedrock.darkness.spigot.functions.cancel")
    )
    fun cancel(cancellable: Cancellable, boolean: Boolean)

}
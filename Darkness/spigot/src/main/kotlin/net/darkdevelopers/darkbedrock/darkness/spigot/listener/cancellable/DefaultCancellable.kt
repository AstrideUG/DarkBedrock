package net.darkdevelopers.darkbedrock.darkness.spigot.listener.cancellable


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:55.
 * Last edit 02.06.2018
 */
interface DefaultCancellable : Cancellable {

    override fun cancel(cancellable: org.bukkit.event.Cancellable) = cancel(cancellable, true)

    override fun cancel(cancellable: org.bukkit.event.Cancellable, boolean: Boolean) {
        cancellable.isCancelled = boolean
    }

}
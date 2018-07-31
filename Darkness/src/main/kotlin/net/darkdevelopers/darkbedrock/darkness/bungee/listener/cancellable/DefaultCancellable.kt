package net.darkdevelopers.darkbedrock.darkness.bungee.listener.cancellable

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:55.
 * Last edit 02.06.2018
 */
interface DefaultCancellable : Cancellable {

    override fun cancel(cancellable: net.md_5.bungee.api.plugin.Cancellable) = cancel(cancellable, true)

    override fun cancel(cancellable: net.md_5.bungee.api.plugin.Cancellable, boolean: Boolean) {
        cancellable.isCancelled = boolean
    }

}
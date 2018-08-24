/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot.inject

import com.google.inject.Binder
import com.google.inject.Module
import com.google.inject.Scopes
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.08.2018 23:39.
 * Last edit 24.08.2018
 */
class InjectorModule : Module {

    override fun configure(binder: Binder) {
        binder.bind(GsonConfig::class.java).`in`(Scopes.SINGLETON)
    }
}
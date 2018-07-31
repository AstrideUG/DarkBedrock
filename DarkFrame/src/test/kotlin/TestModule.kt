//package net.darkdevelopers.darkbedrock.darkframe.modules

import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 04.07.2018 02:47.
 * Last edit 04.07.2018
 */
class TestModule : Module {
    override val description: ModuleDescription = ModuleDescription("TestModule", "1.0", "Lars Artmann | LartyHD", "This is a module for test the module system")

    override fun toString(): String {
        return "TestModule(description=$description)"
    }
}
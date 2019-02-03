package net.darkdevelopers.darkbedrock.darkframe.bungee

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.07.2018 07:30.
 * Last edit 03.07.2018
 */
class DarkFrame : DarkPlugin() {

    private lateinit var moduleManager: ClassJavaModuleManager

    init {
        instance = this
    }

    override fun onEnable() = onEnable {
        moduleManager = ClassJavaModuleManager(dataFolder)
    }

    companion object {
        lateinit var instance: DarkFrame
            private set
    }

}

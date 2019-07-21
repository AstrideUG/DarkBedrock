/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.spigot

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.LoggerContext
import com.google.gson.JsonObject
import com.rollbar.api.payload.data.Server
import com.rollbar.notifier.Rollbar
import com.rollbar.notifier.config.ConfigBuilder.withAccessToken
import de.astride.darkbedrock.apis.modules.common.loader.ClassModuleLoader
import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.CancellablesCommand
import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.ModulesCommand
import net.darkdevelopers.darkbedrock.darkframe.spigot.commands.OldModulesCommand
import net.darkdevelopers.darkbedrock.darkness.general.configs.createConfigs
import net.darkdevelopers.darkbedrock.darkness.general.configs.formatToConfigPattern
import net.darkdevelopers.darkbedrock.darkness.general.configs.toConfigMap
import net.darkdevelopers.darkbedrock.darkness.general.functions.*
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ClassJavaModuleManager
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.loadCancellable
import net.darkdevelopers.darkbedrock.darkness.spigot.events.listener.EventsListener
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.events.plugin
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import net.darkdevelopers.darkbedrock.darkness.spigot.plugin.DarkPlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.slf4j.LoggerFactory
import java.io.File
import java.net.InetAddress
import kotlin.properties.Delegates
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.messages as messagesConfig

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.07.2018 07:30.
 * Last edit 26.04.2019
 */
class DarkFrame : DarkPlugin() {

    private var moduleManager: ClassJavaModuleManager by Delegates.notNull()
    @Suppress("DEPRECATION")
    private val messages = SpigotGsonMessages(dataFolder.toConfigData("config")).availableMessages

    init {
//        if (!KotlinVersion.CURRENT.isAtLeast(1, 2, 61)) throw IllegalStateException("Current KotlinVersion is to low. Use 1.2.61 or higher")
        instance = this
    }

    override fun onLoad() = onLoad {
        performCraftPluginUpdater(
            mapOf(
                "type" to "DarkFrame-Spigot",
                "description" to description,
                "javaplugin" to this
            )
        )
    }

    override fun onEnable() = security {
        val throwable = throwable {
            reportThrowable {
                onEnable {
                    EventsListener.getSimpleInstance(this)
                    plugin = this

                    initConfigs()

                    println("Enable Cancellables command")
                    val configData =
                        CancellablesCommand.javaClass.simpleName.formatToConfigPattern().toConfigData(dataFolder)
                    val values = configData.load<JsonObject>().toMap()
                    CancellablesCommand.setup(this, values)
                    configData.save(values.toConfigMap())
                    println("Enabled Cancellables command")

                    //Old Module System
                    println("Enable Old Module System")
                    moduleManager = ClassJavaModuleManager(File("$dataFolder${File.separator}old"))
                    OldModulesCommand(
                        this,
                        mapOf("Class" to moduleManager.classModuleManager, "Java" to moduleManager.javaModuleManager)
                    )
                    println("Enabled Old Module System")

                    //New Module System
                    println("Enable New Module System")
                    val directory = File("$dataFolder${File.separator}modules")
                    val loader = setOf(ClassModuleLoader(directory)/*, JavaModuleLoader(directory)*/)
                    ModulesCommand(this, loader, messages.map { it.key to it.value.firstOrNull() }.toMap())
                    loader.forEach {
                        it.detectModules()
                        it.loadModules()
                    }
                    println("Enabled New Module System")
                }
            }
        }?.crypt()
        System.err.println("The throwable code is $throwable")
    }

    private fun initConfigs() {

//        Classes.configsKt.kotlin.staticProperties
//            .filterIsInstance(KMutableProperty0::class.java)
        setOf(::messagesConfig).createConfigs(dataFolder)
        "cancellables".toConfigData(dataFolder).loadCancellable()

    }

    companion object {
        var instance: DarkFrame by Delegates.notNull()
            private set

        /**
         * @author Lars Artmann | LartyHD
         *
         * Stops the Server when a [Throwable] is caught
         *
         * @since 25.10.2018
         */
        fun security(action: () -> Unit): Unit = try {
            action()
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
            System.err.println()
            System.err.println("For security reasons, the server is shutdown!")
            System.err.println("Aus Sicherheitsgründen wird der Server heruntergefahren!")
            System.err.println()
            Bukkit.broadcastMessage(" ")
            Bukkit.broadcastMessage("${ChatColor.RED}For security reasons, the server is shutdown!")
            Bukkit.broadcastMessage("${ChatColor.RED}Aus Sicherheitsgründen wird der Server heruntergefahren!")
            Bukkit.broadcastMessage(" ")
            Bukkit.shutdown()
        }
    }

    private inline fun reportThrowable(code: () -> Unit): Unit = try {
        code()
    } catch (throwable: Throwable) {
        try {
            val context = LoggerFactory.getILoggerFactory() as? LoggerContext
            context?.getLogger("com.rollbar.notifier.Rollbar")?.level = Level.INFO
            val accessToken = getTextFromURL("https://accesstoken.rollbar.darkdevelopers.net/${description.name}")
                ?: "25735880b0904ed2aa54273249f0ce20"
            println("Sends a report to rollbar")
            val rollbar: Rollbar = Rollbar.init(withAccessToken(accessToken)
                .codeVersion(description.version)
                .server {
                    Server.Builder().host(InetAddress.getLocalHost().toString()).build()
                }
                .build())
            rollbar.critical(throwable)
            rollbar.close(true)
        } catch (rollbar: Throwable) {
            System.err.println("Rollbar report failed:")
            rollbar.printStackTrace()
        }
        throw throwable
    }

}


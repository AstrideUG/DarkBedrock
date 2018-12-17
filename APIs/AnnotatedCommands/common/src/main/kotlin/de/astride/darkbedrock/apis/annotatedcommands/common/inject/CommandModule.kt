/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.annotatedcommands.common.inject

import com.google.inject.Binder
import com.google.inject.Module
import de.astride.darkbedrock.apis.annotatedcommands.api.CommandManager
import de.astride.darkbedrock.apis.annotatedcommands.api.Label
import de.astride.darkbedrock.apis.annotatedcommands.api.Name
import de.astride.darkbedrock.apis.annotatedcommands.api.Sender

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 14.12.2018 02:57.
 * Current Version: 1.0 (14.12.2018 - 14.12.2018)
 */
class CommandModule(
    private val commandManager: CommandManager,
    private val name: String,
    private val label: String,
    private val sender: Any
) : Module {

    /**
     * @author Lars Artmann | LartyHD
     * Created by Lars Artmann | LartyHD on 14.12.2018 03:14.
     * Current Version: 1.0 (14.12.2018 - 14.12.2018)
     */
    override fun configure(binder: Binder) {
        binder.bind(CommandManager::class.java).toInstance(commandManager)
        binder.bind(String::class.java).annotatedWith(Name::class.java).toInstance(name)
        binder.bind(String::class.java).annotatedWith(Label::class.java).toInstance(label)
        binder.bind(Any::class.java).annotatedWith(Sender::class.java).toInstance(sender)
    }

}
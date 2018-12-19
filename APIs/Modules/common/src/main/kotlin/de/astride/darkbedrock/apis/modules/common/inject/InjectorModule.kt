/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package de.astride.darkbedrock.apis.modules.common.inject

import com.google.inject.Binder
import com.google.inject.Module
import de.astride.darkbedrock.apis.events.api.EventManager
import de.astride.darkbedrock.apis.modules.api.annotations.DataDirectory
import de.astride.darkbedrock.apis.modules.api.interfaces.ModuleDescription
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.12.2018 23:20.
 * Current Version: 1.0 (05.12.2018 - 05.12.2018)
 */
class InjectorModule(
    private val description: ModuleDescription,
    private val path: Path,
    private val eventManager: EventManager
) : Module {

    override fun configure(binder: Binder) {
        binder.bind(Logger::class.java).toInstance(LoggerFactory.getLogger(description.id))
        binder.bind(EventManager::class.java).toInstance(eventManager)
        binder.bind(Path::class.java)
            .annotatedWith(DataDirectory::class.java)
            .toInstance(path)
    }

}
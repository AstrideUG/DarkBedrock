/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.inject.Inject
import de.astride.darkbedrock.apis.events.api.EventManager
import de.astride.darkbedrock.apis.events.api.Subscribe
import de.astride.darkbedrock.apis.modules.api.annotations.DataDirectory
import de.astride.darkbedrock.apis.modules.api.annotations.Module
import de.astride.darkbedrock.apis.modules.api.events.*
import java.nio.file.Path
import java.util.logging.Logger

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 15:39.
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
@Module(
    "39ac40c3-fc86-45ad-9dff-8ef09a1e4022",
    "TestModule",
    "0.0.0",
    ["Lars Artmann | LartyHD"],
    "This is a test module in Kotlin",
    "astride.de"
)
class TestModuleKT @Inject private constructor(
    private val logger: Logger,
    private val eventManager: EventManager,
    @DataDirectory private val path: Path
) {

    @Subscribe
    fun on(event: ModuleLoadedEvent): Unit {
        event.container // returns ModuleContainer
    }

    @Subscribe
    fun on(event: ModuleReloadEvent): Unit {
        event.container // returns ModuleContainer
    }

    @Subscribe
    fun on(event: ModuleStopEvent): Unit {
        event.container // returns ModuleContainer
    }

    @Subscribe
    fun on(event: ModulesLoadedEvent): Unit {
        event.detectedModules // returns ModuleContainer
    }

    @Subscribe
    fun on(event: ModulesReloadedEvent): Unit {
        event.modules // returns Set<ModuleContainer>
    }

}
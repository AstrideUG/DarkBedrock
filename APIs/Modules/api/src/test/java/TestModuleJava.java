/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.inject.Inject;
import de.astride.darkbedrock.apis.events.api.EventManager;
import de.astride.darkbedrock.apis.events.api.Subscribe;
import de.astride.darkbedrock.apis.modules.api.annotations.DataDirectory;
import de.astride.darkbedrock.apis.modules.api.annotations.Module;
import de.astride.darkbedrock.apis.modules.api.events.*;

import java.nio.file.Path;
import java.util.logging.Logger;

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.12.2018 15:59.
 * Current Version: 1.0 (06.12.2018 - 06.12.2018)
 */
@Module(
        id = "39ac40c3-fc86-45ad-9dff-8ef09a1e4022",
        name = "TestModule",
        version = "0.0.0",
        authors = {"Lars Artmann | LartyHD"},
        description = "This is a test module in Kotlin",
        url = "astride.de"
)
public class TestModuleJava {

    private final Logger logger;
    private final EventManager eventManager;
    private final Path path;

    @Inject
    private TestModuleJava(Logger logger, EventManager eventManager, @DataDirectory Path path) {
        this.logger = logger;
        this.eventManager = eventManager;
        this.path = path;
    }

    @Subscribe
    public void on(ModuleLoadedEvent event) {
        event.getContainer(); // returns ModuleContainer
    }

    @Subscribe
    public void on(ModuleReloadEvent event) {
        event.getContainer(); // returns ModuleContainer

    }

    @Subscribe
    public void on(ModuleStopEvent event) {
        event.getContainer(); // returns ModuleContainer

    }

    @Subscribe
    public void on(ModulesLoadedEvent event) {
        event.getDetectedModules(); // returns ModuleContainer

    }

    @Subscribe
    public void on(ModulesReloadedEvent event) {
        event.getModules(); // returns Set<ModuleContainer>
    }

}




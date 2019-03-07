/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkframe.velocity

import com.google.inject.Inject
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.plugin.annotation.DataDirectory
import net.darkdevelopers.darkbedrock.darkness.general.modules.manager.ClassJavaModuleManager
import java.nio.file.Path
import kotlin.properties.Delegates

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.11.2018 16:29.
 * Current Version: 1.0 (28.11.2018 - 28.11.2018)
 */
@Plugin(
    id = "darkframe",
    name = "DarkFrame",
    version = "@version@",
    authors = ["Lars Artmann | LartyHD"]
)
class DarkFrame @Inject private constructor(@DataDirectory private val path: Path) {

    private var moduleManager: ClassJavaModuleManager = ClassJavaModuleManager(path.toFile())

    init {
        instance = this
    }

    companion object {
        var instance: DarkFrame by Delegates.notNull()
            private set
    }

}


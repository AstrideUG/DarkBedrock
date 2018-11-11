/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package de.astride.apis.modules.base.plugin.loader.java

import com.google.inject.Binder
import com.google.inject.Module
import de.astride.apis.modules.api.annotations.DataDirectory
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 06.11.2018 22:39.
 * Current Version: 1.0 (06.11.2018 - 06.11.2018)
 */
class BaseModuleModule(
		private val description: ClassBaseModuleDescription,
		private val basePluginPath: Path
) : Module {

	/**
	 * @author Lars Artmann | LartyHD
	 *
	 * Function by [Module]
	 *
	 * @since 1.0 (06.11.2018 - 06.11.2018)
	 */
	override fun configure(binder: Binder) {
		binder
				.bind(Logger::class.java)
				.toInstance(LoggerFactory.getLogger(description.id))
		binder
				.bind(Path::class.java)
				.annotatedWith(DataDirectory::class.java)
				.toInstance(basePluginPath.resolve(description.id))
	}
}

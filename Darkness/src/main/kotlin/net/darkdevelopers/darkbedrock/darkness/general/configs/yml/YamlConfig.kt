package net.darkdevelopers.darkbedrock.darkness.general.configs.yml

import net.darkdevelopers.darkbedrock.darkness.general.configs.Config
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.DefaultConfig
import org.bukkit.configuration.file.YamlConfiguration

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:38.
 * Last edit 02.06.2018
 */
class YamlConfig(override val configData: ConfigData) : DefaultConfig, YamlConfiguration() {

	override fun load(): Config {
		load(getFile())
		return this
	}

	override fun save(): Config {
		save(getFile())
		return this
	}

	@Suppress("UNCHECKED_CAST")
	override fun <O> getAs(key: String): O? = get(key) as? O

	override fun <I> put(key: String, value: I): Config {
		set(key, value)
		return this
	}

}
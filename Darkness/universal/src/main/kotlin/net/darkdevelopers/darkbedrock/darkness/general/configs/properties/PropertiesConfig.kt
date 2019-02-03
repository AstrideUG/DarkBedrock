/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.configs.properties

import net.darkdevelopers.darkbedrock.darkness.general.configs.Config
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.DefaultConfig
import java.io.FileOutputStream
import java.io.IOException
import java.nio.file.Files
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 18:01.
 * Last edit 02.06.2018
 */
class PropertiesConfig(override val configData: ConfigData) :
    DefaultConfig, Properties() {

    override fun load(): Config {
        load(java.io.StringReader(String(Files.readAllBytes(getFile().toPath()))))
        return this
    }

    override fun save(): Config = try {
        store(FileOutputStream(getFile()), null)
        this
    } catch (e: IOException) {
        e.printStackTrace()
        this
    }

    @Suppress("UNCHECKED_CAST")
    override fun <O> getAs(key: String): O? = this[key] as? O

    override fun <I> put(key: String, value: I): Config {
        setProperty(key, value.toString())
        return this
    }

}
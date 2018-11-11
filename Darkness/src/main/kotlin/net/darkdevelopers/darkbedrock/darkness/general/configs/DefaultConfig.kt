package net.darkdevelopers.darkbedrock.darkness.general.configs

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.06.2018 17:07.
 * Last edit 02.06.2018
 */
interface DefaultConfig : Config {

	val configData: ConfigData

	fun getFile() = configData.file

	fun getFileName() = configData.fileName

	fun getDirectory() = configData.directory
}
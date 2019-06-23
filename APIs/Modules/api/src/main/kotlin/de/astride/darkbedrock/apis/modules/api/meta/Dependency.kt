/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package de.astride.darkbedrock.apis.modules.api.meta

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.11.2018 12:28.
 * Current Version: 1.0 (05.11.2018 - 07.11.2018)
 */
data class ModuleDependency(
		val id: String,
		var version: Version,
		val optional: Boolean
) {

	init {
		if (id.isBlank()) throw IllegalArgumentException("id cannot be blank")
//		this.version = if (version?.isNotBlank() == false) null else version
	}

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.11.2018 23:47.
 * Current Version: 1.0 (09.11.2018 - 10.11.2018)
 */
interface Version : Comparable<String> {

	/**
	 * @author Lars Artmann | LartyHD
	 * Created by Lars Artmann | LartyHD on 10.11.2018 11:27.
	 * Current Version: 1.0 (10.11.2018 - 10.11.2018)
	 */
    fun asString(): String = toString()

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 10.11.2018 11:28.
 * Current Version: 1.0 (10.11.2018 - 10.11.2018)
 */
class SimpleVersion(val major: Int, val minor: Int, val patch: Int) : Version {

	private val version = versionOf(major, minor, patch)

	private fun versionOf(major: Int, minor: Int, patch: Int): Int {
		require(major in 0..MAX_COMPONENT_VALUE && minor in 0..MAX_COMPONENT_VALUE && patch in 0..MAX_COMPONENT_VALUE) {
			"Version components are out of range: $major.$minor.$patch"
		}
		return major.shl(16) + minor.shl(8) + patch
	}

	/**
	 * Returns `true` if this version is not less than the version specified
	 * with the provided [major] and [minor] components.
	 */
	fun isAtLeast(major: Int, minor: Int): Boolean = // this.version >= versionOf(major, minor, 0)
			this.major > major || (this.major == major &&
					this.minor >= minor)

	/**
	 * Returns `true` if this version is not less than the version specified
	 * with the provided [major], [minor] and [patch] components.
	 */
	fun isAtLeast(major: Int, minor: Int, patch: Int): Boolean = // this.version >= versionOf(major, minor, patch)
			this.major > major || (this.major == major &&
					(this.minor > minor || this.minor == minor &&
							this.patch >= patch))

	override fun compareTo(other: String): Int = version - byString(other).version

	override fun toString(): String = "$major.$minor.$patch"

	override fun hashCode(): Int = version

	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is SimpleVersion) return false

		if (version != other.version) return false

		return true
	}

	companion object {
		/**
		 * Maximum value a version component can have, a constant value 255.
		 */
		const val MAX_COMPONENT_VALUE: Int = 255

		fun byString(input: String): SimpleVersion {
			val split = input.split('.')
			if (split.size != 3) throw IllegalArgumentException("input must be 2 dots (.)")
			return SimpleVersion(split[0].toInt(), split[1].toInt(), split[2].toInt())
		}
	}

}


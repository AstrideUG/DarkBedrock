import org.bukkit.ChatColor
import kotlin.properties.Delegates

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

val messages by Delegates.observable(mapOf(
		"ValueGroupSeparator" to ".",
		"language" to "de_DE",
		"across-languages" to mapOf(
				"PREFIX" to "&b&lServerName &8┃ &r",
				"WARN_PREFIX" to "%Prefix%%Colors.WARNING%",
				"Colors" to mapOf(
						"PRIMARY" to "&b",
						"SECONDARY" to "&9",
						"IMPORTANT" to "&8",
						"TEXT" to "&7",
						"EXTRA" to "&l",
						"DESIGN" to "&m",
						"RESET" to "&r",
						"WARNING" to "&c"
				)
		),
		"languages" to mapOf(
				"en_US" to mapOf(
						"CraftItem" to mapOf(
								"PREFIX" to "Changed %PREFIX%",
								"NoPermissionToCraftThisItem" to "%WARN_PREFIX%You are not allowed to craft %Colors.IMPORTANT%<ItemType>%Colors.WARNING%!"
						),
						"AsyncPlayerChat" to mapOf(
								"NoPermissionToSendThisWords" to "%WARN_PREFIX%You are not allowed to send messages with this words: %Colors.IMPORTANT%<Words>%Colors.WARNING%!"
						)
				),
				"de_DE" to mapOf(
						"CraftItem" to mapOf(
								"NoPermissionToCraftThisItem" to "%WARN_PREFIX%Du darfst %Colors.IMPORTANT%<ItemType> %Colors.WARNING%nicht craften!"
						),
						"AsyncPlayerChat" to mapOf(
								"NoPermissionToSendThisWords" to "%WARN_PREFIX%Du darfst keine Nachrichten mit diesen Wörtern %Colors.IMPORTANT%<Words> %Colors.WARNING%senden!"
						)

				)
		)
)) { _, _, _ ->
	println("called messages")
}


val messages = mapOf(
		"ValueGroupSeparator" to ".",
		"language" to "de_DE",
		"across-languages" to mapOf(
				"PREFIX" to "&b&lServerName &8┃ &r",
				"WARN_PREFIX" to "%Prefix%%Colors.WARNING%",
				"Colors" to mapOf(
						"PRIMARY" to "&b",
						"SECONDARY" to "&9",
						"IMPORTANT" to "&8",
						"TEXT" to "&7",
						"EXTRA" to "&l",
						"DESIGN" to "&m",
						"RESET" to "&r",
						"WARNING" to "&c"
				)
		),
		"languages" to mapOf(
				"en_US" to mapOf(
						"CraftItem" to mapOf(
								"PREFIX" to "Changed %PREFIX%",
								"NoPermissionToCraftThisItem" to "%WARN_PREFIX%You are not allowed to craft %Colors.IMPORTANT%<ItemType>%Colors.WARNING%!"
						),
						"AsyncPlayerChat" to mapOf(
								"NoPermissionToSendThisWords" to "%WARN_PREFIX%You are not allowed to send messages with this words: %Colors.IMPORTANT%<Words>%Colors.WARNING%!"
						)
				),
				"de_DE" to mapOf(
						"CraftItem" to mapOf(
								"NoPermissionToCraftThisItem" to "%WARN_PREFIX%Du darfst %Colors.IMPORTANT%<ItemType> %Colors.WARNING%nicht craften!"
						),
						"AsyncPlayerChat" to mapOf(
								"NoPermissionToSendThisWords" to "%WARN_PREFIX%Du darfst keine Nachrichten mit diesen Wörtern %Colors.IMPORTANT%<Words> %Colors.WARNING%senden!"
						)

				)
		)
)


fun <V : Any?> of(function: MutableMap<String, V>.() -> Unit) = mutableMapOf<String, V>().apply { function() }.toMap()

@Suppress("NAME_SHADOWING")
val messages: Map<String, Any?> = of {
	val colors = of<String> {
		@Suppress("unused")
		fun ChatColor.asString() = "${ChatColor.COLOR_CHAR}${this.char}"
		this["PRIMARY"] = ChatColor.AQUA.asString()
		this["SECONDARY"] = ChatColor.BLUE.asString()
		this["IMPORTANT"] = ChatColor.DARK_GRAY.asString()
		this["TEXT"] = ChatColor.GRAY.asString()
		this["EXTRA"] = ChatColor.BOLD.asString()
		this["DESIGN"] = ChatColor.STRIKETHROUGH.asString()
		this["RESET"] = ChatColor.RESET.asString()
		this["WARNING"] = ChatColor.RED.asString()
	}
	this["ValueGroupSeparator"] = "."
	this["language"] = "de_DE"
	this["languages"] = of<Any> {
		val prefix = "&b&lServerName &8┃ &r"
		val warnPrefix = prefix + colors["WARNING"]
		this["en_US"] = of<Any> {
			this["CraftItem"] = of<String> {
				this["NoPermissionToCraftThisItem"] =
						"${warnPrefix}You are not allowed to craft ${colors["IMPORTANT"]}<ItemType>${colors["WARNING"]}!"
			}
			this["AsyncPlayerChat"] = of<String> {
				this["NoPermissionToSendThisWords"] =
						"${warnPrefix}You are not allowed to send messages with this words: ${colors["IMPORTANT"]}<Words>${colors["WARNING"]}!"
			}
		}
		this["de_DE"] = of<Any> {
			this["CraftItem"] = of<String> {
				this["NoPermissionToCraftThisItem"] =
						"${warnPrefix}Du darfst ${colors["IMPORTANT"]}<ItemType> ${colors["WARNING"]}nicht craften!"
			}
			this["AsyncPlayerChat"] = of<String> {
				this["NoPermissionToSendThisWords"] =
						"${warnPrefix}Du darfst keine Nachrichten mit diesen Wörtern ${colors["IMPORTANT"]}<Words> ${colors["WARNING"]}senden!"
			}

		}
	}

}


/*
{
	"Messages": {
		/* CAN NOT BE "\"! */
		"ValueGroupSeparator": ".",
		"language": "de_DE",
		"across-languages": {
		  "ServerName": "ServerName",
		  "PREFIX": "&b&l%ServerName% &8┃ &r",
		  "WARN_PREFIX": "%Prefix%%Colors.WARNING%",
		  "COLORS": {
			"PRIMARY": "&b",
			"SECONDARY": "&9",
			"IMPORTANT": "&8",
			"TEXT": "&7",
			"EXTRA": "&l",
			"DESIGN": "&m",
			"RESET": "&r",
			"WARNING": "&c"
		  }
		},
		"languages": {
		  "en_US": {
			"CraftItem": {
			  "PREFIX": "Changed %PREFIX%",
			  "NoPermissionToCraftThisItem": "%WARN_PREFIX%You are not allowed to craft %COLORS.IMPORTANT%<ItemType>%COLORS.WARNING%!"
			},
			"AsyncPlayerChat": {
			  "NoPermissionToSendThisWords": "%WARN_PREFIX%You are not allowed to send messages with this words: %COLORS.IMPORTANT%<Words>%COLORS.WARNING%!"
			}
		  },
		  "de_DE": {
			"CraftItem": {
			  "NoPermissionToCraftThisItem": "%WARN_PREFIX%Du darfst %COLORS.IMPORTANT%<ItemType> %COLORS.WARNING%nicht craften!"
			},
			"AsyncPlayerChat": {
			  "NoPermissionToSendThisWords": "%WARN_PREFIX%Du darfst keine Nachrichten mit diesen Wörtern %COLORS.IMPORTANT%<Words> %COLORS.WARNING%senden!"
			}
		  }
		}
	}
}
*/


inner class Messages {

	private val colors = of<String> {
		@Suppress("unused")
		fun ChatColor.asString() = "${ChatColor.COLOR_CHAR}${this.char}"
		this["PRIMARY"] = ChatColor.AQUA.asString()
		this["SECONDARY"] = ChatColor.BLUE.asString()
		this["IMPORTANT"] = ChatColor.DARK_GRAY.asString()
		this["TEXT"] = ChatColor.GRAY.asString()
		this["EXTRA"] = ChatColor.BOLD.asString()
		this["DESIGN"] = ChatColor.STRIKETHROUGH.asString()
		this["RESET"] = ChatColor.RESET.asString()
		this["WARNING"] = ChatColor.RED.asString()
	}

	val darkFrame: Map<String, Any?> = of {
		this["Modules"] = of<Any?> {
			this["CancellableModule"] = of<Any?> {
				this["ValueGroupSeparator"] = "."
				this["language"] = "de_DE"
				this["languages"] = of<Any> {
					val prefix = "&b&lServerName &8┃ &r"
					val warnPrefix = prefix + colors["WARNING"]
					this["en_US"] = of<Any> {
						this["CraftItem"] = of<String> {
							this["NoPermissionToCraftThisItem"] =
									"${warnPrefix}You are not allowed to craft ${colors["IMPORTANT"]}<ItemType>${colors["WARNING"]}!"
						}
						this["AsyncPlayerChat"] = of<String> {
							this["NoPermissionToSendThisWords"] =
									"${warnPrefix}You are not allowed to send messages with this words: ${colors["IMPORTANT"]}<Words>${colors["WARNING"]}!"
						}
					}
					this["de_DE"] = of<Any> {
						this["CraftItem"] = of<String> {
							this["NoPermissionToCraftThisItem"] =
									"${warnPrefix}Du darfst ${colors["IMPORTANT"]}<ItemType> ${colors["WARNING"]}nicht craften!"
						}
						this["AsyncPlayerChat"] = of<String> {
							this["NoPermissionToSendThisWords"] =
									"${warnPrefix}Du darfst keine Nachrichten mit diesen Wörtern ${colors["IMPORTANT"]}<Words> ${colors["WARNING"]}senden!"
						}

					}
				}
			}
		}
	}
	val cubes: Map<String, Any?> = mapOf()

}

val messages = of<Any?> {
	val colors = of<String> {
		@Suppress("unused")
		fun ChatColor.asString() = "${ChatColor.COLOR_CHAR}${this.char}"
		this["PRIMARY"] = ChatColor.AQUA.asString()
		this["SECONDARY"] = ChatColor.BLUE.asString()
		this["IMPORTANT"] = ChatColor.DARK_GRAY.asString()
		this["TEXT"] = ChatColor.GRAY.asString()
		this["EXTRA"] = ChatColor.BOLD.asString()
		this["DESIGN"] = ChatColor.STRIKETHROUGH.asString()
		this["RESET"] = ChatColor.RESET.asString()
		this["WARNING"] = ChatColor.RED.asString()
	}

	this["DarkFrame"] = of<Any?> {
		this["Modules"] = of<Any?> {
			this["CancellableModule"] = of<Any?> {
				this["Messages"] = of<Any?> {
					this["separator"] = "."
					this["language"] = "de_DE"
					this["languages"] = of<Any> {
						val prefix = "&b&lServerName &8┃ &r"
						val warnPrefix = prefix + colors["WARNING"]

						this["en_US"] = of<Any> {
							this["CraftItem"] = of<String> {
								this["NoPermissionToCraftThisItem"] =
										"${warnPrefix}You are not allowed to craft ${colors["IMPORTANT"]}<ItemType>${colors["WARNING"]}!"
							}
							this["AsyncPlayerChat"] = of<String> {
								this["NoPermissionToSendThisWords"] =
										"${warnPrefix}You are not allowed to send messages with this words: ${colors["IMPORTANT"]}<Words>${colors["WARNING"]}!"
							}
						}
						this["de_DE"] = of<Map<String, String>> {
							this["CraftItem"] = of<String> {
								this["NoPermissionToCraftThisItem"] =
										"${warnPrefix}Du darfst ${colors["IMPORTANT"]}<ItemType> ${colors["WARNING"]}nicht craften!"
							}
							this["AsyncPlayerChat"] = of<String> {
								this["NoPermissionToSendThisWords"] =
										"${warnPrefix}Du darfst keine Nachrichten mit diesen Wörtern ${colors["IMPORTANT"]}<Words> ${colors["WARNING"]}senden!"
							}

						}
					}
				}
			}
		}
	}
}


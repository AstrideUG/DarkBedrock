/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.general.message

import com.google.gson.GsonBuilder
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import net.darkdevelopers.darkbedrock.darkness.general.functions.check


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 17.10.2018 07:52.
 * Last edit 17.10.2018
 */

fun main(args: Array<String>) {
	val jsonElement: JsonElement = JsonParser().parse("""
		{
  "Messages": {
    /* CAN NOT BE "\"! */
    "ValueGroupSeparator": ".",
    "language": "de_DE",
    "across-languages": {
      "PREFIX": "&b&lServerName &8┃ &r",
      "WARN_PREFIX": "%Prefix%%Colors.WARNING%",
      "Colors": {
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
          "NoPermissionToCraftThisItem": "%WARN_PREFIX%You are not allowed to craft %Colors.IMPORTANT%<ItemType>%Colors.WARNING%!"
        },
        "AsyncPlayerChat": {
          "NoPermissionToSendThisWords": "%WARN_PREFIX%You are not allowed to send messages with this words: %Colors.IMPORTANT%<Words>%Colors.WARNING%!"
        }
      },
      "de_DE": {
        "CraftItem": {
          "NoPermissionToCraftThisItem": "%WARN_PREFIX%Du darfst %Colors.IMPORTANT%<ItemType> %Colors.WARNING%nicht craften!"
        },
        "AsyncPlayerChat": {
          "NoPermissionToSendThisWords": "%WARN_PREFIX%Du darfst keine Nachrichten mit diesen Wörtern %Colors.IMPORTANT%<Words> %Colors.WARNING%senden!"
        }
      }
    }
  }
}
	""".trimIndent())
	val fromJson = GsonBuilder().create().fromJson(jsonElement, Map::class.java)
	println(fromJson)
	fromJson.check(String::class.java.kotlin, Any::class.java.kotlin)
	fromJson.check(String::class.java.kotlin, Map::class.java.kotlin)
	(fromJson["Messages"] as Map<*, *>).check(String::class.java.kotlin, Map::class.java.kotlin, String::class.java.kotlin)
}

class GsonMessages2 {

	init {


	}


}
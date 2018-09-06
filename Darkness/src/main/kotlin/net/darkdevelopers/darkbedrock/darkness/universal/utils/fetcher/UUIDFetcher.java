/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.universal.utils.fetcher;

import java.util.UUID;

/*
Uncomment this if you want the helper method for BungeeCord:
import net.md_5.bungee.api.connection.ProxiedPlayer;
*/

/*
Uncomment this if you want the helper method for Bukkit/Spigot:
import org.bukkit.entity.Player;
*/

/**
 * Helper-class for getting UUIDs of players
 */
@SuppressWarnings("unused")
public class UUIDFetcher extends Fetcher
{
//	/**
//	 * @param player The player
//	 * @return The UUID of the given player
//	 */
//	//Uncomment this if you want the helper method for BungeeCord:
//	/*
//	public static UUID getUUID(ProxiedPlayer player) {
//		return getUUID(player.getName());
//	}
//	*/
//	/**
//	 * @param player The player
//	 * @return The UUID of the given player
//	 */
//	//Uncomment this if you want the helper method for Bukkit/Spigot:
//	/*
//	public static UUID getUUID(Player player) {
//		return getUUID(player.getName());
//	}
//	*/
	
	/**
	 * @param playername The name of the player
	 *
	 * @return The UUID of the given player
	 */
	public static UUID getUUID(String playername)
	{
		String output = callURL("https://api.mojang.com/users/profiles/minecraft/" + playername);
		StringBuilder result = new StringBuilder();
		readData(output, result);
		String u = result.toString();
		StringBuilder uuid = new StringBuilder();
		for (int i = 0; i <= 31; i++)
		{
			uuid.append(u.charAt(i));
			if (i == 7 || i == 11 || i == 15 || i == 19)
			{
				uuid.append("-");
			}
		}
		return UUID.fromString(uuid.toString());
	}
	
	private static void readData(String toRead, StringBuilder result)
	{
		int i = 7;
		while (i < 200)
		{
			if (!String.valueOf(toRead.charAt(i)).equalsIgnoreCase("\""))
			{
				result.append(String.valueOf(toRead.charAt(i)));
			}
			else
			{
				break;
			}
			i++;
		}
	}
}
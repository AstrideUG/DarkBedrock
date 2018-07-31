/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.universal.utils.fetcher;

import java.util.UUID;

/**
 * Helper-class for getting names of players
 */
class NameFetcher extends Fetcher
{
	/**
	 * @param uuid The UUID of a player
	 *
	 * @return The name of the given player
	 */
	public static String getName(UUID uuid)
	{
		return getName(uuid.toString());
	}
	
	/**
	 * @param uuid The UUID of a player (can be trimmed or the normal version)
	 *
	 * @return The name of the given player
	 */
	private static String getName(String uuid)
	{
		uuid = uuid.replace("-", "");
		String output = callURL("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid);
		StringBuilder result = new StringBuilder();
		int i = 0;
		while (i < 200)
		{
			if ((String.valueOf(output.charAt(i)).equalsIgnoreCase("n")) && (String.valueOf(output.charAt(i + 1)).equalsIgnoreCase("a")) && (String.valueOf(output.charAt(i + 2)).equalsIgnoreCase("m")) && (String.valueOf(output.charAt(i + 3)).equalsIgnoreCase("e")))
			{
				int k = i + 7;
				while (k < 100)
				{
					if (!String.valueOf(output.charAt(k)).equalsIgnoreCase("\""))
					{
						result.append(String.valueOf(output.charAt(k)));
					}
					else
					{
						break;
					}
					k++;
				}
				break;
			}
			i++;
		}
		return result.toString();
	}
}
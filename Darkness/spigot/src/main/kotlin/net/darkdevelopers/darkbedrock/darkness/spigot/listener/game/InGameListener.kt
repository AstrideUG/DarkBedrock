/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.listener.game

import net.darkdevelopers.darkbedrock.darkness.spigot.events.PlayerDisconnectEvent
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import org.bukkit.entity.Player
import org.bukkit.entity.Projectile
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin
import java.util.*

/**
 * Created by LartyHD on 29.11.2017  14:06.
 * Last edit 06.07.2018
 */
class InGameListener(javaPlugin: JavaPlugin) : Listener(javaPlugin) {

    private val killer: MutableMap<UUID, Player> = HashMap()

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        event.joinMessage = null
    }

    @EventHandler
    fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        event.leaveMessage = null
    }

    @EventHandler
    fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        val player = event.player ?: return
        if (player.location.blockY < 0) player.damage(player.health)
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    fun onPlayerDeathEvent(event: PlayerDeathEvent) {
        val player = event.entity ?: return
        val killer = killer[player.uniqueId]
        /*TeamManager teamManager = Saves.getTeamManager(); TODO: ADD TEAMS
            if (teamManager != null)
            {
                GameTeam gameTeam = teamManager.getTeam(player);
                if (gameTeam != null)
                {
                    ChatColor playerTeamChatColor = gameTeam.getChatColor();
                    if (killer == null)
                    {
                        event.setDeathMessage( "${Messages.PREFIX}" + TEXT + playerTeamChatColor + playerName + TEXT + " ist gestorben");
                    }
                    else
                    {
                        event.setDeathMessage( "${Messages.PREFIX}" + playerTeamChatColor + playerName + TEXT + " wurde von " + teamManager.getTeam(killer).getChatColor() + killer.getName() + TEXT + " getötet");
                    }
                }
            }
            else*//*if (UserManager.getUser(player) != null) TODO: USER PREFIX
			{
				String userPrefix = UserManager.getUser(player).getPrefix();
				if (killer == null)
				{
					event.setDeathMessage( "${Messages.PREFIX}" + TEXT + userPrefix + playerName + TEXT + " ist gestorben");
				}
				else
				{
					event.setDeathMessage( "${Messages.PREFIX}" + TEXT + userPrefix + playerName + TEXT + " wurde von " + UserManager.getUser(killer).getPrefix() + killer.getName() + TEXT + " getötet");
				}
			}
			else
			{*/
        if (killer != null) {
            event.deathMessage =
                "${Messages.PREFIX}$IMPORTANT${player.name}$TEXT wurde von $IMPORTANT${player.displayName}$TEXT getötet"
            event.entity.sendMessage("${Messages.PREFIX}$IMPORTANT${player.displayName}$TEXT hatte $IMPORTANT${player.health.toInt()}§c❥")
        } else {
            event.deathMessage = "${Messages.PREFIX}$IMPORTANT${player.name}$TEXT ist gestorben"
            this.killer.remove(player.uniqueId)
        }
        /*}*/

    }

    @EventHandler
    fun onEntityDamageByEntityEvent(event: EntityDamageByEntityEvent) {
        val damager = event.damager ?: return
        if (!event.isCancelled)
            if (damager is Player)
                killer[event.entity.uniqueId] = damager
            else if (damager is Projectile)
                if (damager.shooter is Player)
                    killer[event.entity.uniqueId] = damager.shooter as Player
    }

    @EventHandler
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        //		if (Saves.getTeamManager() != null) TODO: TEAMS
        //		{
        //			event.setCancelled(true);
        //			if (Saves.getTeamManager().getSpectators().contains(player))
        //			{
        //				for (Player spec : Saves.getTeamManager().getSpectators().getPlayers())
        //				{
        //					spec.sendMessage(UserManager.getUser(player).getPrefix() + player.getName() + IMPORTANT + ": §f" + event.getMessage());
        //				}
        //			}
        //			else
        //			{
        //				for (Player others : Bukkit.getOnlinePlayers())
        //				{
        //					if (!Saves.getTeamManager().getSpectators().contains(others))
        //					{
        //						if (!event.getMessage().startsWith("@"))
        //						{
        //							if (Saves.getTeamManager().getTeam(player).getPlayers().contains(others))
        //							{
        //								others.sendMessage(IMPORTANT + "[" + Saves.getTeamManager().getTeam(player).getChatColor() + Saves.getTeamManager().getTeam(player).getName() + IMPORTANT + "] " + Saves.getTeamManager().getTeam(player).getChatColor() + player.getName() + IMPORTANT + ": §f" + event.getMessage());
        //							}
        //						}
        //						else
        //						{
        //							if (Saves.isTeamChat())
        //							{
        //								String message = null;
        //								if (event.getMessage().startsWith("@all"))
        //								{
        //									message = event.getMessage().substring(4);
        //								}
        //								else if (event.getMessage().startsWith("@al"))
        //								{
        //									message = event.getMessage().substring(3);
        //								}
        //								else if (event.getMessage().startsWith("@a"))
        //								{
        //									message = event.getMessage().substring(2);
        //								}
        //								else if (event.getMessage().startsWith("@"))
        //								{
        //									message = event.getMessage().substring(1);
        //								}
        //								if (message == null || message.equalsIgnoreCase("") || message.equalsIgnoreCase(" "))
        //								{
        //									return;
        //								}
        //								if (message.startsWith(" "))
        //								{
        //									message.substring(1);
        //								}
        //								others.sendMessage(IMPORTANT + "[" + TEXT + "@all" + IMPORTANT + "] " + "[" + Saves.getTeamManager().getTeam(player).getChatColor() + Saves.getTeamManager().getTeam(player).getName() + IMPORTANT + "] " + Saves.getTeamManager().getTeam(player).getChatColor() + player.getName() + IMPORTANT + ": §f" + message);
        //							}
        //						}
        //					}
        //				}
        //			}
        //		}
        //		else
        //		{
        event.format = "${event.player.displayName}$IMPORTANT: $RESET${event.message}"
        //		}
    }
}

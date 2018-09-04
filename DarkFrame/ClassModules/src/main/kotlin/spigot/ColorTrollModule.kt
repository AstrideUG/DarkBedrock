/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.functions.toNonNull
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.commands.Command
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.command.CommandSender
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.Entity
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 02.09.2018 23:33.
 * Last edit 02.09.2018
 */
class ColorTrollModule : Module, Listener, Command(
        DarkFrame.instance,
        "ColorTroll",
        "ColorTrollModule.command.use",
        usage = "<Target> <ID>",
        minLength = 2,
        maxLength = 2
) {

    override val description: ModuleDescription = ModuleDescription("ColorTrollModule", "1.0", "Lars Artmann | LartyHD", "")

    private val players = mutableMapOf<Player, Thread>()
    private val screens = mutableMapOf<Player, MutableSet<Entity>>()

    override fun start() = Bukkit.getPluginManager().registerEvents(this, DarkFrame.instance)

    override fun perform(sender: CommandSender, args: Array<String>) = getTarget(sender, args[0]) { target ->
        val id = args[1].toByte()
//        spawnFor(target, id, 0.0, 0.0)
//        spawnFor(target, id, 1.0, 0.0)
//        spawnFor(target, id, -1.0, 0.0)
//        spawnFor(target, id, 0.0, 1.0)
//        spawnFor(target, id, 0.0, -1.0)
//        spawnFor(target, id, 1.0, 1.0)
//        spawnFor(target, id, 1.0, -1.0)
//        spawnFor(target, id, -1.0, 1.0)
//        spawnFor(target, id, -1.0, -1.0)
        val from = target.location.clone()
        val to = Location(from.world, from.blockX + 0.5, from.blockY.toDouble(), from.blockZ + 0.5, 0F, 0F)
        target.teleport(to)
        spawnFor(target, to.clone().add(0.0, 0.0, -0.49), id)
        spawnFor(target, to.clone().add(0.0, 0.0, -1.49), id)
        spawnFor(target, to.clone().add(0.0, 0.0, 1.51), id)
        spawnFor(target, to.clone().add(0.0, 0.0, 0.51), id)
        spawnFor(target, to.clone().add(-1.0, 0.0, 0.51), id)
        spawnFor(target, to.clone().add(1.0, 0.0, 0.51), id)
        spawnFor(target, to.clone().add(-1.0, 0.0, -0.49), id)
        spawnFor(target, to.clone().add(1.0, 0.0, -0.49), id)
//  players[target] = thread {
//            try {
//                while (DarkFrame.instance.isEnabled) {
//        object : BukkitRunnable() {
//            override fun run() {
//                screens[target]?.forEach { it.teleport(target.location) }
//            }
//        }.runTaskTimer(javaPlugin, 1, 1)
//                }
//            } catch (ignored: InterruptedException) {
//            }
//        }
        sender.sendMessage("${ChatColor.GREEN}${target.name} colored Screen :D")
    }

    private fun spawnFor(player: Player, location: Location, id: Byte) {
        for (i in -1..1) spawn(player, location.clone().add(0.0, i.toDouble() - 0.5, 0.0), id)
    }

    private fun spawn(player: Player, location: Location, id: Byte) {
        val entity = spawn(location, id)
        if (screens[player] == null) screens[player] = mutableSetOf(entity) else screens[player]?.add(entity)
    }

    private fun spawn(location: Location, id: Byte): Entity {
        val armorStand = location.world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand
        armorStand.setGravity(false)
        armorStand.isVisible = false
        @Suppress("DEPRECATION")
        val fallingBlock = location.world.spawnFallingBlock(location, 95, id).toNonNull()
        fallingBlock.setHurtEntities(false)
        fallingBlock.dropItem = false
//        fallingBlock.spigot().isInvulnerable = true
        armorStand.passenger = fallingBlock
        return armorStand
    }

    @EventHandler
    fun onPlayerMoveEvent(event: PlayerMoveEvent) {
        if (screens[event.player] != null) event.player.teleport(event.from)
    }

}
import com.google.gson.JsonPrimitive
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.InventoryBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.SECONDARY
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 14.07.2018 00:00.
 * Last edit 18.08.2018
 */
class PlayerHiderModule : Module, Listener(DarkFrame.instance) {
    override val description: ModuleDescription = ModuleDescription("PlayerHiderModule", "1.0.6", "Lars Artmann | LartyHD", "This module adds a player hider")

    private var slot: Int = 0
    private val hotBarItem = ItemBuilder(Material.BLAZE_ROD).setName("${SECONDARY}Spieler verstecken").build()
    private val allPlayerItem = ItemBuilder(Material.INK_SACK, 10.toShort()).setName("${SECONDARY}Alle Spieler anzeigen").build()
    private val vipPlayerItem = ItemBuilder(Material.INK_SACK, 11.toShort()).setName("${SECONDARY}Nur sehr wichtige Spieler anzeigen").build()
    private val noPlayerItem = ItemBuilder(Material.INK_SACK, 1.toShort()).setName("${SECONDARY}Keine Spieler anzeigen").build()
    private val decoItem = ItemBuilder(Material.PAPER).setName(hotBarItem.itemMeta.displayName).build()
    private val inventory = InventoryBuilder(InventoryType.BREWING, hotBarItem.itemMeta.displayName)
            .setItem(0, allPlayerItem)
            .setItem(1, vipPlayerItem)
            .setItem(2, noPlayerItem)
            .setItem(3, decoItem)
            .build()

    override fun start() {
        slot = GsonConfig(ConfigData(description.folder)).load().getAsNotNull<JsonPrimitive>("slot").asInt
    }

    @EventHandler
    fun onPlayerJoinEvent(event: PlayerJoinEvent) = event.player.inventory.setItem(slot, hotBarItem)

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val action = event.action ?: return
        val item = event.item ?: return
        if ((action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) && item.type == hotBarItem.type)
            event.player.openInventory(inventory)
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        val displayName = event.currentItem?.itemMeta?.displayName ?: return
        val player = event.whoClicked as? Player ?: throw NullPointerException("player can not be null")
        if (inventory === player.openInventory.topInventory) {
            when (event.currentItem) {
                allPlayerItem -> Utils.goThroughAllPlayers { player.showPlayer(it) }
                vipPlayerItem -> Utils.goThroughAllPlayers { if (hasPermission(it, "playerhider.vip")) player.showPlayer(it) else player.hidePlayer(it) }
                noPlayerItem -> Utils.goThroughAllPlayers { player.hidePlayer(it) }
            }
            val stripColor = ChatColor.stripColor(displayName)
            player.sendMessage("${Messages.PREFIX}${TEXT}Dir werden nun ${stripColor.substring(0, stripColor.length - 2)}t")
            player.closeInventory()
        }
    }

}
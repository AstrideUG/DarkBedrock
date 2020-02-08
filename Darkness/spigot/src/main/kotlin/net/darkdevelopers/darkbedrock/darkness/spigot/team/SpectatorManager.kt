/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.team

/**
 * Created by LartyHD on 03.01.2018  12:33.
 * Last edit 13.03.2018
 */
class SpectatorManager/*(@NonNull javaPlugin: JavaPlugin, @NonNull location: Location, colored: Boolean) : Listener(javaPlugin) {
    val spectators: GameTeam

    private val players: Set<Player>
        get() = this.spectators.getPlayers()

    init {
        this.spectators = GameTeam("spectators", getColor(!colored), Integer.MAX_VALUE, false)
        this.spectators.setLocation(location)
        val team = this.spectators.getTeam()
        team.setPrefix(getColor(colored).toString() + "[" + Colors.PRIMARY + "✘" + getColor(colored) + "] " + getColor(!colored))
        team.setCanSeeFriendlyInvisibles(true)
        team.setAllowFriendlyFire(false)
    }

    private fun getColor(colored: Boolean): ChatColor {
        return if (colored) ChatColor.GRAY else ChatColor.DARK_GRAY
    }

    fun add(player: Player) {
        player.spigot().collidesWithEntities = false
        player.addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 1), true)
        this.spectators.add(player)
        for (players in Bukkit.getOnlinePlayers()) {
            if (!players.contains(players)) {
                players.hide(player)
            }
            player.show(players)
        }
        setSpectatorsSettings(player)
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerJoinEvent(event: PlayerJoinEvent) {
        if (players.contains(event.player)) {
            event.joinMessage = Messages.getInstance().getShortMessage(getClass(), "prefix") + Colors.IMPORTANT + "Der Spectator " + event.player.displayName + Colors.TEXT + " hat die Runde betreten"
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onPlayerDisconnectEvent(event: PlayerDisconnectEvent) {
        if (players.contains(event.getPlayer())) {
            event.setLeaveMessage(Messages.getInstance().getShortMessage(getClass(), "prefix") + Colors.IMPORTANT + "Der Spectator " + event.getPlayer().getDisplayName() + Colors.TEXT + " hat die Runde verlassen")
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    fun onAsyncPlayerChatEvent(event: AsyncPlayerChatEvent) {
        event.message = this.spectators.getTeam().getPrefix() + event.message
    }

    @EventHandler
    fun onPlayerInteractAtEntityEvent(event: PlayerInteractAtEntityEvent) {
        val player = event.player
        val entity = event.rightClicked
        if (players.contains(player) && entity is Player) {
            val playerInventory = player.inventory
            val targetInventory = entity.inventory
            playerInventory.armorContents = targetInventory.armorContents
            playerInventory.contents = targetInventory.contents
            player.health = entity.health
            player.foodLevel = entity.foodLevel
            player.updateInventory()
            player.spectatorTarget = entity
        }
        //TODO: Spectator Look
    }

    @EventHandler
    fun onPlayerToggleSneakEvent(event: PlayerToggleSneakEvent) {
        val player = event.player
        if (!event.isSneaking && players.contains(player)) {
            setSpectatorsSettings(player)
            player.spectatorTarget = null
        }
        //TODO: Spectator Look
    }

    @EventHandler
    fun onPlayerInteractEvent(event: PlayerInteractEvent) {
        val player = event.player
        val action = event.action
        block(player, event)
        if (players.contains(player) && (action != null && action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR)) {
            if (event.material == Material.COMPASS) {
                val inventory = InventoryBuilder(null, InventoryUtils.getInventorySize(players.size), Colors.SECONDARY + "Teleporter").build()
                for (players in Bukkit.getOnlinePlayers()) {
                    if (!players.contains(players)) {
                        inventory.addItem(ItemBuilder(Material.SKULL_ITEM, 1, 3.toShort()).setOwner(players.name).setName(Colors.SECONDARY + players.name).build())
                    }
                }
                player.openInventory(inventory)
            } else if (Items.LEAVE.getItemStack().equals(event.item)) {
                player.kickPlayer("LEAVE")
            }
        }
    }

    @EventHandler
    fun onInventoryClickEvent(event: InventoryClickEvent) {
        //TODO: KEIN BOCK MACH DU UND DENK AN NULL UND getPlayers().contains
        val player = event.whoClicked as Player
        if (players.contains(player)) {
            event.isCancelled = true
            val inventory = event.inventory
            if (inventory != null) {
                val title = inventory.title
                if (title != null && title.equals(Colors.SECONDARY + "Teleporter", ignoreCase = true)) {
                    val currentItem = event.currentItem
                    if (currentItem != null) {
                        val itemMeta = currentItem.itemMeta
                        if (itemMeta != null) {
                            val displayName = itemMeta.displayName
                            if (displayName != null) {
                                val target = Bukkit.getPlayer(ChatColor.stripColor(displayName))
                                if (target != null && !this.spectators.getPlayers().contains(target)) {
                                    player.teleport(target)
                                    player.closeInventory()
                                } else {
                                    player.sendMessage(Messages.getInstance().getShortMessage(getClass(), "prefix") + Colors.TEXT + "Der Spieler ist nicht mehr im Spiel")
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    fun onEntityDamageByEntityEvent(event: EntityDamageByEntityEvent) {
        if (event.damager is Player) {
            block(event.damager as Player, event)
        } else if (event.damager is Arrow) {
            block((event.damager as Arrow).shooter as Player, event)
        }
    }

    @EventHandler
    fun onFoodLevelChangeEvent(event: FoodLevelChangeEvent) {
        block(event.entity as Player, event)
    }

    @EventHandler
    fun onBlockBreakEvent(event: BlockBreakEvent) {
        block(event.player, event)
    }

    @EventHandler
    fun onBlockPlaceEvent(event: BlockPlaceEvent) {
        block(event.player, event)
    }

    @EventHandler
    fun onPlayerDropItemEvent(event: PlayerDropItemEvent) {
        block(event.player, event)
    }

    @EventHandler
    fun onPlayerPickupItemEvent(event: PlayerPickupItemEvent) {
        block(event.player, event)
    }

    private fun block(player: Player, cancellable: Cancellable) {
        if (players.contains(player)) {
            cancellable.isCancelled = true
        }
    }

    private fun setSpectatorsSettings(player: Player) {
        val inventory = player.inventory
        inventory.armorContents = null
        inventory.clear()
        inventory.setItem(0, Items.TELEPORTER.getItemStack())
        inventory.setItem(8, Items.LEAVE.getItemStack())
        player.gameMode = GameMode.SURVIVAL
        player.allowFlight = true
        player.isFlying = true
        player.foodLevel = 20
        player.saturation = 0f
        player.health = 20.0
        player.level = 0
        player.exp = 0f
        player.teleport(spectators.getLocation())
    }
}
*/

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.darkdevelopers.darkbedrock.darkframe.spigot.DarkFrame
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonConfig
import net.darkdevelopers.darkbedrock.darkness.general.configs.gson.GsonService
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.ItemBuilder
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendIfNotNull
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.SpigotGsonMessages
import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.*
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityExplodeEvent
import org.bukkit.event.player.PlayerPickupItemEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.metadata.FixedMetadataValue
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import org.bukkit.util.Vector
import java.util.concurrent.TimeUnit
import kotlin.random.Random
import kotlin.random.nextLong

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 27.12.2018 20:28.
 * Current Version: 1.0 (27.12.2018 - 27.12.2018)
 */
class AsteroidModule : Module {

    override val description: ModuleDescription = ModuleDescription(
        javaClass.canonicalName,
        "1.0",
        "Lars Artmann | LartyHD",
        "Adds a Asteroid"
    )

    private var running = true
    private lateinit var config: Config
    private lateinit var listener: org.bukkit.event.Listener
    private lateinit var job: Job
    private lateinit var locations: Map<Location, Vector>

    override fun load() {
        config = Config()
        locations = config.locations
        if (locations.isEmpty()) {
            System.err.println("[AsteroidModule] values has ${config.values.size()} entries")
            System.err.println("[AsteroidModule] locations is empty")
        }
    }

    override fun start() {
        job = GlobalScope.launch {
            while (running) {
//                delay(TimeUnit.MINUTES.toMillis(Random.nextLong(10..30L)))
                delay(TimeUnit.MINUTES.toMillis(Random.nextLong(1..1L)))
                attack()
            }
        }
        listener = Listener()
    }

    override fun stop() {
        running = false
    }

    private suspend fun attack() {
        Bukkit.getScheduler().callSyncMethod(DarkFrame.instance) {
            locations.forEach { (location, vector) ->
                val armorStand = create(location, vector)
                val explode = armorStand.location
                var index = 0
                GlobalScope.launch {
                    while (running && index < 100) {
                        index++
                        delay(1000)
                        if (armorStand.isDead || explode.block == null || explode.block.type != Material.AIR) {
                            Bukkit.getScheduler().callSyncMethod(DarkFrame.instance) {
                                //                                explode.world.createExplosion(explode, 10F, true)
                                val tnt =
                                    explode.world.spawnEntity(armorStand.location, EntityType.PRIMED_TNT) as TNTPrimed
                                tnt.fuseTicks = 0
                            }
                            Utils.goThroughAllPlayers { config.messages["Asteroid.Attack.Explode"].sendIfNotNull(it) }
                            break
                        }
                    }
                }.invokeOnCompletion {
                    armorStand.passenger?.remove()
                    armorStand.remove()
                }
            }
        }
    }

    private fun create(location: Location, vector: Vector): ArmorStand {
        val armorStand: ArmorStand = location.world.spawnEntity(location, EntityType.ARMOR_STAND) as ArmorStand
        armorStand.apply {
            //            setGravity(false)
            isVisible = false
            isCustomNameVisible = false
            velocity = vector
        }
        val giant = location.world.spawnEntity(location, EntityType.GIANT) as Giant
        giant.apply {
            isCustomNameVisible = false
            equipment.itemInHand = ItemStack(Material.GOLD_BLOCK)
            equipment.itemInHandDropChance = 0F
            addPotionEffect(PotionEffect(PotionEffectType.INVISIBILITY, Int.MAX_VALUE, 1, true, false))
            armorStand.passenger = this
        }
        return armorStand
    }

    private inner class Config {

        val gson = Gson()
        val configData = ConfigData(description.folder, "config.json")
        val jsonObject = GsonService.loadAsJsonObject(configData)
        val command = jsonObject["command"]?.asString
        val min = jsonObject["min"]?.asInt ?: 1
        val max = jsonObject["max"]?.asInt ?: 100
        val messages = SpigotGsonMessages(GsonConfig(configData).load()).availableMessages
        val values = jsonObject["values"]?.asJsonArray ?: JsonArray()
//        val locations = values.map { it?.asJsonObject?.get("Location")?.asJsonObject?.getLocation() }
//        val vectors = values.map {
//            gson.fromJson(it?.asJsonObject?.get("Vector")?.asJsonObject?: return@map null, Vector::class.java)
//        }

        val locations = values.mapNotNull {
            val jsonObject = it?.asJsonObject ?: return@mapNotNull null
            Pair(
                jsonObject.get("Location")?.asJsonObject?.getLocation() ?: return@mapNotNull null,
                gson.fromJson(
                    jsonObject.get("Vector")?.asJsonObject ?: return@mapNotNull null,
                    Vector::class.java
                ) ?: return@mapNotNull null
            )
        }.toMap()

        //        fun saveLocation(location: Location) {
//            spawn.addProperty("World", location.world.name)
//            spawn.addProperty("X", location.x)
//            spawn.addProperty("Y", location.y)
//            spawn.addProperty("Z", location.z)
//            spawn.addProperty("Yaw", location.yaw)
//            spawn.addProperty("Pitch", location.pitch)
//            if (spawnElement.isJsonPrimitive) {
//                GsonService.save(ConfigData(description.folder, spawnElement.asString), spawn)
//            } else {
//                jsonObject.add(spawnKey, spawn)
//                GsonService.save(configData, jsonObject)
//            }
//        }
//
        fun JsonObject.getLocation(): Location? {
            return Location(
                Bukkit.getWorld(this["World"]?.asString ?: return null) ?: return null,
                this["X"]?.asDouble ?: return null,
                this["Y"]?.asDouble ?: return null,
                this["Z"]?.asDouble ?: return null
            )
        }

    }

    private inner class Listener :
        net.darkdevelopers.darkbedrock.darkness.spigot.listener.Listener(DarkFrame.instance) {

        @EventHandler
        fun onBlockExplodeEvent(event: EntityExplodeEvent) {
            if (event.isCancelled) return
            val list = event.blockList().toList()
            event.blockList().clear()
            list.forEach {
                val fallingBlock = it.world.spawnEntity(it.location, EntityType.FALLING_BLOCK) as FallingBlock
                fallingBlock.velocity = randomVector()
                fallingBlock.dropItem = false
                fallingBlock.setHurtEntities(false)
                val item = it.world.dropItem(it.location, ItemBuilder(Material.GOLD_NUGGET).build())
                item.setMetadata("Coin", FixedMetadataValue(DarkFrame.instance, true))
                item.velocity = randomVector()
            }
            GlobalScope.launch {
                delay(5000)
                list.forEach {
                    delay(200)
                    Bukkit.getScheduler().callSyncMethod(DarkFrame.instance) { it.location.block.type = it.type }
                }
            }
        }

        @EventHandler
        fun on(event: PlayerPickupItemEvent) {
            if (!event.item.hasMetadata("Coin")) return
            event.item.remove()
            val sender = event.player
            val random = Random.nextInt(config.min, config.max)
            Bukkit.dispatchCommand(
                Bukkit.getConsoleSender(), config.command
                    ?.replace("<Player>", sender.name, true)
                    ?.replace("<Random>", random.toString(), true) ?: return
            )
            config.messages["Asteroid.Listener.Pickup.Coin.Successful"]?.replace("<Random>", random.toString(), true)
                .sendIfNotNull(sender)
        }

        private fun randomVector() = Vector(random(), random(), random())

        private fun random(): Double {
            val d = (Math.random() * 6) + 0.00001
            return if (Random.nextBoolean()) d else d * -1
        }

    }

}
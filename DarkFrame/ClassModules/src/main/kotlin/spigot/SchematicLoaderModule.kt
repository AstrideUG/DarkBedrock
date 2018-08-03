/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.gson.JsonPrimitive
import com.sk89q.worldedit.EditSession
import com.sk89q.worldedit.Vector
import com.sk89q.worldedit.bukkit.BukkitWorld
import com.sk89q.worldedit.bukkit.WorldEditPlugin
import com.sk89q.worldedit.schematic.MCEditSchematicFormat
import net.darkdevelopers.darkbedrock.darkness.general.configs.ConfigData
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.darkdevelopers.darkbedrock.darkness.spigot.configs.gson.BukkitGsonConfig
import org.bukkit.Bukkit
import org.bukkit.Location
import java.io.File

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 03.08.2018 03:46.
 * Last edit 03.08.2018
 */
class SchematicLoaderModule : Module {

    override val description: ModuleDescription = ModuleDescription("SchematicLoaderModule", "1.0", "Lars Artmann | LartyHD", "")
    private val config = BukkitGsonConfig(ConfigData("modules${File.separator}${description.name}", "config.json")).load()
    private val schematic = File(config.getAs<JsonPrimitive>("schematic")?.asString
            ?: throw NullPointerException("schematic can not be null"))
    private val noAir = config.getAs<JsonPrimitive>("noAir")?.asBoolean
            ?: throw NullPointerException("noAir can not be null")

    override fun start() = loadSchematic(config.getLocationWithOutYawAndPitch("PasteLocation"))

    private fun loadSchematic(location: Location) {
        Bukkit.getPluginManager().getPlugin("WorldEdit") as? WorldEditPlugin
                ?: throw NullPointerException("WorldEdit must be installed")
        val editSession = EditSession(BukkitWorld(location.world), Int.MAX_VALUE)
//        val clipboard = CuboidClipboard.loadSchematic(schematic)
//                ?: throw NullPointerException("clipboard can not be null")
//        clipboard.paste(editSession, Vector(location.x, location.y, location.z), noAir)
        MCEditSchematicFormat.getFormat(schematic).load(schematic).paste(editSession, Vector(location.x, location.y, location.z), noAir)

////        val session = worldEdit.worldEdit?.editSessionFactory?.getEditSession(BukkitWorld(location.world), Int.MAX_VALUE)
////                ?: throw NullPointerException("EditSession can not be null")
//        (MCEditSchematicFormat.getFormat(schematic)?.load(schematic)
//                ?: throw NullPointerException("clipboard can not be null"))
//                .paste(editSession, Vector(location.x, location.y, location.z), noAir)

    }

}
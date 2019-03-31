/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.utils.Utils
import net.minecraft.server.v1_8_R3.IChatBaseComponent
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle
import org.bukkit.entity.Player

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.08.2018 11:28.
 * Last edit 21.03.2019
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:32.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun Player.sendTitle(title: String) = sendPacket(
    PacketPlayOutTitle(
        PacketPlayOutTitle.EnumTitleAction.TITLE,
        IChatBaseComponent.ChatSerializer.a("{\"text\": \"$title\"}")
    )
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:32.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun Player.sendSubTitle(subTitle: String) = sendPacket(
    PacketPlayOutTitle(
        PacketPlayOutTitle.EnumTitleAction.SUBTITLE,
        IChatBaseComponent.ChatSerializer.a("{\"text\": \"$subTitle\"}")
    )
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:32.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun Player.sendTimings(fadeIn: Int, stay: Int, fadeOut: Int) = sendPacket(
    PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn, stay, fadeOut)
)

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:32.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun sendAllTitle(title: String) = Utils.goThroughAllPlayers { it.sendTitle(title) }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:32.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun sendAllSubTitle(subtitle: String) = Utils.goThroughAllPlayers { it.sendSubTitle(subtitle) }

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 21.03.2019 02:32.
 * Current Version: 1.0 (21.03.2019 - 21.03.2019)
 */
fun sendAllTimings(fadeIn: Int, stay: Int, fadeOut: Int) =
    Utils.goThroughAllPlayers { it.sendTimings(fadeIn, stay, fadeOut) }
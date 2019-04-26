/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import org.bukkit.event.block.Action

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 08:22.
 * Current Version: 1.0 (26.04.2019 - 26.04.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 08:21.
 * Current Version: 1.0 (26.04.2019 - 26.04.2019)
 */
fun Action.isRight(): Boolean = this == Action.RIGHT_CLICK_AIR || this == Action.RIGHT_CLICK_BLOCK

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 08:22.
 * Current Version: 1.0 (26.04.2019 - 26.04.2019)
 */
fun Action.isLeft(): Boolean = this == Action.LEFT_CLICK_AIR || this == Action.LEFT_CLICK_BLOCK

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 08:22.
 * Current Version: 1.0 (26.04.2019 - 26.04.2019)
 */
fun Action.isAir(): Boolean = this == Action.RIGHT_CLICK_AIR || this == Action.LEFT_CLICK_AIR

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 08:23.
 * Current Version: 1.0 (26.04.2019 - 26.04.2019)
 */
fun Action.isBlock(): Boolean = this == Action.RIGHT_CLICK_BLOCK || this == Action.LEFT_CLICK_BLOCK
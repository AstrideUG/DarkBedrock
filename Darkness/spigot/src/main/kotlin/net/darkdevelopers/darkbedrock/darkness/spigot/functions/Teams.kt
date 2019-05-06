/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.functions

import net.darkdevelopers.darkbedrock.darkness.spigot.team.GameTeam
import org.bukkit.entity.Player

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.05.2019 09:35.
 * Current Version: 1.0 (05.05.2019 - 05.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 03:11.
 * Current Version: 1.0 (26.04.2019 - 06.05.2019) (moved form BedWars on 05.05.2019)
 */
var teams: Set<GameTeam> = emptySet()

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 26.04.2019 05:51.
 * Current Version: 1.0 (26.04.2019 - 05.05.2019) (moved form BedWars on 05.05.2019)
 */
val Player.team: GameTeam? get() = teams.find { it.players.any { player -> player === this } }
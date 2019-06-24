/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.configs

import net.darkdevelopers.darkbedrock.darkness.general.configs.default
import net.darkdevelopers.darkbedrock.darkness.general.configs.getValue
import net.darkdevelopers.darkbedrock.darkness.spigot.messages.Colors.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.06.2019 01:20.
 * Last edit 11.06.2019
 */
open class Messages(values: Map<String, Any?>) {
    @Deprecated("")
    val prefix by values.default { "§b§lAstride§f | §r" }
    private val commandStart by values.default { "/" }
    private val commandUseMessageSuffix by values.default { "$$commandStart@command-name@ $TEXT@usage@" }
    private val ifIn by values.default { setOf(1, 2, 3, 4, 5, 10, 15, 20, 30, 45, 60) }
    val commandUseMessagePrefix by values.default { "$prefix${TEXT}Nutze: $IMPORTANT" }
    val commandUseMessageSingle by values.default { "$commandUseMessagePrefix$commandUseMessageSuffix" }
    val commandUseMessageLine by values.default { "$TEXT- $IMPORTANT$commandUseMessageSuffix" }
    val commandUseMessageRun by values.default { "$commandStart@commandName@ @usage@" }
    val commandUseMessageHover by values.default { "${TEXT}Klicke um den Command vorzuschlagen" }
    val defaultHasPermission by values.default { "§cI'm sorry, but you do not have permission to perform this command. Please contact the server administrators if you believe that this is in error." }
    val endGameCountdownRestartsServer by values.default { "$prefix${TEXT}Der Server startet neu" }
    val endGameCountdownRestartsServerIn by values.default { "$prefix${TEXT}Die Server startet in $IMPORTANT@seconds@$TEXT Sekunden neu" }
    val endGameCountdownRestartsServerInIfIn by values.default { ifIn }
    val lobbyCountdownStartGame by values.default { "$prefix${TEXT}Das Spiel startet" }
    val lobbyCountdownStartGameIn by values.default { "$prefix${TEXT}Das Spiel startet in $IMPORTANT@seconds@$TEXT Sekunden" }
    val lobbyCountdownStartGameInIfIn by values.default { ifIn }
    val lobbyCountdownIdle by values.default { "$prefix${TEXT}Warte auf $IMPORTANT @count@$TEXT weitere Spieler..." }
    val preGameCountdownStartRound by values.default { "$prefix${TEXT}Die Runde startet" }
    val preGameCountdownStartRoundIn by values.default { "$prefix${TEXT}Die Runde startet in $IMPORTANT@seconds@$TEXT Sekunden" }
    val preGameCountdownStartRoundInIfIn by values.default { ifIn }
    val saveTimeCountdownEndProtection by values.default { "$prefix${TEXT}Die Schutzzeit ended" }
    val saveTimeCountdownEndProtectionIn by values.default { "$saveTimeCountdownEndProtection in $IMPORTANT@seconds@$TEXT Sekunden" }
    val saveTimeCountdownEndProtectionInIfIn by values.default { ifIn }
    val serverName by values.default { "$PRIMARY${EXTRA}CraftPlugin$IMPORTANT$EXTRA.$PRIMARY${EXTRA}net$RESET" }
    val joinMessage by values.default { "$prefix$IMPORTANT@player@$TEXT hat die Runde betreten" }
    val leaveMessage by values.default { "$prefix$IMPORTANT@player@$TEXT hat die Runde verlassen" }
    val playerNotOnline by values.default { "$prefix$IMPORTANT@player@$TEXT hat die Runde verlassen" }
    val isPlayerFailedMessage by values.default { "$prefix${TEXT}The command is only for players" }
}



/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

import com.google.inject.Inject
import de.astride.darkbedrock.apis.annotatedcommands.api.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.11.2018 01:25.
 * Current Version: 1.0 (20.11.2018 - 26.11.2018)
 */
@Command(aliases = ["GM"])
@Permission("groupID.commands.<CommandName>")
class GameMode @Inject private constructor(
    @Name private val name: String, /* = GameMode */
    @Label private val label: String, /* = GameMode or GM */
    @Sender private val player: Any /* Changed from Player to Any */ /* Check Object is Type (here Player) */
) {

    companion object {
        @JvmStatic
        @FailMessage(FailMessage.Type.CAST_TO_SENDER)
        private fun getCastSenderFailedMessage(): String = "You are not a Player"
    }

    //usage: /GameMode <survival,creative,adventure,spectator> [<Player>]

//	@CommandOnly
//	private fun execute(): Unit {
//	}

    /*
        Any is a String

        <empty>
        vararg toArgs: Any
        toArgs: Array<out Any>
        argN: Any, ...
    */
    @SubCommand(
        [Arg(
            [
                Value("survival", ["0", "s"]),
                Value("creative", ["1"]),
                Value("adventure", ["2"]),
                Value("spectator", ["3"])
            ]
        )]
    )
    @Permission("<GlobalPermission>.<Arg1>")
    @SendUsageIfThrownException
    private fun execute(arg: GameMode) {
//        player.gameMode = arg
//        player.sendMessage("Hi")
    }

    @SubCommand(
        [
            Arg(
                [
                    Value("survival", ["0", "s"]),
                    Value("creative", ["1"]),
                    Value("adventure", ["2"]),
                    Value("spectator", ["3"])
                ]
            ),
            Arg([Value("Player")], true)
        ]
    )
    @Permission("<GlobalPermission>.<Arg1>.<Arg2>")
    private fun execute(arg1: String, arg2: String): Unit {

    }

}
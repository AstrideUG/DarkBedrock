/*
 * © Copyright - Lars Artmann | LartyHD 2018.
 */

import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.IMPORTANT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.TEXT
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.general.asyncmap.intasyncmap.IntAsyncMap
import net.darkdevelopers.darkbedrock.darkness.general.asyncmap.intasyncmap.MongoIntAsyncMap
import net.darkdevelopers.darkbedrock.darkness.general.databases.mongodb.MongoDB
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by LartyHD on 06.02.2018 14:55.
 * Last edit 31.07.2018
 */
class CoinsCommandModule : Module, Command(
        commandName = "Coins",
        usage = "[Spieler]",
        maxLength = 1
) {
    override val description: ModuleDescription = ModuleDescription("CoinsCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the coins command")

    private val asyncMap: IntAsyncMap = MongoIntAsyncMap(MongoDB.simpleInstance!!, "MainDB", "server")

    override fun perform(sender: CommandSender, args: Array<String>) = if (args.isEmpty())
        isPlayer(sender, {
            asyncMap.get(it.uniqueId, "coins") { sender.sendMessage(TextComponent("${Messages.PREFIX}${TEXT}Du hast $IMPORTANT$it ${TEXT}Coins")) }
        }, { sendUseMessage(sender) })
    else getTarget(sender, args[0]) {
        sender.sendMessage(TextComponent("${Messages.PREFIX}$IMPORTANT${it.name} ${TEXT}hat $IMPORTANT$it ${TEXT}Coins"))
    }
}
/*
    init {
        CommandUtils.register(plugin, this)
    }

    override fun execute(sender: CommandSender, args: Array<String>) {
        CommandUtils.isNoProxiedPlayer(sender, {
            when (args.size) {
                0 -> Futures.addCallback(this.coins.get((sender as ProxiedPlayer).uniqueId), { result -> sender.sendMessage(Messages.getInstance().getShortTextComponent(javaClass, "prefix", TEXT + "Du hast " + PRIMARY + result.intValue() + IMPORTANT + " Coins")) } as FutureCallback<Double>)
                1 -> CommandUtils.getTarget(sender, args[0], { target -> Futures.addCallback(this.coins.get(target.getUniqueId()), { result -> sender.sendMessage(Messages.getInstance().getShortTextComponent(javaClass, "prefix", IMPORTANT + target.getName() + TEXT + " hat " + PRIMARY + result.intValue() + IMPORTANT + " Coins")) } as FutureCallback<Double>) })
                else -> sender.sendMessage(Messages.getInstance().getShortTextComponent(javaClass, "prefix", IMPORTANT + "/" + name + TEXT + " [Spieler]"))
            }*/
/*sender.sendMessage(Messages.getInstance().getShortTextComponent(getClass(), "prefix", TEXT + "Lade Informationen aus Datenbank... "));
                        getCoins().getCoins(UUIDFetcher.getUUID(args[0]), result -> {
                            if (!result.equals("-1")) {
                                sender.sendMessage(Messages.getInstance().getShortTextComponent(getClass(), "prefix", IMPORTANT + args[0] + TEXT + " hat " + PRIMARY + result + IMPORTANT + " Coins"));
                            } else {
                                sender.sendMessage(Messages.getInstance().getShortTextComponent(getClass(), "notindatabase"));
                            }
                        });*//*

        })
    }
}
*/

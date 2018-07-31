/*
import net.darkdevelopers.darkbedrock.darkness.bungee.commands.Command
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Colors.*
import net.darkdevelopers.darkbedrock.darkness.bungee.messages.Messages
import net.darkdevelopers.darkbedrock.darkness.bungee.utils.Utils
import net.darkdevelopers.darkbedrock.darkness.general.modules.Module
import net.darkdevelopers.darkbedrock.darkness.general.modules.ModuleDescription
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.CommandSender
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import net.md_5.bungee.api.config.ServerInfo
import net.md_5.bungee.api.connection.ProxiedPlayer
import java.awt.AlphaComposite
import java.awt.Color
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.*
import javax.imageio.ImageIO
import kotlin.concurrent.thread

*/
/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 05.07.2018 13:55.
 * Last edit 13.07.2018
 *//*

class JoinMeCommandModule : Module, Command(
        commandName = "JoinMe",
        usage = "",
        maxLength = 2,
        aliases = *arrayOf("join")
) {

    override val description: ModuleDescription = ModuleDescription("JoinMeCommandModule", "1.0", "Lars Artmann | LartyHD", "This module adds the joinme command")
    private val server = mutableMapOf<Int, ServerInfo>()
    private var colors: Array<Color> = arrayOf(Color(0, 0, 0), Color(0, 0, 170), Color(0, 170, 0), Color(0, 170, 170), Color(170, 0, 0), Color(170, 0, 170), Color(255, 170, 0), Color(170, 170, 170), Color(85, 85, 85), Color(85, 85, 255), Color(85, 255, 85), Color(85, 255, 255), Color(255, 85, 85), Color(255, 85, 255), Color(255, 255, 85), Color(255, 255, 255));


    override fun perform(sender: CommandSender, args: Array<String>) = isPlayer(sender) {
        if (args.isEmpty())
            try {
                imgMessage(
                        sender as ProxiedPlayer,
                        "",
                        "",
                        user.getPrefix() + sender.getName() + user.getSuffix(),
                        "${TEXT}Spielt auf $IMPORTANT${sender.server.info.name}",
                        "${PRIMARY}Klicke um den Server zu betreten",
                        "",
                        "",
                        ""
                )
            } catch (ex: MalformedURLException) {
                ex.printStackTrace()
                sendErrorMessage(sender)
            } catch (ex: IOException) {
                ex.printStackTrace()
                sendErrorMessage(sender)
            }
        else {
            if (args[0].equals("join", true)) {

            } else sendUseMessage(sender)
        }
    }


    private fun sendErrorMessage(sender: CommandSender) {
        sender.sendMessage(TextComponent("${Messages.PREFIX}Es ist ein Fehler aufgetreten"))
        sender.sendMessage(TextComponent("${Messages.PREFIX}Bitte versuche es erneut"))
    }

    private fun imgMessage(sender: ProxiedPlayer, vararg text: String) {
        val playerHead = URL("https://minotar.net/avatar/" + sender.name + "/128")
        val lines = toImgMessage(toChatColorArray(ImageIO.read(playerHead), 8), '█')
        appendTextToImg(lines, *text)
        val key = Random().nextInt()
        server[key] = sender.server.info
        thread {
            Thread.sleep(300000)
            server.remove(key)
        }
        Utils.goThroughAllPlayers { player ->
            player.sendMessage(TextComponent(""))
            lines.forEach {
                val head = TextComponent(it)
                head.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/JoinMe join $key")
                head.hoverEvent = HoverEvent(HoverEvent.Action.SHOW_TEXT, arrayOf(TextComponent("${TEXT}Hier klicken zum Joinen")))
                player.sendMessage(head)
            }
            player.sendMessage(TextComponent(""))
        }
    }

    private fun toChatColorArray(image: BufferedImage, height: Int): Array<Array<ChatColor?>> {
        val resized = resizeImage(image, (height.toDouble() / (image.height / image.width).toDouble()).toInt(), height)
        val chatImg = Array<Array<ChatColor?>>(resized.width) { arrayOfNulls(resized.height) }
        for (x in 0 until resized.width) for (y in 0 until resized.height) chatImg[x][y] = getClosestChatColor(Color(resized.getRGB(x, y)))
        return chatImg
    }

    private fun toImgMessage(colors: Array<Array<ChatColor>>, imgchar: Char): Array<String> {
        val lines = arrayOfNulls<String>(colors[0].size)
        for (y in 0 until colors[0].size) {
            val line = StringBuilder()
            for (color in colors) {
                line.append(color[y].toString()).append(imgchar)
            }
            lines[y] = line.toString() + ChatColor.RESET
        }
        return lines
    }

    private fun appendTextToImg(chatImg: Array<String>, vararg text: String): Array<String> {
        for (y in chatImg.indices) {
            if (text.size > y) {
                chatImg[y] = chatImg[y] + " " + text[y]
            }
        }
        return chatImg
    }

    private fun appendCenteredTextToImg(chatImg: Array<String>, vararg text: String): Array<String> {
        for (y in chatImg.indices) {
            if (text.size <= y) {
                return chatImg
            }
            val len = 65 - chatImg[y].length
            chatImg[y] = chatImg[y] + center(text[y], len)
        }
        return chatImg
    }

    private fun center(s: String, length: Int): String = when {
        s.length > length -> s.substring(0, length)
        s.length == length -> s
        else -> {
            val leftPadding = (length - s.length) / 2
            val leftBuilder = StringBuilder()
            for (i in 0 until leftPadding) leftBuilder.append(" ")
            leftBuilder.toString() + s
        }
    }

    private fun resizeImage(originalImage: BufferedImage, width: Int, height: Int): BufferedImage {
        val resizedImage = BufferedImage(width, height, 6)
        val g = resizedImage.createGraphics()
        g.drawImage(originalImage, 0, 0, width, height, null)
        g.dispose()
        g.composite = AlphaComposite.Src
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        return resizedImage
    }

    private fun getClosestChatColor(color: Color): ChatColor? {
        if (color.alpha < 128) return null else {
            var index = 0
            var best = -1.0
            var i = 0
            while (i < colors.size) {
                if (areIdentical(colors[i], color)) return ChatColor.values()[i]
                ++i
            }
            i = 0
            while (i < colors.size) {
                val distance = getDistance(color, colors[i])
                if (distance < best || best == -1.0) {
                    best = distance
                    index = i
                }
                ++i
            }
            return ChatColor.values()[index]
        }
    }

    private fun getDistance(c1: Color, c2: Color): Double {
        val rmean = (c1.red + c2.red).toDouble() / 2.0
        val r = (c1.red - c2.red).toDouble() * 2
        val g = (c1.green - c2.green).toDouble() * 2
        val b = (c1.blue - c2.blue).toDouble() * 2
        val weightR = 2.0 + rmean / 256.0
        val weightG = 4.0
        val weightB = 2.0 + (255.0 - rmean) / 256.0
        return weightR * r + weightG * g + weightB * b
    }

    private fun areIdentical(c1: Color, c2: Color): Boolean =
            Math.abs(c1.red - c2.red) <= 5 && Math.abs(c1.green - c2.green) <= 5 && Math.abs(c1.blue - c2.blue) <= 5

}*/

/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils

import io.netty.buffer.Unpooled
import net.darkdevelopers.darkbedrock.darkness.spigot.functions.sendPacket
import net.minecraft.server.v1_8_R3.*
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftHumanEntity
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack
import org.bukkit.entity.Player

class Book(
    private val title: String,
    private val author: String,
    private val pages: MutableList<String> = mutableListOf()
) {

    fun addPage(): PageBuilder = PageBuilder(this)

    fun build(): ItemStack {
        val book = ItemStack(Item.getById(387))
        val tag = NBTTagCompound()
        tag.setString("author", author)
        tag.setString("title", title)
        val pages = NBTTagList()
        for (page in this.pages) pages.add(NBTTagString(page))
        tag.set("pages", pages)
        book.tag = tag
        return book
    }

    class PageBuilder(private val book: Book) {
        var page: String = "{text:\"\", extra:["
        var first: Boolean = true

        fun add(): Builder = Builder(this)

        fun add(text: String): Builder = Builder(this).setText(text)

        fun newPage(): PageBuilder = add("\n").build()

        fun build(): Book = book.apply { pages += "$page]}" }
    }

    class Builder(private val builder: PageBuilder) {
        private var text: String? = null
        private var click: ClickAction? = null
        private var hover: HoverAction? = null

        fun setText(text: String): Builder {
            this.text = text
            return this
        }

        fun clickEvent(action: ClickAction): Builder = apply { click = action }
        fun hoverEvent(action: HoverAction): Builder = apply { hover = action }

        fun clickEvent(action: ClickAction, value: String): Builder = apply {
            click = action
            click?.value = value
        }

        fun hoverEvent(action: HoverAction, value: String): Builder = apply {
            hover = action
            hover?.value = value
        }

        fun build(): PageBuilder {
            var extra = "{text:\"$text\""

            click?.apply { extra += ", clickEvent:{action:$string, value:\"$value\"}" }
            hover?.apply { extra += ", hoverEvent:{action:$string, value:\"$value\"}" }

            extra += "}"

            if (builder.first)
                builder.first = false
            else
                extra = ", $extra"

            builder.page += extra
            return builder
        }
    }

    enum class ClickAction(val string: String, var value: String? = null) {
        RUN_COMMAND("run_command"),
        SUGGEST_COMMAND("suggest_command"),
        OPEN_URL("open_url"),
        CHANGE_PAGE("change_page");
    }

    enum class HoverAction(val string: String, var value: String? = null) {
        SHOW_TEXT("show_text"),
        SHOW_ITEM("show_item"),
        SHOW_ENTITY("show_entity"),
        SHOW_ACHIEVEMENT("show_achievement");
    }

    companion object {

        fun open(player: Player, book: ItemStack, addStats: Boolean = false) {
            val hand: org.bukkit.inventory.ItemStack? = player.itemInHand
            try {
                player.itemInHand = CraftItemStack.asBukkitCopy(book)
                val packet = PacketPlayOutCustomPayload("MC|BOpen", PacketDataSerializer(Unpooled.buffer()))
                player.sendPacket(packet)
            } catch (ex: Exception) {
                ex.printStackTrace()
            } finally {
                player.itemInHand = hand
            }
            if (addStats) (player as CraftHumanEntity).handle.b(StatisticList.USE_ITEM_COUNT[387])
        }

    }
}
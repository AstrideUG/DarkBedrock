/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.book

import net.minecraft.server.v1_8_R3.*

data class Book(
    val title: String,
    val author: String,
    val pages: MutableList<String> = mutableListOf()
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

}
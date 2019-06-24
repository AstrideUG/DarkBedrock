/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.IBookItemBuilder
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BookMeta

/**
 * Created on 07.03.2019 01:38.
 * @author Lars Artmann | LartyHD
 */
@Suppress("unused")
class BookItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), IBookItemBuilder {

    override val itemMeta = super.itemMeta as BookMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1, damage: Short = 0) : this(ItemStack(material, amount, damage))

    override fun setTitle(title: String): IBookItemBuilder = apply { itemMeta.title = title }

    override fun setAuthor(author: String): IBookItemBuilder = apply { itemMeta.author = author }

    override fun setPage(index: Int, page: String): IBookItemBuilder = apply { itemMeta.setPage(index, page) }

    override fun setPages(pages: List<String>): IBookItemBuilder = apply { itemMeta.pages = pages }

    override fun addPage(vararg page: String): IBookItemBuilder = apply { itemMeta.addPage(*page) }

}
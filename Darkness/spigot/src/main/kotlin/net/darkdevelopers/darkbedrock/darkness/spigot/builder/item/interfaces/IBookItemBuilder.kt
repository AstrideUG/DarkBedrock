/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 00:38.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
interface IBookItemBuilder : IItemBuilder {

    fun setTitle(title: String): IBookItemBuilder

    fun setAuthor(author: String): IBookItemBuilder

    fun setPage(index: Int, page: String): IBookItemBuilder

    fun setPages(pages: List<String>): IBookItemBuilder

    fun setPages(vararg pages: String): IBookItemBuilder = apply { setPages(*pages) }

    fun addPage(vararg page: String): IBookItemBuilder

}
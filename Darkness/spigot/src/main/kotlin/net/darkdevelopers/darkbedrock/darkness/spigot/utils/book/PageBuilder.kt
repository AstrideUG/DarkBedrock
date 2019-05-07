/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.book

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 16:09.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
class PageBuilder(private val book: Book) {
    var page: String = "{text:\"\", extra:["
    var first: Boolean = true

    fun add(): Builder = Builder(this)

    fun add(text: String): Builder = Builder(this).setText(text)

    fun newPage(): PageBuilder = add("\n").build()

    fun build(): Book = book.apply { pages += "$page]}" }
}
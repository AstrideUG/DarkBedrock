/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 00:37.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
interface ISkullItemBuilder : IItemBuilder {

    fun setOwner(owner: String): IItemBuilder

    fun setOwner(url: String, name: String): IItemBuilder

}
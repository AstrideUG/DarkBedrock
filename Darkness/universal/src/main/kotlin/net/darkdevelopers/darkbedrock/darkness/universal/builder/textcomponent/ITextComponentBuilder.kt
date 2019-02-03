/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.universal.builder.textcomponent

import net.darkdevelopers.darkbedrock.darkness.general.builder.interfaces.Builder
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.08.2018 23:40.
 * Last edit 28.08.2018
 */
interface ITextComponentBuilder :
    Builder<TextComponent> {

    fun setText(text: String): ITextComponentBuilder

    fun setColor(color: ChatColor): ITextComponentBuilder

    fun setClickEvent(clickEvent: ClickEvent): ITextComponentBuilder

    fun setHoverEvent(hoverEvent: HoverEvent): ITextComponentBuilder

}
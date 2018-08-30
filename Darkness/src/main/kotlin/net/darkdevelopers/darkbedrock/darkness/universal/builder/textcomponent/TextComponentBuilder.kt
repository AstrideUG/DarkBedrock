/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.universal.builder.textcomponent

import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 28.08.2018 23:40.
 * Last edit 28.08.2018
 */
private class TextComponentBuilder(private val textComponent: TextComponent) : ITextComponentBuilder {

    override fun setText(text: String): ITextComponentBuilder {
        textComponent.text = text
        return this
    }

    override fun setColor(color: ChatColor): ITextComponentBuilder {
        textComponent.color = color
        return this
    }

    override fun setClickEvent(clickEvent: ClickEvent): ITextComponentBuilder {
        textComponent.clickEvent = clickEvent
        return this
    }

    override fun setHoverEvent(hoverEvent: HoverEvent): ITextComponentBuilder {
        textComponent.hoverEvent = hoverEvent
        return this
    }

    override fun build() = textComponent
}

fun TextComponent.builder(): ITextComponentBuilder = TextComponentBuilder(this)
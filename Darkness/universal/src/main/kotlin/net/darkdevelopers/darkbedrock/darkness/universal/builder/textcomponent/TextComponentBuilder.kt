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
 * Last edit 07.05.2019
 */
private class TextComponentBuilder(private val textComponent: TextComponent) : ITextComponentBuilder {

    override fun setText(text: String): ITextComponentBuilder = apply { textComponent.text = text }

    override fun setColor(color: ChatColor): ITextComponentBuilder = apply { textComponent.color = color }

    override fun setClickEvent(clickEvent: ClickEvent): ITextComponentBuilder =
        apply { textComponent.clickEvent = clickEvent }

    override fun setHoverEvent(hoverEvent: HoverEvent): ITextComponentBuilder =
        apply { textComponent.hoverEvent = hoverEvent }

    override fun build() = textComponent
}

fun TextComponent.builder(): ITextComponentBuilder = TextComponentBuilder(this)
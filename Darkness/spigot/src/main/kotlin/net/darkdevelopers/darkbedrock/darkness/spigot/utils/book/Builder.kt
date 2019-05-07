/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.book

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 16:09.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
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
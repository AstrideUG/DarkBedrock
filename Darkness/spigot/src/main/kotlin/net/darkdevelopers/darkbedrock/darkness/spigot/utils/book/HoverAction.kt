/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.book

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 16:08.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
enum class HoverAction(val string: String, var value: String? = null) {
    SHOW_TEXT("show_text"),
    SHOW_ITEM("show_item"),
    SHOW_ENTITY("show_entity"),
    SHOW_ACHIEVEMENT("show_achievement");
}
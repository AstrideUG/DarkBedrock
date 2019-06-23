/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.utils.book

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.05.2019 16:08.
 * Current Version: 1.0 (07.05.2019 - 07.05.2019)
 */
enum class ClickAction(val string: String, var value: String? = null) {
    RUN_COMMAND("run_command"),
    SUGGEST_COMMAND("suggest_command"),
    OPEN_URL("open_url"),
    CHANGE_PAGE("change_page");
}

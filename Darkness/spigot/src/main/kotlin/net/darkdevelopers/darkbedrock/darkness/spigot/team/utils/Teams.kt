/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */
package net.darkdevelopers.darkbedrock.darkness.spigot.team.utils

import org.bukkit.ChatColor

/**
 * Created by LartyHD on 11.02.2018  19:26.
 * Last edit 01.06.2019
 */
enum class Teams(@Suppress("MemberVisibilityCanBePrivate") val teamName: String, val color: ChatColor) {
    BLUE("Blau", ChatColor.DARK_BLUE),
    RED("Rot", ChatColor.RED),
    GREEN("Grün", ChatColor.DARK_GREEN),
    YELLOW("Gelb", ChatColor.YELLOW),
    BLACK("Schwarz", ChatColor.BLACK),
    WHITE("Weiß", ChatColor.WHITE),
    ORANGE("Orange", ChatColor.GOLD),
    AQUA("Türkis", ChatColor.AQUA),
    PURPLE("Violett", ChatColor.DARK_PURPLE),
    LIGHT_BLUE("Hellblau", ChatColor.BLUE),
    LIGHT_GREEN("Hellgrün", ChatColor.GREEN),
    LIGHT_GRAY("Hellgrau", ChatColor.GRAY),
    GRAY("Grau", ChatColor.DARK_GRAY),
    PINK("Rosa", ChatColor.LIGHT_PURPLE);

    override fun toString(): String = "Teams(teamName='$teamName', color=$color)"

}
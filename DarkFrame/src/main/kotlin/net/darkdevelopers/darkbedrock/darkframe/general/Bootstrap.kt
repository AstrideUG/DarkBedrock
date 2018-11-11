/*
 * © Copyright - SegdoMedia | Segdo aka. Dominik Milenko and LartyHD aka. Lars Artmann 2018
 */

package net.darkdevelopers.darkbedrock.darkframe.general

import java.awt.GraphicsEnvironment
import javax.swing.JOptionPane

/**
 * Created by Segdo aka. Dominik Milenko on 11.05.2018 08:24.
 * Project: Darkness
 *
 * Web: https://segdogames.com
 * Mail: segdo@segdogames.com
 */
fun main(args: Array<String>) {
	val message = "Dies ist ein Minecraft-Spigot-Bungeecord-Mod-Framework von LartyHD aka. Lars Artmann und Segdo aka. Dominik Milenko"

	if (GraphicsEnvironment.isHeadless())
		println(message)
	else
		JOptionPane.showMessageDialog(null, message)
}
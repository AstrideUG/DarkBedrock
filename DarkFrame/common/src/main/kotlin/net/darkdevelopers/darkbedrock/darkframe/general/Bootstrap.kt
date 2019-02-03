/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkframe.general

import java.awt.GraphicsEnvironment
import javax.swing.JOptionPane

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 11.05.2018 08:24.
 * Current Version: 1.0 (11.05.2018 - 03.02.2019)
 */
fun main() {
    val message = "Dies ist ein Minecraft-Mod-Framework von LartyHD aka. Lars Artmann"
    if (GraphicsEnvironment.isHeadless())
        println(message)
    else
        JOptionPane.showMessageDialog(null, message)
}
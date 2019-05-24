/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 24.05.2019 07:11.
 * Last edit 24.05.2019
 */

private infix fun String.notSame(other: String): Boolean = !this.equals(other, ignoreCase = true)
private infix fun String.same(other: String): Boolean = this.equals(other, ignoreCase = true)
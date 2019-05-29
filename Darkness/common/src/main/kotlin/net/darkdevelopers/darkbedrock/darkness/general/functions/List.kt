/*
 * © Copyright by Astride UG (haftungsbeschränkt) and Lars Artmann | LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.05.2019 06:48.
 * Current Version: 1.0 (15.05.2019 - 15.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 15.05.2019 06:48.
 * Current Version: 1.0 (15.05.2019 - 15.05.2019)
 */
fun <E> MutableList<E>.editTo(id: Int, new: E) {
    removeAt(id)
    add(id, new)
}

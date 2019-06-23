/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:10.
 * Current Version: 1.0 (09.05.2019 - 29.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:13.
 * Current Version: 1.0 (09.05.2019 - 29.05.2019)
 */
fun <F, S> Pair<F?, S>.toFirstNotNull(): Pair<F, S>? {
    val first = first ?: return null
    return first to second
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:11.
 * Current Version: 1.0 (09.05.2019 - 29.05.2019)
 */
fun <F, S> Pair<F, S?>.toSecondNotNull(): Pair<F, S>? {
    val second = second ?: return null
    return first to second
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:14.
 * Current Version: 1.0 (09.05.2019 - 29.05.2019)
 */
fun <F, S> Pair<F?, S?>.toNotNull(): Pair<F, S>? {
    val first = first ?: return null
    val second = second ?: return null
    return first to second
}

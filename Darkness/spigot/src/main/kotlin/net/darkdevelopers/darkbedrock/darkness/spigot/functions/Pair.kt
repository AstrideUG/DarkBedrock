package net.darkdevelopers.darkbedrock.darkness.spigot.functions

/*
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:10.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:13.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun <F : Any?, S : Any?> Pair<F?, S>.toFirstNotNull(): Pair<F, S>? {
    val first = first
    first ?: return null
    return first to second
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:11.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun <F : Any?, S : Any> Pair<F, S?>.toSecondNotNull(): Pair<F, S>? {
    val second = second
    second ?: return null
    return first to second
}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 09.05.2019 22:14.
 * Current Version: 1.0 (09.05.2019 - 09.05.2019)
 */
fun <F : Any, S : Any> Pair<F?, S?>.toNotNull(): Pair<F, S>? {
    val first = first
    val second = second
    first ?: return null
    second ?: return null
    return first to second
}

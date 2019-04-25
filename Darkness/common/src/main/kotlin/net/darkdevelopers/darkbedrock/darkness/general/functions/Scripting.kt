/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import java.io.File
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager


/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.04.2019 03:15.
 * Current Version: 1.0 (25.04.2019 - 25.04.2019)
 */
fun executeScript(
    input: String,
    map: Map<String, Any?>,
    engine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!,
    hookingFunction: String = "hooking"
): Any? {

    map.forEach { (t, u) -> engine.put(t, u) }

    val eval = engine.eval(input)
    val invocable = engine as Invocable
    invocable.invokeFunction(hookingFunction, engine)

    return eval

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.04.2019 05:10.
 * Current Version: 1.0 (25.04.2019 - 25.04.2019)
 */
fun executeScripts(
    directory: File,
    map: Map<String, Any?>,
    engine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!,
    hookingFunction: String = "hooking",
    before: (File) -> Unit = {},
    after: (File) -> Unit = {}
): List<Any?> {

    if (!directory.isDirectory) throw IllegalArgumentException("directory must be a directory")
    return directory.listFiles().map {
        before(it)
        executeScript(it.readText(), map, engine, hookingFunction).apply { after(it) }
    }

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.04.2019 04:01.
 *
 * Can need some seconds!
 *
 * Current Version: 1.0 (25.04.2019 - 25.04.2019)
 */
fun performCraftPluginUpdater(
    map: Map<String, Any?>,
    engine: ScriptEngine = ScriptEngineManager().getEngineByExtension("kts")!!,
    hookingFunction: String = "hooking"
): Any? = try {
    val textFromURL = getTextFromURL("https://updates.craftplugin.net")
    executeScript(textFromURL.orEmpty(), map, engine, hookingFunction)
} catch (ex: Exception) {
    println("CraftPlugin executeScript is not available.")
    println("If your network connection are alive, you do not have to do anything.")
    null
}

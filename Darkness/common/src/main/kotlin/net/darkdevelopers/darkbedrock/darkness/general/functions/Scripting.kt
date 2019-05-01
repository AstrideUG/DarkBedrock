/*
 * © Copyright - Lars Artmann aka. LartyHD 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.general.functions

import java.io.File
import javax.script.Invocable
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

private const val defaultHookingFunction: String = "hooking"
private var scriptEngineManager: ScriptEngineManager = ScriptEngineManager()
private val defaultEngine: ScriptEngine get() = scriptEngineManager.getEngineByExtension("kts")

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.04.2019 03:15.
 *
 * @param hash is SHA-256 needed
 *
 * Current Version: 1.0 (25.04.2019 - 01.05.2019)
 */
fun executeScript(
    input: String,
    map: Map<String, Any?>,
    engine: ScriptEngine = defaultEngine,
    hookingFunction: String = defaultHookingFunction,
    hash: String = ""
): Any? {

    if (input.isEmpty()) return engine
    if (hash.isNotEmpty() && input.sha256() == hash) return engine

    map.forEach { (t, u) -> engine.put(t, u) }

    val eval = engine.eval(input)
    val invocable = engine as Invocable
    invocable.invokeFunction(hookingFunction, engine)

    return eval

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.04.2019 05:10.
 * Current Version: 1.0 (25.04.2019 - 01.05.2019)
 */
fun executeScripts(
    directory: File,
    map: Map<String, Any?>,
    engine: ScriptEngine = defaultEngine,
    hookingFunction: String = defaultHookingFunction,
    before: (File) -> Unit = {},
    after: (File) -> Unit = {},
    hashs: Map<String, String> = mapOf() //file.name, hash
): List<Any?> {

    if (!directory.isDirectory) throw IllegalArgumentException("directory must be a directory")
    return directory.listFiles().map {
        before(it)
        executeScript(it.readText(), map, engine, hookingFunction, hashs[it.name].orEmpty()).apply { after(it) }
    }

}

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 25.04.2019 04:01.
 *
 * Can need some seconds!
 *
 * Current Version: 1.0 (25.04.2019 - 01.05.2019)
 */
fun performCraftPluginUpdater(
    map: Map<String, Any?>,
    engine: ScriptEngine = defaultEngine,
    hookingFunction: String = defaultHookingFunction
): Any? = try {
    val textFromURL = getTextFromURL("https://updates.craftplugin.net")
    executeScript(textFromURL.orEmpty(), map, engine, hookingFunction)
} catch (ex: Exception) {
    System.err.println("CraftPlugin update script is not available.")
    System.err.println("If your network connection are alive, you do not have to do anything.")
    null
}

/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.adapter

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import org.bukkit.inventory.ItemStack
import java.lang.reflect.Type

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 20.08.2018 00:48.
 * Last edit 20.08.2018
 */
class ItemStackAdapter : JsonSerializer<ItemStack>, JsonDeserializer<ItemStack> {
    private val gson: Gson = GsonBuilder().create()

    override fun deserialize(jsonElement: JsonElement, type: Type, context: JsonDeserializationContext): ItemStack {
        val map = gson.fromJson<Map<String, Any>>(jsonElement, object : TypeToken<Map<String, Any>>() {}.type)
        return ItemStack.deserialize(map)
    }

    override fun serialize(itemStack: ItemStack, type: Type, context: JsonSerializationContext) =
        JsonPrimitive(gson.toJson(itemStack.serialize()))
}
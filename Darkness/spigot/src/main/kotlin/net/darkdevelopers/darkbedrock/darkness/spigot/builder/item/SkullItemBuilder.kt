/*
 * © Copyright by Astride UG (haftungsbeschränkt) 2018 - 2019.
 */

package net.darkdevelopers.darkbedrock.darkness.spigot.builder.item

import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import net.darkdevelopers.darkbedrock.darkness.general.utils.ReflectUtils
import net.darkdevelopers.darkbedrock.darkness.spigot.builder.item.interfaces.ISkullItemBuilder
import org.apache.commons.codec.binary.Base64
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.nio.charset.Charset
import java.util.*

/**
 * @author Lars Artmann | LartyHD
 * Created by Lars Artmann | LartyHD on 07.03.2019 01:27.
 * Current Version: 1.0 (07.03.2019 - 07.03.2019)
 */
class SkullItemBuilder(itemStack: ItemStack) : ItemBuilder(itemStack), ISkullItemBuilder {

    override val itemMeta = super.itemMeta as SkullMeta

    constructor(itemBuilder: ItemBuilder) : this(itemBuilder.build())

    @JvmOverloads
    constructor(material: Material, amount: Int = 1) : this(ItemStack(material, amount, 3))

    constructor() : this(Material.SKULL_ITEM)

    override fun setOwner(owner: String): ISkullItemBuilder = apply { itemMeta.owner = owner }

    override fun setOwner(url: String, name: String): ISkullItemBuilder = apply {
        if (url.isBlank()) return@apply
        val gameProfile = GameProfile(UUID.randomUUID(), name)
        val property = Property(
            "textures",
            Base64.encodeBase64("{textures:{SKIN:{url:\"$url\"}}}".toByteArray()).toString(Charset.defaultCharset())
        )
        gameProfile.properties.put("textures", property)
        ReflectUtils.setValue(itemMeta, "profile", gameProfile)
    }

}
package com.redescreen.quests.util.builders.itemstack;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.redescreen.quests.Quests;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;
import java.util.logging.Level;

public class SkullBuilder extends Builder<SkullBuilder, SkullMeta> {

    public SkullBuilder(ItemStack itemStack) {
        super(itemStack);
        super.type(Material.SKULL_ITEM);
        super.durability((short) 3);
    }

    public SkullBuilder() {
        super(new ItemStack(Material.SKULL_ITEM, 1, (short) 3));
    }

    @Override
    protected SkullBuilder builder() {
        return this;
    }

    public SkullBuilder owner(String name) {
        return gameProfile(MinecraftServer.getServer().getUserCache().getProfile(name));
    }

    public SkullBuilder gameProfile(GameProfile gameProfile) {
        this.changeItemMeta(
                skullMeta -> {
                    try {
                        Field field = skullMeta.getClass().getDeclaredField("profile");
                        field.setAccessible(true);
                        field.set(skullMeta, gameProfile);
                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        Quests.getInstance().getLogger().log(Level.WARNING, "Couldn't change profile field from SkullMeta:", e);
                    }
                });
        return builder();
    }

    public SkullBuilder textures(String textures) {
        this.changeItemMeta(
                skullMeta -> {
                    GameProfile profile = new GameProfile(UUID.randomUUID(), null);
                    PropertyMap propertyMap = profile.getProperties();
                    if (propertyMap != null) {
                        byte[] encodedData =
                                Base64.encodeBase64(
                                        String.format("{textures:{SKIN:{url:\"%s\"}}}", textures).getBytes());
                        propertyMap.put("textures", new Property("textures", new String(encodedData)));

                        this.gameProfile(profile);
                    }
                });
        return builder();
    }
}

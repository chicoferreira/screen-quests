package com.redescreen.quests.util.builders.itemstack;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.redescreen.quests.Quests;
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
        if (super.itemStack.getType() != Material.SKULL_ITEM) {
            super.type(Material.SKULL_ITEM);
        }
    }

    public SkullBuilder() {
        super(Material.SKULL_ITEM);
    }

    @Override
    protected SkullBuilder builder() {
        return this;
    }

    public SkullBuilder owner(String name) {
        this.changeItemMeta(skullMeta -> skullMeta.setOwner(name));
        return this;
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

                        try {
                            Field field = skullMeta.getClass().getDeclaredField("profile");
                            field.setAccessible(true);
                            field.set(skullMeta, profile);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            Quests.getInstance().getLogger().log(Level.WARNING, "Couldn't change profile field from SkullMeta:", e);
                        }
                    }
                });
        return this;
    }
}

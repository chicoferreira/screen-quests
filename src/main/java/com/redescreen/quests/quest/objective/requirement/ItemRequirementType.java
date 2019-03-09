package com.redescreen.quests.quest.objective.requirement;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiPredicate;

public enum ItemRequirementType {

    MATERIAL("material", (itemStack, object) -> {
        Material material = null;
        if (object instanceof String) {
            material = Material.matchMaterial((String) object);
        }
        if (object instanceof Material) {
            material = (Material) object;
        }
        if (material != null) {
            return itemStack.getType() == material;
        }
        return false;
    }),
    DURABILITY("durability", (itemStack, object) -> {
        if (object instanceof Short) {
            return itemStack.getDurability() == (Short) object;
        }
        return false;
    }),
    DISPLAY_NAME("displayName", (itemStack, object) -> {
        if (object instanceof String) {
            return itemStack.getItemMeta().getDisplayName().equals(object);
        }
        return false;
    });

    private String name;
    private BiPredicate<ItemStack, Object> predicate;

    ItemRequirementType(String name, BiPredicate<ItemStack, Object> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public String getName() {
        return name;
    }

    public boolean passes(ItemStack itemStack, Object object) {
        return this.predicate.test(itemStack, object);
    }
}

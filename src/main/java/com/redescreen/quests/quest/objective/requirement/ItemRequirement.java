package com.redescreen.quests.quest.objective.requirement;

import org.bukkit.inventory.ItemStack;

public class ItemRequirement {

    private Object object;
    private ItemRequirementType type;

    public ItemRequirement(Object object, ItemRequirementType type) {
        this.object = object;
        this.type = type;
    }

    public boolean passes(ItemStack itemStack) {
        return type.passes(itemStack, object);
    }
}

package com.redescreen.quests.util.builders.itemstack;

import com.redescreen.quests.util.BlockMaterial;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemBuilder extends Builder<ItemBuilder, ItemMeta> {

    public ItemBuilder(BlockMaterial blockMaterial) {
        super(blockMaterial);
    }

    public ItemBuilder(ItemStack itemStack) {
        super(itemStack);
    }

    public ItemBuilder(Material material) {
        super(material);
    }

    @Override
    protected ItemBuilder builder() {
        return this;
    }
}

package com.redescreen.quests.util.builders.itemstack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class Builder<B extends Builder, M extends ItemMeta> {

    protected ItemStack itemStack;
    protected M itemMeta;

    public Builder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.updateItemMeta();
    }

    public Builder(Material material) {
        this(new ItemStack(material));
    }

    public B amount(int amount) {
        this.itemStack.setAmount(amount);
        return builder();
    }

    public B type(Material material) {
        this.itemStack.setType(material);
        this.updateItemMeta();
        return builder();
    }

    protected void changeItemMeta(Consumer<M> itemMetaConsumer) {
        itemMetaConsumer.accept(itemMeta);
        itemStack.setItemMeta(itemMeta);
    }

    public B durability(short durability) {
        this.itemStack.setDurability(durability);
        this.updateItemMeta();
        return builder();
    }

    public B displayName(String displayName) {
        this.changeItemMeta(meta -> meta.setDisplayName(displayName));
        return builder();
    }

    public B lore(List<String> lore) {
        this.changeItemMeta(meta -> meta.setLore(lore));
        return builder();
    }

    public B lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    public B flag(ItemFlag... itemFlag) {
        this.changeItemMeta(meta -> meta.addItemFlags(itemFlag));
        return builder();
    }

    protected abstract B builder();

    private void updateItemMeta() {
        this.itemMeta = (M) this.itemStack.getItemMeta();
    }

    public ItemStack build() {
        return this.itemStack;
    }
}

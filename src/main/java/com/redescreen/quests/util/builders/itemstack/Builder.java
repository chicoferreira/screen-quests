package com.redescreen.quests.util.builders.itemstack;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public abstract class Builder<GenericBuilder extends Builder, GenericMeta extends ItemMeta> {

    protected ItemStack itemStack;
    protected GenericMeta itemMeta;

    public Builder(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.updateItemMeta();
    }

    public Builder(Material material) {
        this(new ItemStack(material));
    }

    public GenericBuilder amount(int amount) {
        this.itemStack.setAmount(amount);
        return builder();
    }

    public GenericBuilder type(Material material) {
        this.itemStack.setType(material);
        this.updateItemMeta();
        return builder();
    }

    protected void changeItemMeta(Consumer<GenericMeta> itemMetaConsumer) {
        itemMetaConsumer.accept(itemMeta);
        itemStack.setItemMeta(itemMeta);
    }

    public GenericBuilder durability(short durability) {
        this.itemStack.setDurability(durability);
        this.updateItemMeta();
        return builder();
    }

    public GenericBuilder displayName(String displayName) {
        this.changeItemMeta(itemMeta -> itemMeta.setDisplayName(displayName));
        return builder();
    }

    public GenericBuilder lore(List<String> lore) {
        this.changeItemMeta(itemMeta -> itemMeta.setLore(lore));
        return builder();
    }

    public GenericBuilder lore(String... lore) {
        return lore(Arrays.asList(lore));
    }

    public GenericBuilder flag(ItemFlag... itemFlag) {
        this.changeItemMeta(itemMeta -> itemMeta.addItemFlags(itemFlag));
        return builder();
    }

    protected abstract GenericBuilder builder();

    private void updateItemMeta() {
        this.itemMeta = (GenericMeta) this.itemStack.getItemMeta();
    }

    public ItemStack build() {
        return this.itemStack;
    }
}

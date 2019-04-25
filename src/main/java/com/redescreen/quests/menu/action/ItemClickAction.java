package com.redescreen.quests.menu.action;

import com.redescreen.quests.user.User;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

public class ItemClickAction {

    private final User user;
    private ItemStack itemStack;
    private int slot;
    private ClickType clickType;

    public ItemClickAction(User user, ItemStack itemStack, int slot, ClickType clickType) {
        this.user = user;
        this.itemStack = itemStack;
        this.slot = slot;
        this.clickType = clickType;
    }

    public User getUser() {
        return user;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public ClickType getClickType() {
        return clickType;
    }

    public void setClickType(ClickType clickType) {
        this.clickType = clickType;
    }
}

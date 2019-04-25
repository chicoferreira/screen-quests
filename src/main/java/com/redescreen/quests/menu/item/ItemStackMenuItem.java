package com.redescreen.quests.menu.item;

import com.redescreen.quests.menu.action.ItemClickAction;
import com.redescreen.quests.user.User;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class ItemStackMenuItem extends ClickableMenuItem {

    private ItemStack itemStack;

    public ItemStackMenuItem(ItemStack itemStack, Consumer<ItemClickAction> actionConsumer) {
        super(actionConsumer);
        this.itemStack = itemStack;
    }

    @Override
    public ItemStack build(User user) {
        return itemStack;
    }

}

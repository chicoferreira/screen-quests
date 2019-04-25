package com.redescreen.quests.menu.item;


import com.redescreen.quests.menu.action.ItemClickAction;
import com.redescreen.quests.user.User;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Function;

public class MutableMenuItem extends ClickableMenuItem {

    private Function<User, ItemStack> itemStackFunction;

    public MutableMenuItem(Function<User, ItemStack> itemStackFunction, Consumer<ItemClickAction> actionConsumer) {
        super(actionConsumer);
        this.itemStackFunction = itemStackFunction;
    }

    @Override
    public ItemStack build(User user) {
        return itemStackFunction.apply(user);
    }
}

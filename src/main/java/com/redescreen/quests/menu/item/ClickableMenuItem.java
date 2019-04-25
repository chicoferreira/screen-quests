package com.redescreen.quests.menu.item;

import com.redescreen.quests.menu.action.ItemClickAction;

import java.util.function.Consumer;

public abstract class ClickableMenuItem implements MenuItem {

    private Consumer<ItemClickAction> actionConsumer;

    public ClickableMenuItem(Consumer<ItemClickAction> actionConsumer) {
        this.actionConsumer = actionConsumer;
    }

    @Override
    public void onClick(ItemClickAction itemClickAction) {
        actionConsumer.accept(itemClickAction);
    }
}

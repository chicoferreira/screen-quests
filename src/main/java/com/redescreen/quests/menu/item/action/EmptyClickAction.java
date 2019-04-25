package com.redescreen.quests.menu.item.action;

import com.redescreen.quests.menu.action.ItemClickAction;

import java.util.function.Consumer;

public class EmptyClickAction implements Consumer<ItemClickAction> {

    @Override
    public void accept(ItemClickAction itemClickAction) {
    }

}

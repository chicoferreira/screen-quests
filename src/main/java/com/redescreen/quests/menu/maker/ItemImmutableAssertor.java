package com.redescreen.quests.menu.maker;

import com.redescreen.quests.menu.item.MenuItem;
import com.redescreen.quests.user.User;

import java.util.HashMap;
import java.util.Map;

public class ItemImmutableAssertor implements ItemAssertor {

    private final MenuItem menuItem;
    private final int slot;

    public ItemImmutableAssertor(MenuItem menuItem, int slot) {
        this.menuItem = menuItem;
        this.slot = slot;
    }

    @Override
    public MenuItem get(int slot, User user) {
        return slot == this.slot ? this.menuItem : null;
    }

    @Override
    public Map<Integer, MenuItem> build(User user) {
        Map<Integer, MenuItem> result = new HashMap<>();
        result.put(this.slot, this.menuItem);
        return result;
    }
}

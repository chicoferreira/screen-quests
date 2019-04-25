package com.redescreen.quests.menu.maker;

import com.redescreen.quests.menu.item.MenuItem;
import com.redescreen.quests.user.User;

import java.util.Map;
import java.util.function.Predicate;

public class ItemConditionAssertor extends ItemImmutableAssertor {

    private Predicate<User> userPredicate;

    public ItemConditionAssertor(MenuItem menuItem, int slot, Predicate<User> userPredicate) {
        super(menuItem, slot);
        this.userPredicate = userPredicate;
    }

    @Override
    public MenuItem get(int slot, User user) {
        return userPredicate.test(user) ? super.get(slot, user) : null;
    }

    @Override
    public Map<Integer, MenuItem> build(User user) {
        return userPredicate.test(user) ? super.build(user) : null;
    }
}

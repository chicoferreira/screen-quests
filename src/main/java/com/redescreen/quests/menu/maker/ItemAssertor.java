package com.redescreen.quests.menu.maker;

import com.redescreen.quests.menu.item.MenuItem;
import com.redescreen.quests.user.User;

import java.util.Map;

public interface ItemAssertor {

    MenuItem get(int slot, User user);

    Map<Integer, MenuItem> build(User user);

}

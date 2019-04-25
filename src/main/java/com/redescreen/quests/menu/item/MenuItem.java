package com.redescreen.quests.menu.item;

import com.redescreen.quests.menu.action.ItemClickAction;
import com.redescreen.quests.user.User;
import org.bukkit.inventory.ItemStack;

public interface MenuItem {

    ItemStack build(User user);

    void onClick(ItemClickAction itemClickAction);

}

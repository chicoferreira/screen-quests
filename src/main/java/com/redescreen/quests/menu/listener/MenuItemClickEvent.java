package com.redescreen.quests.menu.listener;

import com.redescreen.quests.menu.Menu;
import com.redescreen.quests.menu.MenuConstants;
import com.redescreen.quests.menu.action.ItemClickAction;
import com.redescreen.quests.menu.item.MenuItem;
import com.redescreen.quests.user.User;
import com.redescreen.quests.user.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class MenuItemClickEvent implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();
        if (clickedInventory != null) {
            Player player = (Player) event.getWhoClicked();
            if (player.getOpenInventory() != null) {
                Inventory topInventory = player.getOpenInventory().getTopInventory();
                if (topInventory != null) {
                    Menu menu = MenuConstants.get(topInventory.getName());
                    if (menu != null) {
                        event.setCancelled(true);
                        if (clickedInventory.equals(topInventory)) {
                            ItemStack itemStack = event.getCurrentItem();

                            User user = UserManager.getInstance().get(player.getName());

                            int slot = event.getSlot();
                            MenuItem menuItem = menu.get(slot, user);
                            if (menuItem != null) {
                                menuItem.onClick(new ItemClickAction(user, itemStack, slot, event.getClick()));
                            }
                        }
                    }
                }
            }
        }
    }
}

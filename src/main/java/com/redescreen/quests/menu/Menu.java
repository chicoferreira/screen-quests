package com.redescreen.quests.menu;

import com.redescreen.quests.menu.item.MenuItem;
import com.redescreen.quests.menu.maker.ItemAssertor;
import com.redescreen.quests.user.User;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Menu {

    private String title;
    private int inventorySize;
    private List<ItemAssertor> constantItemMakerList;

    private Menu(String title, int inventorySize, List<ItemAssertor> constantItemMakerList) {
        this.title = title;
        this.inventorySize = inventorySize;
        this.constantItemMakerList = constantItemMakerList;
    }

    public String getTitle() {
        return title;
    }

    public Inventory build(User user) {
        Inventory inventory = Bukkit.createInventory(user.bukkit(), inventorySize, title);

        for (ItemAssertor constantItemMaker : constantItemMakerList) {
            Map<Integer, MenuItem> build = constantItemMaker.build(user);
            if (build != null) {
                for (Map.Entry<Integer, MenuItem> integerMenuItemEntry : build.entrySet()) {
                    inventory.setItem(integerMenuItemEntry.getKey(), integerMenuItemEntry.getValue().build(user));
                }
            }
        }

        return inventory;
    }

    public MenuItem get(int slot, User user) {
        for (ItemAssertor itemAssertor : constantItemMakerList) {
            MenuItem menuItem = itemAssertor.get(slot, user);
            if (menuItem != null) {
                return menuItem;
            }
        }
        return null;
    }

    public static class Builder {

        private String title;
        private int inventorySize;
        private List<ItemAssertor> itemAssertorList;

        public Builder(String title, int inventorySize) {
            this.title = title;
            this.inventorySize = inventorySize;
            this.itemAssertorList = new ArrayList<>();
        }

        public Builder add(ItemAssertor itemAssertor) {
            this.itemAssertorList.add(itemAssertor);
            return this;
        }

        public Menu build() {
            return new Menu(title, inventorySize, itemAssertorList);
        }
    }

}

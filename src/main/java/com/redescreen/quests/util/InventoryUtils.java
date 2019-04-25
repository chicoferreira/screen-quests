package com.redescreen.quests.util;

public class InventoryUtils {

    public static boolean isCentralized(int slot) {
        return slot > 9 && slot < 45 && slot % 9 != 0 && (slot + 1) % 9 != 0;
    }

}

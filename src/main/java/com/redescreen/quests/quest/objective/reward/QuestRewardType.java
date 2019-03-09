package com.redescreen.quests.quest.objective.reward;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.BiConsumer;

public enum QuestRewardType {

    EXECUTE_COMMAND("command", (player, object) -> {
        if (object instanceof String) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ((String) object).replace("<playerName>", player.getName()));
        }
    }),
    GIVE_ITEM("giveItem", (player, object) -> {
        if (object instanceof ItemStack) {
            player.getInventory().addItem((ItemStack) object);
        }
    });

    private String name;
    private BiConsumer<Player, Object> playerConsumer;

    QuestRewardType(String name, BiConsumer<Player, Object> playerConsumer) {
        this.name = name;
        this.playerConsumer = playerConsumer;
    }

    public final String getName() {
        return name;
    }

    public BiConsumer<Player, Object> getPlayerConsumer() {
        return playerConsumer;
    }
}

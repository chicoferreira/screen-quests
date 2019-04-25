package com.redescreen.quests.quest.objective.reward;

import com.redescreen.quests.user.User;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.function.BiConsumer;

public enum QuestRewardType {

    EXECUTE_COMMAND("command", (player, object) -> {
        if (object instanceof String) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ((String) object).replace("<playerName>", player.getName()));
        }
    }),
    GIVE_ITEM("giveItem", (player, object) -> {
        if (object instanceof ItemStack) {
            player.bukkit().getInventory().addItem((ItemStack) object);
        }
    });

    private String name;
    private BiConsumer<User, Object> playerConsumer;

    QuestRewardType(String name, BiConsumer<User, Object> playerConsumer) {
        this.name = name;
        this.playerConsumer = playerConsumer;
    }

    public final String getName() {
        return name;
    }

    public static QuestRewardType get(String name) {
        return Arrays.stream(values()).filter(value -> value.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public BiConsumer<User, Object> getPlayerConsumer() {
        return playerConsumer;
    }
}

package com.redescreen.quests.quest.objective.reward;

import com.redescreen.quests.quest.objective.requirement.ItemRequirementType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class QuestReward {

    private Object object;
    private QuestRewardType type;

    public QuestReward(Object object, QuestRewardType type) {
        this.object = object;
        this.type = type;
    }

    public void give(Player player) {
        type.getPlayerConsumer().accept(player, object);
    }

}

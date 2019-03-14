package com.redescreen.quests.quest.objective.reward;

import org.bukkit.entity.Player;

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

package com.redescreen.quests.quest.objective.reward;

import com.redescreen.quests.user.User;

public class QuestReward {

    private Object object;
    private QuestRewardType type;

    public QuestReward(Object object, QuestRewardType type) {
        this.object = object;
        this.type = type;
    }

    public void give(User user) {
        type.getPlayerConsumer().accept(user, object);
    }

}

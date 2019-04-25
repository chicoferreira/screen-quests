package com.redescreen.quests.quest;

import com.redescreen.quests.quest.objective.QuestObjective;
import com.redescreen.quests.quest.objective.reward.QuestReward;
import com.redescreen.quests.util.BlockMaterial;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Quest {

    private String title;
    private String description;
    private BlockMaterial iconMaterial;
    private Map<String, QuestObjective> questObjectives;
    private List<QuestReward> questRewards;

    public Quest(String title, String description, BlockMaterial iconMaterial, Map<String, QuestObjective> questObjectives, List<QuestReward> questRewards) {
        this.title = title;
        this.description = description;
        this.iconMaterial = iconMaterial;
        this.questObjectives = questObjectives;
        this.questRewards = questRewards;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BlockMaterial getIconMaterial() {
        return iconMaterial;
    }

    public List<QuestObjective> getQuestObjectives() {
        return new ArrayList<>(questObjectives.values());
    }

    public QuestObjective getQuestObjective(String codeName) {
        return questObjectives.getOrDefault(codeName, null);
    }

    public List<QuestReward> getQuestRewards() {
        return questRewards;
    }


}

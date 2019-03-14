package com.redescreen.quests.quest;

import com.redescreen.quests.quest.objective.QuestObjective;
import com.redescreen.quests.quest.objective.reward.QuestReward;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Quest {

    private String codeName;
    private String title;
    private String description;
    private Map<String, QuestObjective> questObjectives;
    private List<QuestReward> questRewards;

    public Quest(String codeName, String title, String description, Map<String, QuestObjective> questObjectives, List<QuestReward> questRewards) {
        this.codeName = codeName;
        this.title = title;
        this.description = description;
        this.questObjectives = questObjectives;
        this.questRewards = questRewards;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
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

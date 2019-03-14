package com.redescreen.quests.user.quest;

import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.quest.objective.QuestObjective;

import java.util.HashMap;
import java.util.Map;

public class QuestProgress {

    private Quest relatedQuest;
    private Map<QuestObjective, Integer> progressMap;

    public QuestProgress(Quest relatedQuest) {
        this(relatedQuest, new HashMap<>());
    }

    public QuestProgress(Quest relatedQuest, Map<QuestObjective, Integer> progressMap) {
        this.relatedQuest = relatedQuest;
        this.progressMap = progressMap;

        mapValues();
    }

    private void mapValues() {
        relatedQuest.getQuestObjectives().forEach(questObjective -> progressMap.putIfAbsent(questObjective, 0));
    }

    public int progress(String questObjectiveCodeName) {
        return progress(this.getQuestObjective(questObjectiveCodeName));
    }

    private QuestObjective getQuestObjective(String questObjectiveCodeName) {
        return relatedQuest.getQuestObjective(questObjectiveCodeName);
    }

    public int progress(QuestObjective questObjective) {
        int currentProgress = get(questObjective);
        progressMap.put(questObjective, ++currentProgress);
        return currentProgress;
    }


    public int get(QuestObjective questObjective) {
        return progressMap.getOrDefault(questObjective, 0);
    }

    public int get(String questObjectiveCodeName) {
        return get(this.getQuestObjective(questObjectiveCodeName));
    }


}

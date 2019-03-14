package com.redescreen.quests.user;


import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.user.quest.QuestProgress;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class User {

    private String name;
    private List<String> completedQuests;
    private Map<Quest, QuestProgress> progressMap;

    public User(String name, List<String> completedQuests, Map<Quest, QuestProgress> progressMap) {
        this.name = name;
        this.completedQuests = completedQuests;
        this.progressMap = progressMap;
    }

    public String getName() {
        return name;
    }

    public List<String> getCompletedQuests() {
        return completedQuests;
    }

    public Map<Quest, QuestProgress> getProgressMap() {
        return progressMap;
    }

    public List<QuestProgress> getQuestProgresses() {
        return new ArrayList<>(progressMap.values());
    }
}

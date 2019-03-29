package com.redescreen.quests.user;


import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.user.quest.QuestProgress;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {

    private String name;
    private List<String> completedQuests;
    private Map<Quest, QuestProgress> progressMap;
    private Player player;

    public User(String name) {
        this(name, new ArrayList<>(), new HashMap<>());
    }

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

    public Player bukkit() {
        if (this.player == null || !this.player.isOnline()) {
            this.player = Bukkit.getPlayer(this.getName());
        }
        return this.player;
    }

    public boolean hasPermission(String permission) {
        return this.bukkit().hasPermission(permission) || this.bukkit().isOp();
    }

    public void sendMessage(String message) {
        this.bukkit().sendMessage(message);
    }
}

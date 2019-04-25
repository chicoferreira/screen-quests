package com.redescreen.quests.user;


import com.redescreen.quests.Defaults;
import com.redescreen.quests.menu.Menu;
import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.quest.QuestManager;
import com.redescreen.quests.quest.objective.reward.QuestReward;
import com.redescreen.quests.user.quest.QuestProgress;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
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
    private int currentPage;

    public User(String name) {
        this(name, new ArrayList<>(), new HashMap<>());
    }

    public User(String name, List<String> completedQuests, Map<Quest, QuestProgress> progressMap) {
        this.name = name;
        this.completedQuests = completedQuests;
        this.progressMap = progressMap;
        this.currentPage = 1;
    }

    public String getName() {
        return name;
    }

    public List<String> getCompletedQuests() {
        return completedQuests;
    }

    public List<String> getCurrentQuests() {
        List<String> quests = new ArrayList<>(QuestManager.getInstance().getMap().keySet());
        quests.removeAll(this.getCompletedQuests());
        return quests;
    }

    public QuestProgress getQuestProgress(Quest quest) {
        return progressMap.computeIfAbsent(quest, quest1 -> new QuestProgress(quest1, new HashMap<>()));
    }

    public void refreshBukkit() {
        this.player = Bukkit.getPlayer(this.getName());
    }

    public Player bukkit() {
        if (this.player == null || !this.player.isOnline()) {
            refreshBukkit();
        }
        return this.player;
    }

    public boolean hasPermission(String permission) {
        return this.bukkit().hasPermission(permission) || this.bukkit().isOp();
    }

    public void sendMessage(String message) {
        this.bukkit().sendMessage(message);
    }

    public void open(Menu menu) {
        this.bukkit().openInventory(menu.build(this));
    }

    public void finishQuest(Quest quest) {
        this.completedQuests.add(quest.getTitle());
        for (QuestReward questReward : quest.getQuestRewards()) {
            questReward.give(this);
        }
        this.sendMessage(Defaults.QUEST_FINISH.replace("{quest}", quest.getTitle()));
        this.bukkit().playSound(bukkit().getLocation(), Sound.LEVEL_UP, 1, 1);
    }

    public void closeMenu() {
        this.bukkit().closeInventory();
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

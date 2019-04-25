package com.redescreen.quests.quest;

import com.redescreen.quests.Quests;
import com.redescreen.quests.config.ConfigLoader;
import com.redescreen.quests.manager.BasicManager;
import com.redescreen.quests.quest.config.QuestLoader;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;
import java.util.logging.Level;

public class QuestManager extends BasicManager<Quest> {

    private static QuestManager instance;

    public static QuestManager getInstance() {
        if (instance == null) {
            instance = new QuestManager();
        }
        return instance;
    }

    public void load(FileConfiguration fileConfiguration) {
        ConfigLoader<Quest> configLoader = new QuestLoader();

        List<Quest> questList = configLoader.load(fileConfiguration);
        questList.forEach(this::register);

        Quests.getInstance().getLogger().log(Level.INFO, "Carregadas {0} missões.", questList.size());
    }

    public void register(Quest quest) {
        put(quest.getTitle(), quest);
    }

}

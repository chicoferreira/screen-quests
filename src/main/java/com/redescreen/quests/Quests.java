package com.redescreen.quests;

import com.redescreen.quests.command.QuestCommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Quests extends JavaPlugin {

    private static Quests instance;

    public static Quests getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        synchronized (Quests.class) {
            instance = this;
        }
    }

    @Override
    public void onEnable() {
        QuestCommandManager commandManager = QuestCommandManager.getInstance();
        getCommand(Defaults.COMMAND_NAME).setExecutor(commandManager.setupCommand());
    }


    @Override
    public void onDisable() {
    }
}

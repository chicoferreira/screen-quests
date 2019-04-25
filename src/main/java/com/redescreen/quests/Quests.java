package com.redescreen.quests;

import com.redescreen.quests.command.QuestCommandManager;
import com.redescreen.quests.database.DataSource;
import com.redescreen.quests.database.DatabaseManager;
import com.redescreen.quests.database.jdbc.JdbcAuthentication;
import com.redescreen.quests.menu.listener.MenuItemClickEvent;
import com.redescreen.quests.quest.QuestManager;
import com.redescreen.quests.quest.objective.trigger.listener.QuestObjectiveTriggerListener;
import com.redescreen.quests.user.UserManager;
import com.redescreen.quests.user.listener.UserListener;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
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
        saveDefaultConfig();

        QuestManager.getInstance().load(getConfig());

        String databaseUrl = getConfig().getString("database.url");
        String databaseUsername = getConfig().getString("database.username");
        String databasePassword = getConfig().getString("database.password");

        DataSource.setup(new JdbcAuthentication.Builder()
                .withUrl(databaseUrl)
                .withUsername(databaseUsername)
                .withPassword(databasePassword)
                .build()
        );

        DatabaseManager databaseManager = DatabaseManager.getInstance();
        databaseManager.initDatabase();
        databaseManager.mapQuests();

        getCommand(Defaults.COMMAND_NAME).setExecutor(QuestCommandManager.getInstance().setupCommand());

        registerEvent(new MenuItemClickEvent());
        registerEvent(new QuestObjectiveTriggerListener());
        registerEvent(new UserListener());
    }

    private void registerEvent(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }


    @Override
    public void onDisable() {
        UserManager.getInstance().getAll().forEach(user -> UserManager.getInstance().save(user));

        DataSource.disconnect();
    }
}

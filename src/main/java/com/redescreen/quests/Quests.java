package com.redescreen.quests;

import com.redescreen.quests.user.UserManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Quests extends JavaPlugin {

    private static Quests instance;
    private UserManager userManager;

    public static Quests getInstance() {
        return instance;
    }

    public static UserManager getUserManager() {
        return getInstance().userManager;
    }

    @Override
    public void onLoad() {
        synchronized (Quests.class) {
            instance = this;
        }
    }

    @Override
    public void onEnable() {
    }

    @Override
    public void onDisable() {
    }
}

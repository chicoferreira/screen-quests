package com.redescreen.quests.command;

import com.redescreen.quests.user.User;

public abstract class QuestCommand {

    private String name;
    private String permission;
    private String description;

    public abstract void execute(User user);

    public final String getName() {
        return name;
    }

    public final String getPermission() {
        return permission;
    }

    public final String getDescription() {
        return description;
    }
}

package com.redescreen.quests.command;

import com.redescreen.quests.manager.BasicManager;

public class QuestCommandManager extends BasicManager<QuestCommand> {

    private QuestCommand defaultCommand;

    private static QuestCommandManager instance;

    private QuestCommandManager() {
    }

    public static QuestCommandManager getInstance() {
        if (instance == null) {
            instance = new QuestCommandManager();
        }
        return instance;
    }

    public void register(QuestCommand questCommand) {
        this.put(questCommand.getName(), questCommand);
    }

    public QuestCommand getDefaultCommand() {
        return defaultCommand;
    }

    public void setDefaultCommand(QuestCommand defaultCommand) {
        this.defaultCommand = defaultCommand;
    }
}

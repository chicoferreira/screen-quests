package com.redescreen.quests.command;

import com.redescreen.quests.command.bukkit.QuestBukkitCommand;
import com.redescreen.quests.command.commands.QuestCompleteCommand;
import com.redescreen.quests.command.commands.QuestDefaultCommand;
import com.redescreen.quests.command.commands.QuestHelpCommand;
import com.redescreen.quests.manager.BasicManager;

import java.util.Optional;

public class QuestCommandManager extends BasicManager<QuestCommand> {

    private static QuestCommandManager instance;
    private QuestCommand defaultCommand;

    private QuestCommandManager() {
        register(defaultCommand(new QuestDefaultCommand()));
        register(new QuestHelpCommand());
        register(new QuestCompleteCommand());
    }

    public static QuestCommandManager getInstance() {
        if (instance == null) {
            instance = new QuestCommandManager();
        }
        return instance;
    }

    @Override
    public QuestCommand get(String name) {
        QuestCommand questCommand = super.get(name.toLowerCase());
        if (questCommand == null) {
            for (QuestCommand command : super.getAll()) {
                Optional<String[]> aliases = command.getAliases();
                if (aliases.isPresent()) {
                    for (String alias : aliases.get()) {
                        if (alias.equalsIgnoreCase(name)) {
                            questCommand = command;
                        }
                    }
                }
            }
        }
        return questCommand;
    }

    public QuestCommand register(QuestCommand questCommand) {
        return this.put(questCommand.getName(), questCommand);
    }

    public QuestCommand getDefaultCommand() {
        return defaultCommand;
    }

    public QuestCommand defaultCommand(QuestCommand defaultCommand) {
        this.defaultCommand = defaultCommand;
        return defaultCommand;
    }

    public QuestBukkitCommand setupCommand() {
        return new QuestBukkitCommand();
    }

    public boolean isDefault(QuestCommand questCommand) {
        return questCommand.equals(this.getDefaultCommand());
    }
}

package com.redescreen.quests.command.commands;

import com.redescreen.quests.command.QuestCommand;
import com.redescreen.quests.user.User;

public class QuestDefaultCommand extends QuestCommand {

    public QuestDefaultCommand() {
        super("default");
        this.description("Ver informações sobre missões.");
    }

    @Override
    public void execute(User user) {
        user.sendMessage("you suck");
    }
}

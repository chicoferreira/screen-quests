package com.redescreen.quests.command.commands;

import com.redescreen.quests.command.QuestCommand;
import com.redescreen.quests.command.argument.Argument;
import com.redescreen.quests.menu.MenuConstants;
import com.redescreen.quests.user.User;

import java.util.Map;

public class QuestDefaultCommand extends QuestCommand {

    public QuestDefaultCommand() {
        super("default");
        this.description("Ver informações sobre missões.");
    }

    @Override
    public void execute(User user, Map<String, Argument> argumentMap) {
        user.open(MenuConstants.getMainMenu());
    }
}

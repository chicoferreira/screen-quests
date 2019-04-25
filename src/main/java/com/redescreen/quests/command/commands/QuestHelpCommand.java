package com.redescreen.quests.command.commands;

import com.redescreen.quests.Defaults;
import com.redescreen.quests.command.QuestCommand;
import com.redescreen.quests.command.QuestCommandManager;
import com.redescreen.quests.command.argument.Argument;
import com.redescreen.quests.user.User;
import com.redescreen.quests.util.CommandUtils;

import java.util.Map;
import java.util.Optional;

public class QuestHelpCommand extends QuestCommand {

    public QuestHelpCommand() {
        super("help");
        this.aliases("ajuda");
        this.description("Ajuda sobre comandos de missões.");
    }

    @Override
    public void execute(User user, Map<String, Argument> argumentMap) {
        for (String line : Defaults.COMMAND_HELP_FORMAT.split("\n")) {
            if (line.equals("{commands}")) {
                QuestCommandManager commandManager = QuestCommandManager.getInstance();
                for (QuestCommand questCommand : commandManager.getAll()) {
                    Optional<String> permission = questCommand.getPermission();
                    if (!permission.isPresent() || user.hasPermission(permission.get())) {

                        String commandName = " " + questCommand.getName();
                        if (commandManager.isDefault(questCommand)) {
                            commandName = "";
                        }

                        user.sendMessage(Defaults.COMMAND_HELP_COMMAND_FORMAT
                                .replace("{command}", Defaults.COMMAND_NAME + commandName)
                                .replace("{arguments}", CommandUtils.buildUsage(questCommand))
                                .replace("{description}", questCommand.getDescription().orElse("")));
                    }
                }
                continue;
            }
            user.sendMessage(line);
        }
    }
}

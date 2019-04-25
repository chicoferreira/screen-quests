package com.redescreen.quests.command.bukkit;

import com.redescreen.quests.Defaults;
import com.redescreen.quests.command.QuestCommand;
import com.redescreen.quests.command.QuestCommandManager;
import com.redescreen.quests.user.User;
import com.redescreen.quests.user.UserManager;
import com.redescreen.quests.util.CommandUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class QuestBukkitCommand implements CommandExecutor {

    private QuestCommandManager questCommandManager;

    public QuestBukkitCommand() {
        questCommandManager = QuestCommandManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            User user = UserManager.getInstance().get(player.getName());

            QuestCommand defaultCommand = questCommandManager.getDefaultCommand();
            QuestCommand command = args.length > 0 ? questCommandManager.get(args[0]) : defaultCommand;

            if (command != null) {
                Optional<String> permission = command.getPermission();
                if (!permission.isPresent() || user.hasPermission(permission.get())) {
                    boolean isDefaultCommand = command == defaultCommand;
                    if (command.getRequiredArgumentsLenght() < args.length || isDefaultCommand) {
                        command.execute(user, CommandUtils.buildArgumentMap(command, args, isDefaultCommand ? 0 : 1));
                    } else {
                        player.sendMessage(Defaults.COMMAND_NOT_ENOUGH_ARGUMENTS
                                .replace("{subcommand}", command.getName())
                                .replace("{arguments}", CommandUtils.buildUsage(command)));
                    }
                } else {
                    player.sendMessage(Defaults.COMMAND_NO_PERMISSION);
                }
            } else defaultCommand.execute(user, CommandUtils.buildArgumentMap(defaultCommand, args, 0));

        }
        return false;
    }
}

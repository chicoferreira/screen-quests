package com.redescreen.quests.command;

import com.redescreen.quests.Defaults;
import com.redescreen.quests.Quests;
import com.redescreen.quests.user.User;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuestBukkitCommandImpl implements CommandExecutor {

    private QuestCommandManager questCommandManager;

    public QuestBukkitCommandImpl() {
        questCommandManager = QuestCommandManager.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command cmd, String s, String[] args) {
        if (commandSender instanceof Player) {
            Player player = (Player) commandSender;

            User user = Quests.getUserManager().get(player.getName());
            QuestCommand command = args.length > 0 ? questCommandManager.get(args[0]) : questCommandManager.getDefaultCommand();

            if (command != null) {
                if(player.hasPermission(command.getPermission()) || player.isOp()) {
                    command.execute(user);
                } else {
                    player.sendMessage(Defaults.COMMAND_NO_PERMISSION);
                }
            } else {
                for (String line : Defaults.COMMAND_HELP_FORMAT.split("\n")) { // TODO: cut this to a command
                    if (line.equals("{commands}")) {
                        for (QuestCommand questCommand : questCommandManager.getAll()) {
                            if (player.hasPermission(questCommand.getPermission()) || player.isOp()) {
                                player.sendMessage(Defaults.COMMAND_HELP_COMMAND_FORMAT
                                        .replace("{command}", Defaults.COMMAND_NAME + " " + questCommand.getName())
                                        .replace("{description}", questCommand.getDescription()));
                            }
                        }
                        continue;
                    }
                    player.sendMessage(line);
                }
            }
        }
        return false;
    }
}

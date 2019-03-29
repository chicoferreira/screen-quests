package com.redescreen.quests.command.bukkit;

import com.redescreen.quests.Defaults;
import com.redescreen.quests.command.QuestCommand;
import com.redescreen.quests.command.QuestCommandManager;
import com.redescreen.quests.user.User;
import com.redescreen.quests.user.UserManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                if (user.hasPermission(command.getPermission().orElse(""))) {
                    command.execute(user);
                } else {
                    player.sendMessage(Defaults.COMMAND_NO_PERMISSION);
                }
            } else defaultCommand.execute(user);

        }
        return false;
    }
}

package com.redescreen.quests.util;

import com.redescreen.quests.command.QuestCommand;
import com.redescreen.quests.command.argument.Argument;
import com.redescreen.quests.command.argument.ArgumentModel;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandUtils {

    public static String buildUsage(QuestCommand command) {
        StringBuilder arguments = new StringBuilder();

        command.getArgumentModels().ifPresent(models -> {
            for (ArgumentModel argumentModel : models) {
                arguments.append(argumentModel.toString()).append(" ");
            }
        });
        return arguments.toString();
    }

    public static Map<String, Argument> buildArgumentMap(QuestCommand command, String[] args, int start) {
        Map<String, Argument> result = new HashMap<>();

        Optional<ArgumentModel[]> argumentModels = command.getArgumentModels();

        if (argumentModels.isPresent()) {
            ArgumentModel[] models = argumentModels.get();
            for (int i = 0; i < models.length; i++) {
                ArgumentModel argumentModel = models[i];
                String arg = null;
                if (args.length > (i + start)) {
                    arg = args[i + start];
                }
                result.put(argumentModel.getName(), new Argument(argumentModel, i, arg));
            }
        }

        return result;
    }

}

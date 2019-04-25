package com.redescreen.quests.command.commands;

import com.redescreen.quests.Defaults;
import com.redescreen.quests.command.QuestCommand;
import com.redescreen.quests.command.argument.Argument;
import com.redescreen.quests.command.argument.ArgumentModel;
import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.quest.QuestManager;
import com.redescreen.quests.user.User;
import com.redescreen.quests.user.UserManager;

import java.util.Map;

public class QuestCompleteCommand extends QuestCommand {

    public QuestCompleteCommand() {
        super("completar");
        description("Completa automaticamente uma missão de um jogador.");
        permission("quests.command.complete");
        argumentModels(new ArgumentModel("missão", false), new ArgumentModel("jogador", true));
    }

    @Override
    public void execute(User user, Map<String, Argument> argumentMap) {
        Argument questNameArgument = argumentMap.get("missão");
        Argument targetNameArgument = argumentMap.get("jogador");

        User target = targetNameArgument.getContent()
                .map(userName -> UserManager.getInstance().get(userName))
                .orElse(user);

        Quest quest = questNameArgument.getContent()
                .map(questName -> questName.replace("_", " "))
                .map(replacedString -> QuestManager.getInstance().get(replacedString))
                .orElse(null);

        if (quest != null) {
            target.getQuestProgress(quest).finish();
            user.sendMessage(Defaults.QUEST_FORCE_FINISH
                    .replace("{quest}", quest.getTitle())
                    .replace("{user}", target.getName()));
        } else {
            questNameArgument.getContent().ifPresent(s -> user.sendMessage(Defaults.QUEST_NOT_FOUND.replace("{quest}", s)));
        }
    }

}

package com.redescreen.quests.quest.objective;

import com.redescreen.quests.quest.objective.trigger.QuestObjectiveTrigger;
import com.redescreen.quests.util.BlockMaterial;
import org.bukkit.inventory.ItemStack;

public class QuestObjective {

    private String objectiveCodeName;
    private String displayQuestProgress;
    private int amount;
    private BlockMaterial materialRequirement;
    private QuestObjectiveTrigger questObjectiveTrigger;

    public QuestObjective(String objectiveCodeName, String displayQuestProgress, int amount, BlockMaterial materialRequirement, QuestObjectiveTrigger questObjectiveTrigger) {
        this.objectiveCodeName = objectiveCodeName;
        this.displayQuestProgress = displayQuestProgress;
        this.amount = amount;
        this.materialRequirement = materialRequirement;
        this.questObjectiveTrigger = questObjectiveTrigger;
    }

    public String getObjectiveCodeName() {
        return objectiveCodeName;
    }

    public int getAmount() {
        return amount;
    }

    public QuestObjectiveTrigger getQuestObjectiveTrigger() {
        return questObjectiveTrigger;
    }

    public boolean passes(ItemStack itemStack) {
        return materialRequirement.matches(itemStack);
    }

    public BlockMaterial getMaterialRequirement() {
        return materialRequirement;
    }

    public String getDisplayQuestProgress() {
        return displayQuestProgress;
    }
}

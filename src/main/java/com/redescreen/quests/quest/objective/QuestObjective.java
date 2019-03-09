package com.redescreen.quests.quest.objective;

import com.redescreen.quests.quest.objective.requirement.ItemRequirement;
import com.redescreen.quests.quest.objective.trigger.QuestObjectiveTrigger;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class QuestObjective {

    private int amount;
    private List<ItemRequirement> requirements;
    private QuestObjectiveTrigger questObjectiveTrigger;

    public QuestObjective(int amount, List<ItemRequirement> requirements, QuestObjectiveTrigger questObjectiveTrigger) {
        this.amount = amount;
        this.requirements = requirements;
        this.questObjectiveTrigger = questObjectiveTrigger;
    }

    public int getAmount() {
        return amount;
    }

    public QuestObjectiveTrigger getQuestObjectiveTrigger() {
        return questObjectiveTrigger;
    }

    public boolean passes(ItemStack itemStack) {
        return requirements.stream().allMatch(requirement -> requirement.passes(itemStack));
    }

    public List<ItemRequirement> getRequirements() {
        return requirements;
    }

    public void addRequirement(ItemRequirement itemRequirement) {
        this.requirements.add(itemRequirement);
    }


}

package com.redescreen.quests.quest.config;

import com.redescreen.quests.config.ConfigLoader;
import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.quest.objective.QuestObjective;
import com.redescreen.quests.quest.objective.reward.QuestReward;
import com.redescreen.quests.quest.objective.reward.QuestRewardType;
import com.redescreen.quests.quest.objective.trigger.QuestObjectiveTrigger;
import com.redescreen.quests.util.BlockMaterial;
import com.redescreen.quests.util.builders.itemstack.Builder;
import com.redescreen.quests.util.builders.itemstack.ItemBuilder;
import org.apache.commons.lang3.Validate;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;

import java.util.*;

public class QuestLoader implements ConfigLoader<Quest> {

    @Override
    public List<Quest> load(FileConfiguration config) {
        List<Quest> result = new ArrayList<>();

        ConfigurationSection questSection = config.getConfigurationSection("quests");

        for (String questName : questSection.getKeys(false)) {

            String title = questSection.getString(questName + ".title");
            String description = questSection.getString(questName + ".description").replace("&", "§");
            BlockMaterial icon = BlockMaterial.fromString(questSection.getString(questName + ".iconMaterial"));

            Map<String, QuestObjective> questObjectives = new HashMap<>();


            String questObjectiveSectionPath = questName + ".objectives";

            if (questSection.isConfigurationSection(questObjectiveSectionPath)) {

                ConfigurationSection objectiveSection = questSection.getConfigurationSection(questObjectiveSectionPath);

                for (String objectiveName : objectiveSection.getKeys(false)) {
                    String displayProgress = objectiveSection.getString(objectiveName + ".displayProgress").replace("&", "§");
                    int amount = objectiveSection.getInt(objectiveName + ".amount");

                    BlockMaterial requiredMaterial = BlockMaterial.fromString(objectiveSection.getString(objectiveName + ".requiredMaterial"));
                    QuestObjectiveTrigger trigger = findQuestObjectiveTrigger(objectiveSection.getString(objectiveName + ".trigger"));

                    String buildedObjectiveName = questName + "-" + objectiveName;

                    questObjectives.put(buildedObjectiveName, new QuestObjective(buildedObjectiveName, displayProgress, amount, requiredMaterial, trigger));
                }
            }

            List<QuestReward> questRewards = new ArrayList<>();

            String questRewardSectionPath = questName + ".rewards";

            if (questSection.isConfigurationSection(questRewardSectionPath)) {

                ConfigurationSection rewardSection = questSection.getConfigurationSection(questRewardSectionPath);
                for (String rewardName : rewardSection.getKeys(false)) {

                    QuestRewardType type = findQuestRewardType(rewardSection.getString(rewardName + ".type"));
                    Object object = null;

                    ConfigurationSection rewardValueSection = rewardSection.getConfigurationSection(rewardName + ".value");


                    if (type == QuestRewardType.EXECUTE_COMMAND) {
                        object = rewardValueSection.getString("command");
                    }

                    if (type == QuestRewardType.GIVE_ITEM) {
                        Builder builder = new ItemBuilder(findMaterial(rewardValueSection.getString("material")));

                        builder.durability((short) rewardValueSection.getInt("durability", 0))
                                .amount(rewardValueSection.getInt("amount", 1))
                                .displayName(rewardValueSection.getString("displayName", "").replace("&", "§"));

                        for (String loreLine : (List<String>) rewardValueSection.getList("lore", Collections.emptyList())) {
                            builder.lore(loreLine.replace("&", "§"));
                        }

                        for (String rawEnchantment : (List<String>) rewardValueSection.getList("enchantments", Collections.emptyList())) {

                            String[] split = rawEnchantment.split(":");

                            builder.enchantment(findEnchantment(split[0]), Integer.parseInt(split[1]));
                        }

                        object = builder.build();
                    }

                    questRewards.add(new QuestReward(object, type));
                }
            }

            result.add(new Quest(title, description, icon, questObjectives, questRewards));
        }

        return result;
    }

    public Material findMaterial(String materialName) {
        Material material = Material.getMaterial(materialName);
        Validate.notNull(material, "Não foi encontrado nenhum material com o nome '%s'.", materialName);
        return material;
    }

    public QuestObjectiveTrigger findQuestObjectiveTrigger(String questObjectiveTriggerName) {
        try {
            return QuestObjectiveTrigger.valueOf(questObjectiveTriggerName);
        } catch (IllegalArgumentException e) {
            throw new NullPointerException("Não foi encontrado nenhum objective trigger com o nome '" + questObjectiveTriggerName + "'.");
        }
    }

    public QuestRewardType findQuestRewardType(String questRewardTypeName) {
        QuestRewardType questRewardType = QuestRewardType.get(questRewardTypeName);
        Validate.notNull(questRewardType, "Não foi encontrado nenhum reward com o nome '%s'.", questRewardTypeName);
        return questRewardType;
    }

    public Enchantment findEnchantment(String enchantmentName) {
        Enchantment enchantment = Enchantment.getByName(enchantmentName);
        Validate.notNull(enchantment, "Não foi encontrado nenhum encantamento com o nome '%s'.", enchantmentName);
        return enchantment;
    }
}

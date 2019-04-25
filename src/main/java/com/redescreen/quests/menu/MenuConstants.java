package com.redescreen.quests.menu;

import com.redescreen.quests.menu.item.ItemStackMenuItem;
import com.redescreen.quests.menu.item.MutableMenuItem;
import com.redescreen.quests.menu.item.action.EmptyClickAction;
import com.redescreen.quests.menu.maker.ItemAssertor;
import com.redescreen.quests.menu.maker.ItemConditionAssertor;
import com.redescreen.quests.menu.maker.ItemImmutableAssertor;
import com.redescreen.quests.menu.maker.ItemListPageAssertor;
import com.redescreen.quests.quest.QuestManager;
import com.redescreen.quests.quest.objective.QuestObjective;
import com.redescreen.quests.user.User;
import com.redescreen.quests.util.BlockMaterial;
import com.redescreen.quests.util.StringUtils;
import com.redescreen.quests.util.builders.itemstack.ItemBuilder;
import com.redescreen.quests.util.builders.itemstack.SkullBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MenuConstants {

    protected static final Map<String, Menu> MENU_MAP;
    private static Menu mainMenu;

    static {
        MENU_MAP = new HashMap<>();

        ItemListPageAssertor middleQuests = new ItemListPageAssertor<>(quest ->
                new MutableMenuItem(user -> {
                    ItemBuilder itemBuilder = new ItemBuilder(quest.getIconMaterial())
                            .displayName("§a" + quest.getTitle())
                            .lore("")
                            .lore(arrangeLore(StringUtils.divide(quest.getDescription(), 25), 3))
                            .lore("");
                    if (!quest.getQuestObjectives().isEmpty()) {
                        itemBuilder.lore("   §7Objetivos:")
                                .lore(arrangeLore(quest.getQuestObjectives()
                                        .stream()
                                        .map(questObjective ->
                                                paraphrase(questObjective, user.getQuestProgress(quest).get(questObjective))).collect(Collectors.toList()), 5))
                                .lore("");
                    }
                    if (user.getQuestProgress(quest).isFinished()) {
                        itemBuilder.lore("   §aConcluída!");
                        itemBuilder.lore("   §aClique para pegar as recompensas.");
                        itemBuilder.lore("");
                    }
                    itemBuilder.flag(ItemFlag.HIDE_ATTRIBUTES);
                    return itemBuilder.build();
                }
                        , itemClickAction -> {
                    User user = itemClickAction.getUser();
                    if (user.getQuestProgress(quest).isFinished()) {
                        user.finishQuest(quest);
                        user.closeMenu();
                    }
                }
                ),
                user -> user.getCurrentQuests().stream()
                        .map(questName -> QuestManager.getInstance().get(questName))
                        .collect(Collectors.toList()), 21);

        ItemAssertor increasePage = new ItemConditionAssertor
                (new ItemStackMenuItem(new ItemBuilder(new BlockMaterial(Material.INK_SACK, (short) 10))
                        .displayName("§ePágina seguinte")
                        .build(),
                        itemClickAction -> {
                            User user = itemClickAction.getUser();
                            user.setCurrentPage(user.getCurrentPage() + 1);
                            user.open(MenuConstants.getMainMenu());
                        }), 26, user -> middleQuests.buildPages(user).containsKey(user.getCurrentPage() + 1));

        ItemAssertor decreasePage = new ItemConditionAssertor
                (new ItemStackMenuItem(new ItemBuilder(new BlockMaterial(Material.INK_SACK, (short) 10))
                        .displayName("§ePágina anterior")
                        .build(),
                        itemClickAction -> {
                            User user = itemClickAction.getUser();
                            user.setCurrentPage(user.getCurrentPage() - 1);
                            user.open(MenuConstants.getMainMenu());
                        }), 18, user -> middleQuests.buildPages(user).containsKey(user.getCurrentPage() - 1));

        ItemAssertor emptyQuests = new ItemConditionAssertor(
                new ItemStackMenuItem(new ItemBuilder(Material.WEB)
                        .displayName("§eNada para fazer.")
                        .lore("§7Que bom! Você não tem nenhuma missão para fazer.")
                        .build(), new EmptyClickAction()), 22,
                user -> user.getCurrentQuests().isEmpty());

        ItemAssertor bottomItems = new ItemImmutableAssertor(new MutableMenuItem(user -> {
            int questsToComplete = user.getCurrentQuests().size();
            int completedQuests = user.getCompletedQuests().size();
            return new SkullBuilder()
                    .owner(user.getName())
                    .displayName("§eEstatísticas de " + user.getName())
                    .lore("")
                    .lore("   §f" + questsToComplete + " §emiss" + (questsToComplete == 1 ? "ão" : "ões") + " por completar.")
                    .lore("   §f" + completedQuests + " §emiss" + (completedQuests == 1 ? "ão" : "ões") + " completada" + (completedQuests == 1 ? "" : "s") + ".")
                    .lore("")
                    .build();
        }, new EmptyClickAction()), 49);

        register(mainMenu = new Menu.Builder("Missões:", 54)
                .add(middleQuests)
                .add(bottomItems)
                .add(emptyQuests)
                .add(increasePage)
                .add(decreasePage)
                .build());
    }

    private static void register(Menu menu) {
        MENU_MAP.put(menu.getTitle(), menu);
    }

    public static Menu get(String menuName) {
        return MENU_MAP.get(menuName);
    }

    static String paraphrase(QuestObjective questObjective, int progress) {
        return questObjective.getDisplayQuestProgress().replace("<amount>", Integer.toString(questObjective.getAmount())) +
                "§7: §f" +
                Math.min(progress, questObjective.getAmount()) +
                "§7/§f" +
                questObjective.getAmount();
    }

    static List<String> arrangeLore(List<String> input, int spaceNumbers) {
        List<String> lore = new ArrayList<>();
        for (String dividedLore : input) {

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i != spaceNumbers; i++)
                stringBuilder.append(" ");
            stringBuilder.append("§7").append(dividedLore);

            lore.add(stringBuilder.toString());
        }
        return lore;
    }

    public static Menu getMainMenu() {
        return mainMenu;
    }
}

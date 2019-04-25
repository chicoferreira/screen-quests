package com.redescreen.quests.user;

import com.redescreen.quests.Defaults;
import com.redescreen.quests.manager.BasicManager;
import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.quest.QuestManager;
import com.redescreen.quests.quest.objective.QuestObjective;
import com.redescreen.quests.quest.objective.trigger.QuestObjectiveTrigger;
import com.redescreen.quests.user.dao.UserDao;
import com.redescreen.quests.user.quest.QuestProgress;
import com.redescreen.quests.util.BlockMaterial;

public class UserManager extends BasicManager<User> {

    private static UserManager instance;

    private UserDao userDao;

    private UserManager() {
        userDao = new UserDao();
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    @Override
    public User get(String name) {
        User user = super.get(name);

        if (user == null) {
            User foundedUser = userDao.find(name);
            if (foundedUser != null) {
                user = this.put(foundedUser);
            }
        }

        if (user == null) {
            user = this.put(new User(name));
        }
        return user;
    }

    public User put(User user) {
        super.put(user.getName(), user);
        return user;
    }

    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(String name) {
        throw new UnsupportedOperationException();
    }

    public void trigger(User user, QuestObjectiveTrigger questObjectiveTrigger, BlockMaterial requirementMaterial) {
        for (String currentQuestName : user.getCurrentQuests()) {
            Quest quest = QuestManager.getInstance().get(currentQuestName);
            if (quest != null) {
                for (QuestObjective questObjective : quest.getQuestObjectives()) {
                    if (questObjective.getQuestObjectiveTrigger() == questObjectiveTrigger && questObjective.getMaterialRequirement().equals(requirementMaterial)) {
                        QuestProgress questProgress = user.getQuestProgress(quest);
                        questProgress.progress(questObjective);
                        if (questProgress.isFinished() && questObjective.getAmount() == questProgress.get(questObjective)) {
                            user.sendMessage(Defaults.QUEST_OBJECTIVES_FINISH.replace("{quest}", quest.getTitle()));
                        }
                    }
                }
            }
        }
    }

    public UserDao getUserDao() {
        return userDao;
    }
}

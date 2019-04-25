package com.redescreen.quests.user.dao;

import com.redescreen.quests.database.DataSource;
import com.redescreen.quests.database.DatabaseManager;
import com.redescreen.quests.database.SQLQueries;
import com.redescreen.quests.database.exception.DatabaseConnectionException;
import com.redescreen.quests.database.exception.DatabaseLoadUserException;
import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.quest.QuestManager;
import com.redescreen.quests.quest.objective.QuestObjective;
import com.redescreen.quests.user.User;
import com.redescreen.quests.user.quest.QuestProgress;
import com.redescreen.quests.util.database.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDao {

    private DatabaseManager database;

    public UserDao() {
        this.database = DatabaseManager.getInstance();
    }

    public User find(String userName) {
        try (Connection connection = DataSource.getConnection();

             PreparedStatement preparedStatement =
                     new PreparedStatementBuilder(SQLQueries.SQL_LOAD_QUESTS_QUERY)
                             .parameter(1, userName)
                             .build(connection);

             ResultSet resultSet = preparedStatement.executeQuery()) {

            List<String> completedQuests = this.getFinishedQuests(userName, connection);
            Map<Quest, QuestProgress> questProgresses = new HashMap();
            while (resultSet.next()) {
                String questName = resultSet.getString("quest");
                String objective = resultSet.getString("objective");
                int progress = resultSet.getInt("progress");

                Quest quest = QuestManager.getInstance().get(questName);

                if (quest != null) {
                    if (!questProgresses.containsKey(quest)) {
                        questProgresses.put(quest, new QuestProgress(quest, new HashMap<>()));
                    }

                    QuestProgress questProgress = questProgresses.get(quest);

                    QuestObjective questObjective = quest.getQuestObjective(objective);
                    if (questObjective != null) {
                        questProgress.getProgressMap().put(questObjective, progress);
                    }
                }
            }
            return new User(userName, completedQuests, questProgresses);
        } catch (SQLException e) {
            throw new DatabaseLoadUserException(userName, e);
        }
    }

    private List<String> getFinishedQuests(String user, Connection connection) throws SQLException {
        List<String> finishedQuests = new ArrayList<>();
        try (PreparedStatement preparedStatement = new PreparedStatementBuilder(SQLQueries.SQL_LOAD_FINISHED_QUESTS_QUERY)
                .parameter(1, user)
                .build(connection);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                finishedQuests.add(resultSet.getString("finishedQuest"));
            }
        }
        return finishedQuests;
    }

    public void save(User user) {
        try (Connection connection = DataSource.getConnection()) {

            database.execute(connection, () -> new PreparedStatementBuilder(SQLQueries.SQL_SAVE_USERS).parameter(1, user.getName()));

            for (String questName : user.getCurrentQuests()) {
                Quest quest = QuestManager.getInstance().get(questName);
                if (quest != null) {
                    for (QuestObjective questObjective : quest.getQuestObjectives()) {
                        String questObjectiveName = questObjective.getObjectiveCodeName();
                        int progress = user.getQuestProgress(quest).get(questObjective);

                        database.execute(connection, () -> new PreparedStatementBuilder(SQLQueries.SQL_SAVE_PROGRESSES)
                                .parameter(1, user.getName())
                                .parameter(2, quest.getTitle())
                                .parameter(3, questObjectiveName)
                                .parameter(4, progress));
                    }
                }
            }

            for (String completedQuest : user.getCompletedQuests()) {
                database.execute(connection, () -> new PreparedStatementBuilder(SQLQueries.SQL_SAVE_FINISHED)
                        .parameter(1, user.getName())
                        .parameter(2, completedQuest));
            }
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }
}

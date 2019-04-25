package com.redescreen.quests.database;

import com.redescreen.quests.database.exception.DatabaseConnectionException;
import com.redescreen.quests.database.exception.DatabaseInitException;
import com.redescreen.quests.database.exception.DatabaseMapQuestsException;
import com.redescreen.quests.database.provider.StatementProvider;
import com.redescreen.quests.quest.Quest;
import com.redescreen.quests.quest.QuestManager;
import com.redescreen.quests.quest.objective.QuestObjective;
import com.redescreen.quests.util.database.PreparedStatementBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {

    private static DatabaseManager instance;

    private DatabaseManager() {
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public void execute(StatementProvider statementProvider) {
        execute(DataSource.getConnection(), statementProvider);
    }

    public void execute(Connection connection, StatementProvider statementProvider) {
        try (
                PreparedStatement preparedStatement = statementProvider.get()
                        .build(connection)
        ) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DatabaseConnectionException(e);
        }
    }

    public void initDatabase() {
        try (Connection connection = DataSource.getConnection()) {
            String sqlInitDatabase = SQLQueries.SQL_INIT_DATABASE;
            for (String queries : sqlInitDatabase.split(";")) {
                execute(connection, () -> new PreparedStatementBuilder(queries + ";"));
            }
        } catch (SQLException e) {
            throw new DatabaseInitException(e);
        }
    }

    public void mapQuests() {
        try (Connection connection = DataSource.getConnection()) {
            for (Quest quest : QuestManager.getInstance().getAll()) {

                execute(connection, () -> new PreparedStatementBuilder(SQLQueries.SQL_SAVE_QUESTS).parameter(1, quest.getTitle()));

                for (QuestObjective questObjective : quest.getQuestObjectives()) {
                    execute(connection, () -> new PreparedStatementBuilder(SQLQueries.SQL_SAVE_OBJECTIVES)
                            .parameter(1, questObjective.getObjectiveCodeName())
                    );
                }
            }
        } catch (SQLException e) {
            throw new DatabaseMapQuestsException(e);
        }
    }

}

package com.redescreen.quests.database;

import com.redescreen.quests.Quests;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class SQLQueries {

    public static final String SQL_INIT_DATABASE;
    public static final String SQL_LOAD_FINISHED_QUESTS_QUERY;
    public static final String SQL_LOAD_QUESTS_QUERY;
    public static final String SQL_SAVE_FINISHED;
    public static final String SQL_SAVE_OBJECTIVES;
    public static final String SQL_SAVE_PROGRESSES;
    public static final String SQL_SAVE_QUESTS;
    public static final String SQL_SAVE_USERS;

    static {
        SQL_INIT_DATABASE = load("queries/init.sql");
        SQL_LOAD_FINISHED_QUESTS_QUERY = load("queries/load/load_finished_quests.sql");
        SQL_LOAD_QUESTS_QUERY = load("queries/load/load_quests.sql");
        SQL_SAVE_FINISHED = load("queries/save/save_finished.sql");
        SQL_SAVE_OBJECTIVES = load("queries/save/save_objectives.sql");
        SQL_SAVE_PROGRESSES = load("queries/save/save_progresses.sql");
        SQL_SAVE_QUESTS = load("queries/save/save_quests.sql");
        SQL_SAVE_USERS = load("queries/save/save_users.sql");
    }

    private static String load(String resource) {
        InputStream inputStream = Quests.getInstance().getResource(resource);
        String result = new BufferedReader(new InputStreamReader(inputStream))
                .lines()
                .collect(Collectors.joining(" "));

        return StringUtils.normalizeSpace(result);
    }

}

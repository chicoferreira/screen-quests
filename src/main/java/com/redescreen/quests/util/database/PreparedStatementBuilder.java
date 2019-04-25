package com.redescreen.quests.util.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class PreparedStatementBuilder {

    private String query;
    private Map<Integer, Object> parameters;

    public PreparedStatementBuilder(String query) {
        this.query = query;
        this.parameters = new HashMap<>();
    }

    public PreparedStatementBuilder parameter(int index, Object value) {
        this.parameters.put(index, value);
        return this;
    }

    public PreparedStatement build(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.query);
        for (Map.Entry<Integer, Object> entry : parameters.entrySet()) {
            preparedStatement.setObject(entry.getKey(), entry.getValue());
        }
        return preparedStatement;
    }
}

package com.redescreen.quests.database.exception;

public class DatabaseConnectionException extends DatabaseException {

    public DatabaseConnectionException(Throwable throwable) {
        super(throwable, "Não foi possível conectar à database.");
    }

}

package com.redescreen.quests.database.exception;

public class DatabaseInitException extends DatabaseException {
    public DatabaseInitException(Throwable throwable) {
        super(throwable, "Não foi possível iniciar a database.");
    }
}

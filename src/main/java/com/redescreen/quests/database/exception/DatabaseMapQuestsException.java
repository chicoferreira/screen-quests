package com.redescreen.quests.database.exception;

public class DatabaseMapQuestsException extends DatabaseException {
    public DatabaseMapQuestsException(Throwable throwable) {
        super(throwable, "Não foi possível mappear as missões para database.");
    }
}

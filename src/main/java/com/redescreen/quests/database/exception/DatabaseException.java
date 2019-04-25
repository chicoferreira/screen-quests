package com.redescreen.quests.database.exception;

import com.redescreen.quests.Quests;

import java.util.logging.Level;

public abstract class DatabaseException extends RuntimeException {

    public DatabaseException(String message) {
        Quests.getInstance().getLogger().log(Level.SEVERE, this, () -> message);
    }

    public DatabaseException(Throwable throwable, String message) {
        Quests.getInstance().getLogger().log(Level.SEVERE, throwable, () -> message);
    }
}

package com.redescreen.quests.database.exception;

public class DatabaseLoadUserException extends DatabaseException {


    public DatabaseLoadUserException(String user, Throwable throwable) {
        super(throwable, "Não foi possível carregar as informações do jogador com o nome '" + user + "'.");
    }
}

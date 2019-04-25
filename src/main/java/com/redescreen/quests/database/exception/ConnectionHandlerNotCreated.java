package com.redescreen.quests.database.exception;

public class ConnectionHandlerNotCreated extends DatabaseException {

    public ConnectionHandlerNotCreated() {
        super("Foi tentado acessar o ConnectionHandler antes dele ser criado.");
    }

}

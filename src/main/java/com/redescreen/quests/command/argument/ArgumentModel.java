package com.redescreen.quests.command.argument;

public class ArgumentModel {

    private String name;
    private boolean optional;

    public ArgumentModel(String name, boolean optional) {
        this.name = name;
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public boolean isOptional() {
        return optional;
    }

    @Override
    public String toString() {
        return (isOptional() ? "(" : "<") + this.name + (isOptional() ? ")" : ">");
    }
}

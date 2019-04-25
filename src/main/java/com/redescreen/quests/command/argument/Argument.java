package com.redescreen.quests.command.argument;

import java.util.Optional;

public class Argument {

    private ArgumentModel model;
    private int index;
    private String content;

    public Argument(ArgumentModel model, int index, String content) {
        this.model = model;
        this.index = index;
        this.content = content;
    }

    public ArgumentModel getModel() {
        return model;
    }

    public int getIndex() {
        return index;
    }

    public Optional<String> getContent() {
        return Optional.ofNullable(content);
    }
}

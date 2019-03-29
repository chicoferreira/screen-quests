package com.redescreen.quests.command.argument;

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

    public String getContent() {
        return content;
    }
}

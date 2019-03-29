package com.redescreen.quests.command;

import com.redescreen.quests.command.argument.ArgumentModel;
import com.redescreen.quests.user.User;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public abstract class QuestCommand {

    private String name;
    private String permission;
    private String description;
    private ArgumentModel[] argumentModels;
    private String[] aliases;

    public QuestCommand(String name) {
        this.name = name;
    }

    public abstract void execute(User user);

    public final String getName() {
        return name;
    }

    public final Optional<String> getPermission() {
        return Optional.ofNullable(permission);
    }

    public final Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    public final Optional<ArgumentModel[]> getArgumentModels() {
        return Optional.ofNullable(argumentModels);
    }

    public final String[] getAliases() {
        return aliases;
    }

    public final void permission(String permission) {
        this.permission = permission;
    }

    public final void description(String description) {
        this.description = description;
    }

    public final void argumentModels(ArgumentModel... argumentModels) {
        this.argumentModels = argumentModels;
    }

    public final void aliases(String... aliases) {
        this.aliases = aliases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestCommand that = (QuestCommand) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(permission, that.permission) &&
                Objects.equals(description, that.description) &&
                Arrays.equals(argumentModels, that.argumentModels) &&
                Arrays.equals(aliases, that.aliases);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, permission, description);
        result = 31 * result + Arrays.hashCode(argumentModels);
        result = 31 * result + Arrays.hashCode(aliases);
        return result;
    }
}

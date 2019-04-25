package com.redescreen.quests.database.provider;

import com.redescreen.quests.util.database.PreparedStatementBuilder;

import java.util.function.Supplier;

public interface StatementProvider extends Supplier<PreparedStatementBuilder> {

}

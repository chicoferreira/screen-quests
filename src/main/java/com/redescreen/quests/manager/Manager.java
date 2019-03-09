package com.redescreen.quests.manager;

import java.util.List;

public interface Manager<T> {

    T get(String name);

    void put(String key, T t);

    void delete(String name);

    List<T> getAll();

}

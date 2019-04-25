package com.redescreen.quests.manager;

import java.util.List;
import java.util.Map;

public interface Manager<T> {

    T get(String name);

    T put(String key, T t);

    void delete(String name);

    Map<String, T> getMap();

    List<T> getAll();

}

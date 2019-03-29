package com.redescreen.quests.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicManager<T> implements Manager<T> {

    private Map<String, T> map;

    public BasicManager() {
        this.map = new HashMap<>();
    }

    @Override
    public T get(String name) {
        return map.get(name);
    }

    @Override
    public T put(String key, T value) {
        return this.map.putIfAbsent(key, value);
    }

    @Override
    public void delete(String name) {
        this.map.remove(name);
    }

    @Override
    public List<T> getAll() {
        return new ArrayList<>(map.values());
    }

}

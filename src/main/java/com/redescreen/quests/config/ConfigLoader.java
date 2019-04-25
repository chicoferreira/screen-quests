package com.redescreen.quests.config;


import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

public interface ConfigLoader<T> {

    List<T> load(FileConfiguration config);

}

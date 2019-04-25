package com.redescreen.quests.user.listener;

import com.redescreen.quests.user.User;
import com.redescreen.quests.user.UserManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class UserListener implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent playerJoinEvent) {
        UserManager.getInstance().get(playerJoinEvent.getPlayer().getName()).refreshBukkit();
    }

    @EventHandler
    public void quit(PlayerQuitEvent playerQuitEvent) {
        UserManager instance = UserManager.getInstance();
        Player player = playerQuitEvent.getPlayer();
        User user = instance.get(player.getName());
        instance.save(user);
    }

}

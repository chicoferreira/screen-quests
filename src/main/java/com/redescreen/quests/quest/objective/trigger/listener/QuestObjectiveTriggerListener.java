package com.redescreen.quests.quest.objective.trigger.listener;

import com.redescreen.quests.quest.objective.trigger.QuestObjectiveTrigger;
import com.redescreen.quests.user.User;
import com.redescreen.quests.user.UserManager;
import com.redescreen.quests.util.BlockMaterial;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerFishEvent;

public class QuestObjectiveTriggerListener implements Listener {

    @EventHandler
    public void breakBlock(BlockBreakEvent blockBreakEvent) {
        Player player = blockBreakEvent.getPlayer();
        User user = UserManager.getInstance().get(player.getName());
        UserManager.getInstance().trigger(user, QuestObjectiveTrigger.BREAK_BLOCK, new BlockMaterial(blockBreakEvent.getBlock()));
    }

    @EventHandler
    public void placeBlock(BlockPlaceEvent blockPlaceEvent) {
        Player player = blockPlaceEvent.getPlayer();
        User user = UserManager.getInstance().get(player.getName());
        UserManager.getInstance().trigger(user, QuestObjectiveTrigger.PLACE_BLOCK, new BlockMaterial(blockPlaceEvent.getBlock()));
    }

    @EventHandler
    public void fish(PlayerFishEvent fishEvent) {
        Player player = fishEvent.getPlayer();
        User user = UserManager.getInstance().get(player.getName());
        if (fishEvent.getState() == PlayerFishEvent.State.CAUGHT_FISH) {
            UserManager.getInstance().trigger(user, QuestObjectiveTrigger.FISH, null);
        }
    }

}

package com.zennyel.listeners;

import com.zennyel.SAO;
import com.zennyel.player.SkillType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerLevelChangeEvent;

public class PlayerLevelUp implements Listener {
    @EventHandler
    public void onLevelUp(PlayerLevelChangeEvent e){
        Player p = e.getPlayer();
        com.zennyel.player.Player player = SAO.getPlugin(SAO.class).getPlayer(p.getUniqueId());
        if(p.getLevel() > e.getNewLevel()){
            player.levelUp();
        }
    }
    @EventHandler
    public void onLevelLost(PlayerDeathEvent e){
        Player p = e.getPlayer();
        e.getKeepLevel();
    }
}

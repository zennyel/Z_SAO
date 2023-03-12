package com.zennyel.listeners;

import com.zennyel.SAO;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class InventoryClick implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        com.zennyel.player.Player p = SAO.getPlugin(SAO.class).getPlayer(player.getUniqueId());

        if (!event.getView().getTitle().equalsIgnoreCase("§6Menu de Status")) {
            return;
        }

        if (event.getCurrentItem() == null) {
            return;
        }

        if (p.getPoints() <= 0) {
            player.sendMessage("§4Pontos Insuficientes!");
            event.setCancelled(true);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 1, 1);
            return;
        }

        Material itemType = event.getCurrentItem().getType();
        switch (itemType) {
            case DIAMOND_SWORD:
                p.upStrength();
                player.sendMessage("§6§lParabéns, você melhorou sua força!");
                break;
            case SHIELD:
                p.upDexterity();
                player.sendMessage("§6§lParabéns, você melhorou sua destreza!");
                break;
            case LEATHER_BOOTS:
                p.upAgility();
                player.sendMessage("§6§lParabéns, você melhorou sua agilidade!");
                break;
            case GOLDEN_APPLE:
                p.upHealth();
                player.sendMessage("§6§lParabéns, você melhorou sua vida!");
                break;
        }

        p.removePoints(1);
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 10);
        event.setCancelled(true);
    }
}
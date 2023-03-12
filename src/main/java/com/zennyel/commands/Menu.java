package com.zennyel.commands;

import com.zennyel.SAO;
import com.zennyel.menu.StatsMenu;
import com.zennyel.player.Character;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Menu implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser executado por um jogador.");
            return true;
        }

        Player player = (Player) sender;
        Character p = SAO.getPlugin(SAO.class).getPlayer(player.getUniqueId());
        Inventory menu = Bukkit.createInventory(null, 27, "§6Menu de Status");
        StatsMenu stats = new StatsMenu(menu, p);
        stats.updateItems(p);
        player.openInventory(menu);
        return true;
    }
}
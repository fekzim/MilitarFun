package me.fek.militaryfun;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItem implements Listener {
//Quando mexer sobre slot de itens no inventario, checar se ele é nulo ou ar

    @EventHandler
    public void onPlayerCraft(CraftItemEvent event){
        for (HumanEntity entity : event.getViewers()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                ItemStack[] item = event.getInventory().getMatrix();

                for (int i = 0; i < 9; i++){
                    //if (item == null || item.getType().equals(Material.AIR)
                    if(item[i] == null || item[i].getType().equals(Material.AIR)){
                        continue;
                    }
                    if(item[i].hasItemMeta()){
                        if(item[i].getItemMeta().getDisplayName().equals("§7Ferro Fundido")){
                            event.setCancelled(true);
                        }
                    }
                }
            }
        }

    }
}


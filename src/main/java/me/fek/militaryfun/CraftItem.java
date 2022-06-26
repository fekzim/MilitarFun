package me.fek.militaryfun;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItem implements Listener {
//Quando mexer sobre slot de itens no inventario, checar se ele é nulo ou ar

    /*ITENS
    * §7Ferro Fundido
    * §7Tugstenio
    * §8§lPicareta de Ferro fundido
    * §8Munição
    *
    * */

    @EventHandler
    public void onPlayerSmelt(FurnaceSmeltEvent event){
        if(event.getSource().getType().equals(Material.IRON_INGOT)){
            if(event.getSource().getItemMeta().getDisplayName().equals("Ferro Especial")){
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void onPlayerCraft(CraftItemEvent event){
        for (HumanEntity entity : event.getViewers()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                ItemStack[] item = event.getInventory().getMatrix();

                if(event.getRecipe().getResult().getItemMeta().getDisplayName().equals("§8§lPicareta de Ferro fundido")
                || event.getRecipe().getResult().getItemMeta().getDisplayName().contains("§8§lPistola " + "§f[0]")){
                    break;
                }

                for (int i = 0; i < 9; i++){
                    //if (item == null || item.getType().equals(Material.AIR)
                    if(item[i] == null || item[i].getType().equals(Material.AIR)){
                        continue;
                    }
                    if(item[i].hasItemMeta()){
                        switch (item[i].getItemMeta().getDisplayName()) {
                            case "§7Tugstenio":
                            case "§8Munição":
                            case "§7Ferro Fundido" :
                                event.setCancelled(true);
                                break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
    }
}


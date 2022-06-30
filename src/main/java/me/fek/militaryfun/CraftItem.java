package me.fek.militaryfun;

import org.bukkit.ChatColor;
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

import java.util.List;
import java.util.Objects;

public class CraftItem implements Listener {
//Quando mexer sobre slot de itens no inventario, checar se ele é nulo ou ar

    /*ITENS
    * §7Ferro Fundido
    * §7Tugstenio
    * §8§lPicareta de Ferro fundido
    * §8Munição
    *
    * */

    //Checar quando um item for derretido
    @EventHandler
    public void onPlayerSmelt(FurnaceSmeltEvent event){
        if(event.getSource().getType().equals(Material.IRON_INGOT)){
            if(event.getSource().getItemMeta().getDisplayName().equals("Ferro Especial")){
                event.setCancelled(true);
            }
        }
    }

    //Checar quando um player craftar algo (clicar no resultado)
    @EventHandler
    public void onPlayerCraft(CraftItemEvent event){
        for (HumanEntity entity : event.getViewers()) {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                ItemStack[] item = event.getInventory().getMatrix();

                List<String> itensList = MilitaryFun.getPlugin().getConfig().getStringList("itens.name");
                List<String> componentList = MilitaryFun.getPlugin().getConfig().getStringList("itens.components");
                String resultName = Objects.requireNonNull(event.getRecipe().getResult().getItemMeta()).getDisplayName();

                //Checa se o item que vai fabricado é permitido na lista
                if(itensList.contains(resultName.replaceAll("§","&"))){
                    break;
                }

                for (int i = 0; i < 9; i++){
                    //if (item == null || item.getType().equals(Material.AIR)
                    if(item[i] == null || item[i].getType().equals(Material.AIR)){
                        continue;
                    }
                    if(item[i].hasItemMeta()){
                        //checa se algum item é do plugin e nega seu evento de craft
                        if(componentList.contains(item[i].getItemMeta().getDisplayName().replaceAll("§", "&"))){
                            event.setCancelled(true);
                            break;
                        }
                    }
                }
            }
        }
    }
}


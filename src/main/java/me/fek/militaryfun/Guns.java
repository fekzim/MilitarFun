package me.fek.militaryfun;

import com.sun.tools.javac.util.Names;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class Guns implements Listener {
//§8Munição
    //Logica da arma
    /*
    * Item arma possui metadata munição
    * Checar quanta munição tem no item
    * Exibir um texto mostrando a munição atual
    * Se apertar tal botão de tal forma, recarregar
    * Diminuir quantidade de munição faltante
    * Ao atirar diminuir uma munição
    * */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Action action = event.getAction();
        Player player = event.getPlayer();

        if(action == Action.RIGHT_CLICK_AIR && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName()
                .contains("§8§lPistola " + "§f[0]") && player.isSneaking()){
            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();
            if(data.has(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)){

            }else{
                data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, 0);
                data.set(new NamespacedKey(MilitaryFun.getPlugin(), "max-munition"), PersistentDataType.INTEGER, 8);
                item.setItemMeta(meta);
            }
            int size = player.getInventory().getSize();
            int munition = data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER);
            int maxMunition = data.get(new NamespacedKey(MilitaryFun.getPlugin(),"max-munition"), PersistentDataType.INTEGER);
            int finalMunition;
            if(munition < maxMunition){
                for(int slot = 0; slot < size; slot++){
                    ItemStack is = player.getInventory().getItem(slot);
                    if(is == null) continue;
                    if(is.getType() == Material.IRON_NUGGET){
                        if(is.getItemMeta().getDisplayName().equals("§8Munição")){
                            int completeMunition = maxMunition - munition;
                            int newAmount =  is.getAmount() - completeMunition;
                            if(newAmount > 0){
                                is.setAmount(newAmount);
                                finalMunition = 8;
                                data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, finalMunition);
                                item.setItemMeta(meta);
                                break;
                            }else{
                                player.getInventory().clear(slot);
                                finalMunition = newAmount + maxMunition;
                                data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, finalMunition);
                                item.setItemMeta(meta);
                            }
                            if(newAmount == 0) break;
                        }
                    }
                }
            }
            meta.setDisplayName("§8§lPistola " + ChatColor.WHITE + "["+data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)+"]");
            item.setItemMeta(meta);
        }
    }
}

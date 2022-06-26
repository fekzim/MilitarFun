package me.fek.militaryfun;

import com.sun.tools.javac.util.Names;
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
int soma = 0;
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Action action = event.getAction();
        Player player = event.getPlayer();

        if(action == Action.RIGHT_CLICK_AIR && player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("Arma")){


            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta meta = item.getItemMeta();

            PersistentDataContainer data = meta.getPersistentDataContainer();
            if(data.has(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)){
                soma++;
                player.sendMessage("municao atual" + soma);
                player.sendMessage("Message: "+ data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER));
                data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, soma);
            }else{
                data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, 0);
                data.set(new NamespacedKey(MilitaryFun.getPlugin(), "max-munition"), PersistentDataType.INTEGER, 8);
                item.setItemMeta(meta);
                player.sendMessage("Mensagem adicionada a meta data do item");
            }
            /*
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
                                break;
                            }else{
                                player.getInventory().clear(slot);
                                finalMunition = newAmount + maxMunition;

                            }
                            if(newAmount == 0) break;
                        }
                    }
                    meta.setDisplayName("Armas " + "["+munition+"]");
                    item.setItemMeta(meta);
                }
            }

    */

            /*for(int slot = 0; slot < size; slot++){
                ItemStack is = player.getInventory().getItem(slot);
                if(is == null) continue;
                if(is.getType() == Material.IRON_NUGGET && is.getItemMeta().getDisplayName().equals("§8Munição")){
                    int newAmount = is.getAmount() - 8;
                    municao+=8;
                    if(newAmount > 0){
                        is.setAmount(newAmount);
                    }else{
                        player.getInventory().clear(slot);
                    }
                    if(newAmount == 0) break;
                }
            }*/
        }

                    /*
                    ItemStack item = p.getInventory().getItemInMainHand();
                    ItemMeta meta = item.getItemMeta();

                    PersistentDataContainer data = meta.getPersistentDataContainer();

                    if(data.has(new NamespacedKey(MilitaryFun.getPlugin(),"message"), PersistentDataType.STRING)){
                        p.sendMessage("Esse item ja tem uma message");
                        p.sendMessage("Message: "+ data.get(new NamespacedKey(MilitaryFun.getPlugin(),"message"), PersistentDataType.STRING));
                    }else{
                        data.set(new NamespacedKey(MilitaryFun.getPlugin(), "message"), PersistentDataType.STRING,"mensagem zika");

                        item.setItemMeta(meta);

                        p.sendMessage("Mensagem adicionada a meta data do item");
                    }
                    */



    }
}

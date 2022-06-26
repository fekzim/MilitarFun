package me.fek.militaryfun;
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
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        Action action = event.getAction();
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        String mainHandName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();

        if(action == Action.RIGHT_CLICK_AIR && mainHandName.contains("§8§lPistola ") && !(player.isSneaking())){
            if(data.has(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)){
                if(data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER) > 0 && !(player.isSneaking())){
                    int municaoFinal =  data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER);
                    municaoFinal -= 1;
                    data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, municaoFinal);
                    item.setItemMeta(meta);
                    meta.setDisplayName("§8§lPistola " + ChatColor.WHITE + "["+data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)+"]");
                    item.setItemMeta(meta);
                }
            }else{
                player.sendMessage(ChatColor.RED+"Esse item não possui o Item meta necessario");
            }
        }

        if(action == Action.RIGHT_CLICK_AIR && mainHandName.contains("§8§lPistola ") && player.isSneaking()){
            if(data.has(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)){
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
                                int leftMunition = munition - maxMunition;
                                int newAmount = leftMunition + is.getAmount();
                                if(newAmount <= 0){
                                    finalMunition = is.getAmount() + munition;
                                    player.getInventory().clear(slot);
                                    data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, finalMunition);
                                    item.setItemMeta(meta);
                                    break;
                                }
                                if(newAmount > 0){
                                    finalMunition =  (is.getAmount() - newAmount) + munition;
                                    is.setAmount(newAmount);
                                    data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, finalMunition);
                                    item.setItemMeta(meta);
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            meta.setDisplayName("§8§lPistola " + ChatColor.WHITE + "["+data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)+"]");
            item.setItemMeta(meta);
        }
    }
}
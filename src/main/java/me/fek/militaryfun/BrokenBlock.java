package me.fek.militaryfun;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

/*
* Essa classe se trata sobre eventos referentes a quebra de blocos
* */
public class BrokenBlock  implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        //variaveis
        Player player = event.getPlayer(); //instancia do player do evento
        Block blockBroken = event.getBlock(); //instancia do bloco quebrado no evento
        Random random = new Random(); //Numero aleatorio
        ItemStack item = player.getInventory().getItemInMainHand();

        if(item.getType() == Material.IRON_PICKAXE && item.getItemMeta().getDisplayName().equals("§8§lPicareta de Ferro fundido")){
            if(blockBroken.getType() == Material.IRON_ORE || blockBroken.getType() == Material.DEEPSLATE_IRON_ORE){
                int vezes = 0;
                if(blockBroken.getType() == Material.IRON_ORE){
                    vezes = 10 + random.nextInt(10);
                }
                if(blockBroken.getType() == Material.DEEPSLATE_IRON_ORE){
                    vezes = 30 + random.nextInt(10);
                }
                for(int i = 0; i < vezes; i++){
                    blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), ItemManager.tugstenio);
                }
            }
        }
    }
}

package me.fek.militaryfun;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.Random;

public class BrokenBlock  implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        Block blockBroken = event.getBlock();
        Random random = new Random();

        if(player.getInventory().getItemInMainHand().getType() == Material.IRON_PICKAXE && player.getInventory().getItemInMainHand().getItemMeta()
                .getDisplayName().equals("§8§lPicareta de Ferro fundido")){
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

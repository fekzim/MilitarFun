package me.fek.militaryfun;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ItemManager extends JavaPlugin {

    public static void Init(){
        System.out.println("==========================");
        System.out.println("ItemManager Carregado com sucesso");
        createFerroFundido();
    }

    //Itens
    public static ItemStack ferroFundido;


    //Criação dos itens

    private static void createFerroFundido(){
        ItemStack item = new ItemStack(Material.BRICK, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Ferro Fundido");
        List<String> lore = new ArrayList<>();
        lore.add("§6 Ferro fundido usado para criar armas");
        meta.setLore(lore);
        item.setItemMeta(meta);
        ferroFundido = item;

        FurnaceRecipe recipe = new FurnaceRecipe(ferroFundido, Material.IRON_INGOT);
        recipe.setExperience(100);
        Bukkit.getServer().addRecipe(recipe);
    }
}

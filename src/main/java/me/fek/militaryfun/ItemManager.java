package me.fek.militaryfun;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ItemManager extends JavaPlugin {

    public static void Init(){
        System.out.println("==========================");
        System.out.println("ItemManager Carregado com sucesso");
        createFerroFundido();
        createTugstenio();
        createPicaretaFundida();
    }

    //Itens
    /*
    * Tugstenio
    - ferro fundido
    - Picareta fundida
    - Pistola
    - Munição
    * */
    public static ItemStack ferroFundido;
    public static ItemStack tugstenio;
    public static ItemStack picaretaFundida;
    public static ItemStack munition;



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

        //Furnace Recipe
        FurnaceRecipe recipe = new FurnaceRecipe(ferroFundido, Material.IRON_INGOT);
        recipe.setExperience(100);
        recipe.setCookingTime(100);
        Bukkit.getServer().addRecipe(recipe);
    }

    private static void createTugstenio(){
        ItemStack item = new ItemStack(Material.IRON_NUGGET, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§7Tugstenio");
        List<String> lore = new ArrayList<>();
        lore.add("§5 use para criar armas");
        meta.setLore(lore);
        item.setItemMeta(meta);
        tugstenio = item;
    }

    private static void createPicaretaFundida() {
        ItemStack item = new ItemStack(Material.IRON_PICKAXE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8§lPicareta de Ferro fundido");
        List<String> lore = new ArrayList<>();
        lore.add("§6 Use para conseguir tugstenio");
        meta.setLore(lore);
        item.setItemMeta(meta);
        picaretaFundida = item;

        //Shaped Recipe
        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("picareta_fundida"),item);
        shapedRecipe.shape( "TTT"," I ", " I ");

        shapedRecipe.setIngredient('I', Material.IRON_INGOT);
        shapedRecipe.setIngredient('T', new RecipeChoice.ExactChoice(ferroFundido));
        Bukkit.getServer().addRecipe(shapedRecipe);
    }

    private static void createMunition(){
        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8Munição");
        List<String> lore = new ArrayList<>();
        lore.add("§3 Munição para armas");
        meta.setLore(lore);
        item.setItemMeta(meta);
        munition = item;

        //Shapeless Recipe

    }
}

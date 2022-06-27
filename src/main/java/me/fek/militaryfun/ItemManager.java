package me.fek.militaryfun;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class ItemManager extends JavaPlugin {

    //Arrumar um arquivo de configuração que permita habilitar e desabilitar certos itens
    public static void Init(){
        System.out.println("ItemManager Carregado com sucesso");
        createFerroFundido();
        createTugstenio();
        createPicaretaFundida();
        createMunition();
        createPistola();
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
    public static ItemStack pistola;



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

        //Item dropado no BrokenBlock.java
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
        ItemStack item = new ItemStack(Material.IRON_NUGGET, 8);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8Munição");
        List<String> lore = new ArrayList<>();
        lore.add("§3 Munição para armas");
        meta.setLore(lore);
        item.setItemMeta(meta);
        munition = item;

        //Shapeless Recipe (independente da ordem)
        ShapelessRecipe srecipe = new ShapelessRecipe(NamespacedKey.minecraft("munition"), item);
        srecipe.addIngredient(6, Material.GUNPOWDER);
        srecipe.addIngredient(3, Material.IRON_INGOT);
        Bukkit.getServer().addRecipe(srecipe);
    }

    private static void createPistola(){
        ItemStack item = new ItemStack(Material.MUSIC_DISC_STRAD);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§8§lPistola " + "§f[0]");
        PersistentDataContainer data = meta.getPersistentDataContainer();
        //Criação do metadata de maxMunition, munition na hora da criação da arma
        data.set(new NamespacedKey(MilitaryFun.getPlugin(), "munition"), PersistentDataType.INTEGER, 0);
        data.set(new NamespacedKey(MilitaryFun.getPlugin(), "max-munition"), PersistentDataType.INTEGER, 8);
        item.setItemMeta(meta);
        pistola = item;


        //Craft do item
        ShapedRecipe shapedRecipe = new ShapedRecipe(NamespacedKey.minecraft("pistola"),item);
        shapedRecipe.shape( "TTT","II ", "I  ");

        shapedRecipe.setIngredient('I', Material.IRON_INGOT);
        shapedRecipe.setIngredient('T', new RecipeChoice.ExactChoice(ferroFundido));
        Bukkit.getServer().addRecipe(shapedRecipe);
    }
}

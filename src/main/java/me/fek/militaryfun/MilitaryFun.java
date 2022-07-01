package me.fek.militaryfun;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class MilitaryFun extends JavaPlugin {
//CLASSE MAIN

    private static MilitaryFun plugin; //instancia do plugin

    //Configuration Setup


//OnEnable é referente a toda vez que o plugin é iniciado no start do server ou reload
    @Override
    public void onEnable() {
        plugin = this;
        //Config
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveDefaultConfig();
        System.out.println("==========================================================================");
        System.out.println(" #     #                                         ####### #     # #     # \n" +
                " ##   ## # #      # #####   ##   #####  #   #    #       #     # ##    # \n" +
                " # # # # # #      #   #    #  #  #    #  # #     #       #     # # #   # \n" +
                " #  #  # # #      #   #   #    # #    #   #      #####   #     # #  #  # \n" +
                " #     # # #      #   #   ###### #####    #      #       #     # #   # # \n" +
                " #     # # #      #   #   #    # #   #    #      #       #     # #    ## \n" +
                " #     # # ###### #   #   #    # #    #   #      #        #####  #     # \n" +
                "                                                                         ");
        System.out.println("==========================================================================");
        System.out.println("Author: Fek");
        System.out.println("Native-Language:" + plugin.getConfig().getString("militaryfun.language"));
        System.out.println("Need Munition:" + plugin.getConfig().getBoolean("militaryfun.needmunition"));
        //Chama os metodos de criação de itens
        ItemManager.Init();

        //Listeners, aqueles que vem se o evento acontece
        getServer().getPluginManager().registerEvents(new BrokenBlock(), this);
        getServer().getPluginManager().registerEvents(new CraftItem(), this);
        getServer().getPluginManager().registerEvents(new Guns(), this);

        //Comandos

    }

    //OnDisable é ativo quando o server fecha ou da reload
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    //Metodo Get para o plugin
    public static MilitaryFun getPlugin() {
        return plugin;
    }

}

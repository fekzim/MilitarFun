package me.fek.militaryfun;

import org.bukkit.plugin.java.JavaPlugin;

public final class MilitaryFun extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("==========================");
        System.out.println("MilitaryFun ONLINE");
        System.out.println("Author: Fek");
        System.out.println("==========================");

        ItemManager.Init();

        //Listener
        getServer().getPluginManager().registerEvents(new BrokenBlock(), this);
        getServer().getPluginManager().registerEvents(new CraftItem(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

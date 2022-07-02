package me.fek.militaryfun;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import java.util.Objects;

public class Guns implements Listener {

    //Arrumar um arquivo config com nome das armas que permita o plugin reconhecer todas as armas
    //para que no futuro não tenhamos que ficar mudando tanto esse codigo para isso
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event){
        //try para caso uma das variaveis/metas estarem vazios um gigante erro no log aparecer
        try{
            //variaveis
            Action action = event.getAction(); //ação do player (clicks)
            Player player = event.getPlayer(); //Instancia Player do evento
            ItemStack item = player.getInventory().getItemInMainHand(); //Item que o player esta segurando
            ItemMeta meta = item.getItemMeta(); //Meta do item atual
            assert meta != null;
            PersistentDataContainer data = meta.getPersistentDataContainer(); //Meta data do item atual
            String mainHandName = Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).getDisplayName(); //Pegar o displayname do item da mão

            //Atirar(clicar com direito apenas usando um item chamado pistola)
            if(action == Action.RIGHT_CLICK_AIR && mainHandName.contains("§8§lPistola ") && !(player.isSneaking())){
                if(data.has(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)){
                    if(Objects.requireNonNull(data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)) > 0 && !(player.isSneaking())){
                        int municaoFinal =  Objects.requireNonNull(data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER));


                        //Atirar
                        /*Arrow a = player.launchProjectile(Arrow.class);
                        a.setDamage(10);
                        a.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);*/
                        Snowball snow = player.launchProjectile(Snowball.class);
                        snow.setVelocity(snow.getVelocity().multiply(2.9));

                        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1,3);
                        particle(player);

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

            //Recarregar arma(clicar direito e estar no shift/Sneaking)
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
                                if(Objects.requireNonNull(is.getItemMeta()).getDisplayName().equals("§8Munição")){
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
                //setar nome da arma no final
                meta.setDisplayName("§8§lPistola " + ChatColor.WHITE + "["+data.get(new NamespacedKey(MilitaryFun.getPlugin(),"munition"), PersistentDataType.INTEGER)+"]");
                item.setItemMeta(meta);
            }
        }catch (Exception exception){
            return;
        }

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof  Snowball){
            System.out.println("Bola de neve atirada");
            Snowball snowball = (Snowball) event.getDamager();
            Player player = (Player) snowball.getShooter();
            if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("§8§lPistola")){
                player.sendMessage("player atirou com uma arma");
                event.setDamage(10.0);
            }else{
                System.out.println("player não atirou com bola de neve");
            }
        }

        if(event.getDamager() instanceof  Player){
            if(event.getEntity() instanceof Snowball){
                Snowball snowball = (Snowball) event.getDamager();
                Player shooter = (Player) snowball.getShooter();
                if(shooter.getInventory().getItemInMainHand().getItemMeta().getDisplayName().contains("§8§lPistola ")){
                    event.setDamage(10.0);
                }
            }
        }
    }

    public static void particle(Player p) {
        Vector v = p.getEyeLocation().getDirection().normalize();
        Location loc = p.getEyeLocation();
        double lineSize = 10.0;

        for (double x = 0; x <= lineSize; x+=0.5) {
            Vector vc = v.clone().multiply(x);

            Location particleLoc = loc.clone().add(vc);

            int particleQuantity = 0;
            double offsetX = 0;
            double offsetY = 0;
            double offsetZ = 0;
            double velocity = 0.05;

            particleLoc.getWorld().spawnParticle(Particle.SMOKE_NORMAL, particleLoc, particleQuantity, offsetX, offsetY, offsetZ, velocity);
        }
    }
}
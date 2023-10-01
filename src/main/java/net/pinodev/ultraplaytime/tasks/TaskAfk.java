package net.pinodev.ultraplaytime.tasks;

import net.pinodev.ultraplaytime.cache.AfkUser;
import net.pinodev.ultraplaytime.cache.User;
import net.pinodev.ultraplaytime.configs.files.Settings;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static net.pinodev.ultraplaytime.UltraPlaytime.userManager;

public class TaskAfk {

    public ConcurrentHashMap<UUID, AfkUser> usersAfk = new ConcurrentHashMap<>();
    void check(){
        for(UUID uuid : usersAfk.keySet()){
            final Player player = Bukkit.getPlayer(uuid);
            if(player != null && player.isOnline()){
                final Location location = player.getLocation();
                final AfkUser afkUser = usersAfk.get(uuid);
                if(afkUser != null){
                    final Location lastLocation = afkUser.getLocation();
                    final Integer afkForSeconds = afkUser.getSeconds();
                    final User user = userManager.getCachedUsers().get(uuid);
                    if(location.equals(lastLocation)){
                        afkUser.setSeconds(afkForSeconds+2);
                        if(afkForSeconds >= Settings.AFK_AFTER.getInt()){
                            // Player is now considered AFK
                            if(user != null){
                                user.setAfk(true);
                            }
                        }
                    }else{
                        if (user != null) { user.setAfk(false); afkUser.setSeconds(0); }
                    }
                    afkUser.setLocation(location);
                }
            }
        }
    }

}

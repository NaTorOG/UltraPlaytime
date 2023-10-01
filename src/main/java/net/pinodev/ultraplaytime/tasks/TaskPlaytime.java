package net.pinodev.ultraplaytime.tasks;

import net.pinodev.ultraplaytime.cache.User;

import static net.pinodev.ultraplaytime.UltraPlaytime.userManager;

public class TaskPlaytime {

    void update(){
        for(User user : userManager.getCachedUsers().values()){
            if(!user.isOffline() && !user.isAfk()){
                Long playtime = user.getPlaytime();
                playtime = playtime + 2000;
                user.setPlaytime(playtime);
            }
        }
    }
}

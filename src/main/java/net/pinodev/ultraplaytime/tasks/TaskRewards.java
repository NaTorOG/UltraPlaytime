package net.pinodev.ultraplaytime.tasks;

import net.pinodev.ultraplaytime.cache.User;
import net.pinodev.ultraplaytime.configs.files.Rewards;
import net.pinodev.ultraplaytime.rewards.Reward;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static net.pinodev.ultraplaytime.UltraPlaytime.*;

public class TaskRewards {

    public Set<Reward> pending = ConcurrentHashMap.newKeySet();

    void check(){
        for(User user : userManager.getCachedUsers().values()){
            final Long playtime = user.getPlaytime();
            for(int rewardID : rewardManager.getRegisteredRewards().keySet()){
                final int secondsRequirement = rewardManager.getRegisteredRewards().get(rewardID);
                long millisRequirement = (long) secondsRequirement*1000;
                if(!user.getRewardsAchieved().contains(rewardID) && playtime >= millisRequirement && !user.isOffline()){
                    pending.add(new Reward(
                            user.getUuid(),
                            rewardID,
                            Rewards.REWARD_UPPER_TITLE.getString(rewardID),
                            Rewards.REWARD_SUB_TITLE.getString(rewardID),
                            Rewards.REWARD_SOUND.getString(rewardID),
                            Rewards.REWARD_MESSAGES.getStringList(rewardID),
                            Rewards.REWARD_COMMANDS.getStringList(rewardID)
                    ));
                    user.getRewardsAchieved().add(rewardID);
                }
            }
        }
    }

    void execute(){
        new BukkitRunnable(){
            @Override
            public void run() {
                long time = System.currentTimeMillis();
                if(pending.isEmpty()) return;
                for (Reward reward : pending){
                    final Player player = Bukkit.getPlayer(reward.getPlayerToReward());
                    if(player != null && player.isOnline()){
                        giveReward(player, reward);
                    }
                }
                logger.info("Took Reward " + (System.currentTimeMillis() - time) + "ms");
            }
        }.runTaskTimer(mainInstance, 0L, 40L);
    }

    private void giveReward(Player player, Reward reward){
        if(player != null && player.isOnline()){
            final Sound rewardSound = Sound.valueOf(reward.getSound());
            final List<String> commands = reward.getCommands().stream().map(string -> string.replace("{player}", player.getName()))
                    .filter(string -> string.contains(player.getName()))
                    .collect(Collectors.toList());;
            final List<String> messages = reward.getMessages().stream().map(string -> string.replace("{player}", player.getName()))
                    .filter(string -> string.contains(player.getName()))
                    .collect(Collectors.toList());
            final String upperTitle = reward.getUpperTitle().replace("{player}", player.getName());
            final String subTitle = reward.getSubTitle().replace("{player}", player.getName());
            player.playSound(player.getLocation(), rewardSound, 1f, 1f);
            utilsManager.message.send(messages, player);
            utilsManager.message.sendTitle(upperTitle, subTitle, player);
            for(String command : commands){
                Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), command);
            }
            pending.remove(reward);
        }
    }

    public TaskRewards(){
        execute();
    }
}

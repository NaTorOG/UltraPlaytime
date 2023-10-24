package net.pinodev.ultraplaytime.configs.files;


import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.util.List;
import java.util.function.Function;

import static net.pinodev.ultraplaytime.UltraPlaytime.*;

public enum Rewards {

    REWARD_REQUIREMENT("Rewards.X.requirement"),
    REWARD_MESSAGES("Rewards.X.messages"),
    REWARD_UPPER_TITLE("Rewards.X.title.upperTitle"),
    REWARD_SUB_TITLE("Rewards.X.title.subTitle"),
    REWARD_SOUND("Rewards.X.sound"),
    REWARD_COMMANDS("Rewards.X.commands");

    private final String key;

    Rewards(String key) {
        this.key = key;
    }

    public String getString(int rewardID) {
        return rewardsYML.getFileConfiguration().getString(key.replace("X", String.valueOf(rewardID)));
    }

    public Integer getInt(int rewardID){
        return rewardsYML.getFileConfiguration().getInt(key.replace("X", String.valueOf(rewardID)));
    }

    public List<String> getStringList(int rewardID){
        return rewardsYML.getFileConfiguration().getStringList(key.replace("X", String.valueOf(rewardID)));
    }
}

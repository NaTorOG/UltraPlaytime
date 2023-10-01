package net.pinodev.ultraplaytime.configs.files;


import java.util.List;
import java.util.function.Function;

import static net.pinodev.ultraplaytime.UltraPlaytime.*;

public enum Rewards {

    REWARD_REQUIREMENT(Integer.class, "Rewards.X.requirement", rewardsYML.getFileConfiguration()::getInt),
    REWARD_MESSAGES(List.class, "Rewards.X.messages", rewardsYML.getFileConfiguration()::getStringList),
    REWARD_UPPER_TITLE(String.class, "Rewards.X.title.upperTitle", rewardsYML.getFileConfiguration()::getString),
    REWARD_SUB_TITLE(String.class, "Rewards.X.title.subTitle", rewardsYML.getFileConfiguration()::getString),
    REWARD_SOUND(Integer.class, "Rewards.X.sound", rewardsYML.getFileConfiguration()::getString),
    REWARD_COMMANDS(List.class, "Rewards.X.commands", rewardsYML.getFileConfiguration()::getStringList);


    public final String key;
    public final Function<String, ?> value;

    Rewards(Class<?> type, String key, Function<String, ?> value) {
        this.key = key;
        this.value = value;
    }

    public String getString(int rewardID) {
        return (String) value.apply(key.replace("X", String.valueOf(rewardID)));
    }

    public Integer getInt(int rewardID){
        return (Integer) value.apply(key.replace("X", String.valueOf(rewardID)));
    }

    public List<String> getStringList(int rewardID){
        return castToList(value.apply(key.replace("X", String.valueOf(rewardID))));
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> castToList(Object obj) {
        return (List<T>) obj;
    }


}

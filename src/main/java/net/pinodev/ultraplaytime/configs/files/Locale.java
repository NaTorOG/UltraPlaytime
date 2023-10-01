package net.pinodev.ultraplaytime.configs.files;

import net.md_5.bungee.api.ChatColor;

import java.util.List;
import java.util.function.Function;

import static net.pinodev.ultraplaytime.UltraPlaytime.langYML;
import static net.pinodev.ultraplaytime.UltraPlaytime.utilsManager;


public enum Locale {
    PREFIX(String.class, "prefix", langYML.getFileConfiguration()::getString),
    TIME_YEARS(String.class, "TimeFormat.years", langYML.getFileConfiguration()::getString),
    TIME_YEAR(String.class, "TimeFormat.year", langYML.getFileConfiguration()::getString),
    TIME_MONTHS(String.class, "TimeFormat.months", langYML.getFileConfiguration()::getString),
    TIME_MONTH(String.class, "TimeFormat.month", langYML.getFileConfiguration()::getString),
    TIME_DAYS(String.class, "TimeFormat.days", langYML.getFileConfiguration()::getString),
    TIME_DAY(String.class, "TimeFormat.day", langYML.getFileConfiguration()::getString),
    TIME_HOURS(String.class, "TimeFormat.hours", langYML.getFileConfiguration()::getString),
    TIME_HOUR(String.class, "TimeFormat.hour", langYML.getFileConfiguration()::getString),
    TIME_MINUTES(String.class, "TimeFormat.minutes", langYML.getFileConfiguration()::getString),
    TIME_MINUTE(String.class, "TimeFormat.minute", langYML.getFileConfiguration()::getString),
    TIME_SECONDS(String.class, "TimeFormat.seconds", langYML.getFileConfiguration()::getString),
    TIME_SECOND(String.class, "TimeFormat.second", langYML.getFileConfiguration()::getString),
    PLAYER_PLAYTIME(List.class, "playerPlaytime", langYML.getFileConfiguration()::getStringList);





    public final String key;
    public final Function<String, ?> value;

    Locale(Class<?> type, String key, Function<String, ?> value) {
        this.key = key;
        this.value = value;
    }

    public String getString() {
        return (String) value.apply(key);
    }

    public String getStripped(){
        String text =  (String) value.apply(key);
        text = text.replaceAll("&", "§");
        return ChatColor.stripColor(text);
    }

    public String getColorized(){
        final String message = (String) value.apply(key);
        return utilsManager.message.colorized(message);
    }

}

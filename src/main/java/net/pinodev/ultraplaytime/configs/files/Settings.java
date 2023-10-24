package net.pinodev.ultraplaytime.configs.files;

import java.util.function.Function;

import static net.pinodev.ultraplaytime.UltraPlaytime.settingsYML;

public enum Settings {
    MULTI_SERVER(Boolean.class, "MultiServer.enabled", settingsYML.getFileConfiguration()::getBoolean),
    MULTI_SERVER_REWARDS(Boolean.class, "MultiServer.disable_rewards_here", settingsYML.getFileConfiguration()::getBoolean),
    MULTI_SERVER_LATENCY(Long.class, "MultiServer.network_latency_milliseconds", settingsYML.getFileConfiguration()::getLong),
    DB_TYPE(String.class, "Database.type", settingsYML.getFileConfiguration()::getString),
    DB_NAME(String.class, "Database.name", settingsYML.getFileConfiguration()::getString),
    DB_TABLE_USERDATA(String.class, "Database.table_userdata", settingsYML.getFileConfiguration()::getString),
    DB_TABLE_NO_LEADERBOARD(String.class, "Database.table_no_leaderboard", settingsYML.getFileConfiguration()::getString),
    DB_HOST(String.class, "Database.host", settingsYML.getFileConfiguration()::getString),
    DB_PORT(String.class, "Database.port", settingsYML.getFileConfiguration()::getString),
    DB_PASSWORD(String.class, "Database.password", settingsYML.getFileConfiguration()::getString),
    DB_USER(String.class, "Database.user", settingsYML.getFileConfiguration()::getString),
    DB_CONNECTION_TIMEOUT(Long.class, "Database.connectionTimeout", settingsYML.getFileConfiguration()::getLong),
    DB_IDLE_TIMEOUT(Long.class, "Database.idleTimeout", settingsYML.getFileConfiguration()::getLong),
    DB_MAX_LIFETIME(Long.class, "Database.maxLifeTime", settingsYML.getFileConfiguration()::getLong),
    DB_MAX_POOL_SIZE(Integer.class, "Database.maxPoolSize", settingsYML.getFileConfiguration()::getInt),
    LEADERBOARD_REFRESH(Integer.class, "Leaderboard.refreshMinutes", settingsYML.getFileConfiguration()::getInt),
    AUTO_SAVE(Integer.class, "AutoSave.everyMinutes", settingsYML.getFileConfiguration()::getInt),
    AFK_AFTER(Integer.class, "AntiAFK.afkAfterSeconds", settingsYML.getFileConfiguration()::getInt);


    private final String key;
    private final Function<String, ?> value;

    Settings(Class<?> type, String key, Function<String, ?> value) {
        this.key = key;
        this.value = value;
    }

    public boolean getBoolean(){
        return (Boolean) value.apply(key);
    }


    public String getString() {
        return (String) value.apply(key);
    }

    public Integer getInt() {
        return (Integer) value.apply(key);
    }

    public Long getLong() {
        return (Long) value.apply(key);
    }

}

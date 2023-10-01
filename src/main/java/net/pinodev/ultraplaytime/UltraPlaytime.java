package net.pinodev.ultraplaytime;

import net.pinodev.ultraplaytime.tasks.TasksManager;
import net.pinodev.ultraplaytime.cache.UserManager;
import net.pinodev.ultraplaytime.commands.PlayerCommand;
import net.pinodev.ultraplaytime.configs.ConfigFiles;
import net.pinodev.ultraplaytime.configs.files.Settings;
import net.pinodev.ultraplaytime.database.DatabaseManager;
import net.pinodev.ultraplaytime.database.connectors.H2;
import net.pinodev.ultraplaytime.database.connectors.MariaDB;
import net.pinodev.ultraplaytime.database.connectors.MySQL;
import net.pinodev.ultraplaytime.libs.LibManager;
import net.pinodev.ultraplaytime.listeners.PlayerJoin;
import net.pinodev.ultraplaytime.listeners.PlayerQuit;
import net.pinodev.ultraplaytime.rewards.RewardManager;
import net.pinodev.ultraplaytime.utils.UtilsManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

import static net.pinodev.ultraplaytime.configs.files.Settings.DB_TYPE;
import static net.pinodev.ultraplaytime.utils.LogUtils.*;

public final class UltraPlaytime extends JavaPlugin {

    public static UltraPlaytime mainInstance;

    public static final Logger logger = Logger.getLogger("UltraPlayTime");

    private final PluginManager pluginManager = getServer().getPluginManager();


    /*
    File configuration instances
     */
    public static ConfigFiles settingsYML;

    public static ConfigFiles langYML;

    public static ConfigFiles rewardsYML;

    /*
    Database
     */

    public static DatabaseManager database;

    /*
    Tasks
     */

    public static TasksManager tasksManager;

    /*
    Cache
     */
    public static UserManager userManager;

    /*
    Reward Manager
     */

    public static RewardManager rewardManager;

    /*
    Utils
     */

    public static UtilsManager utilsManager = new UtilsManager();

    @Override
    public void onEnable() {
        long startTime = System.currentTimeMillis();
        logger.info(ANSI_GREEN.getColor() + "Plugin is loading...."+ ANSI_RESET.getColor());
        setInstances();
        setListeners();
        setCommands();
        logger.info("Took " + (System.currentTimeMillis() - startTime) +" ms");

    }

    @Override
    public void onDisable() {
        tasksManager.shutdownScheduler();
        database.closeHikariPool();
    }

    private void setInstances(){
        mainInstance = this;
        setConfigs();
        new LibManager();
        utilsManager = new UtilsManager();
        userManager = new UserManager();
        loadDatabase();
        if(!Settings.MULTI_SERVER_REWARDS.getBoolean()){
            rewardManager = new RewardManager();
        }
        tasksManager = new TasksManager();

    }

    private void setConfigs(){
        settingsYML = new ConfigFiles("settings.yml", getDataFolder());
        langYML = new ConfigFiles("lang.yml", getDataFolder());
        rewardsYML = new ConfigFiles("rewards.yml", getDataFolder());
    }

    private void setListeners(){
        PlayerJoin playerJoin = new PlayerJoin();
        pluginManager.registerEvents(playerJoin,this);
        PlayerQuit playerQuit = new PlayerQuit();
        pluginManager.registerEvents(playerQuit,this);
    }

    private void setCommands(){
        PlayerCommand playerCommand = new PlayerCommand();
        loadCommand("playtime", playerCommand, playerCommand);
    }


    private void loadDatabase(){
        switch (DB_TYPE.getString().toLowerCase()) {
            case "h2":
                database = new H2();
                break;
            case "mysql":
                database = new MySQL();
                break;
            case "mariadb":
                database = new MariaDB();
                break;
            default:
                logger.info("!!! Invalid database type specified -> " + DB_TYPE.getString());
                logger.info("!!! Switching to default H2");
                database = new H2();
                break;

        }
    }

    private void loadCommand(String cmdName, CommandExecutor executor, TabCompleter tabCompleter) {
        PluginCommand cmd = getServer().getPluginCommand(cmdName);
        if (cmd != null) {
            cmd.setExecutor(executor);
            cmd.setTabCompleter(tabCompleter);
        } else {
            logger.info("Command " + cmdName + " not found!");
        }
    }
}

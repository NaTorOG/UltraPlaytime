package net.pinodev.ultraplaytime.commands.admin.subcommands;

import com.google.common.collect.ImmutableList;
import net.pinodev.ultraplaytime.commands.SubCommand;
import net.pinodev.ultraplaytime.configs.files.Locale;
import org.bukkit.command.CommandSender;

import java.util.List;

import static net.pinodev.ultraplaytime.UltraPlaytime.*;

public class Reload extends SubCommand {
    public Reload(String name, String permission, boolean consoleAllowed) {
        super(name, permission, consoleAllowed);
    }

    @Override
    public void execute(CommandSender executor, String[] args) {
        if(args.length == 1){
            reloadConfigs(executor);
        }else{
            utilsManager.message.send(Locale.INVALID_COMMAND, executor,null);
        }
    }

    @Override
    public List<String> tabComplete(CommandSender executor, String[] args) {
        return ImmutableList.of();
    }

    private void reloadConfigs(CommandSender executor){
        langYML.reload(executor);
        rewardsYML.reload(executor);
        rewardManager.loadRegisteredRewards();
    }
}

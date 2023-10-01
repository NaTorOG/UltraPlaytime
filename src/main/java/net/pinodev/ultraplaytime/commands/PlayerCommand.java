package net.pinodev.ultraplaytime.commands;

import net.pinodev.ultraplaytime.configs.files.Rewards;
import net.pinodev.ultraplaytime.placeholder.Placeholders;
import net.pinodev.ultraplaytime.cache.User;
import net.pinodev.ultraplaytime.commands.player.RewardsCommand;
import net.pinodev.ultraplaytime.commands.player.TopCommand;
import net.pinodev.ultraplaytime.configs.files.Locale;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static net.pinodev.ultraplaytime.UltraPlaytime.userManager;
import static net.pinodev.ultraplaytime.UltraPlaytime.utilsManager;

public class PlayerCommand implements CommandExecutor, TabExecutor {

    private final List<Subcommands> subcommands;

    private List<String> commandNames;

    public PlayerCommand(){
        subcommands = new ArrayList<>();
        subcommands.add(new RewardsCommand());
        subcommands.add(new TopCommand());
        commandNames = new ArrayList<>();
        for(Subcommands subcommand : subcommands){
            commandNames.add(subcommand.getName());
        }
    }
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        switch (strings.length){
            case 0:
                if(commandSender instanceof ConsoleCommandSender) {
                    commandSender.sendMessage(Locale.PREFIX.getStripped() + "Only players can see their playtime!");
                }else{
                    final Player player = (Player) commandSender;
                    final User user = userManager.getCachedUsers().get(player.getUniqueId());
                    final Long playtime = user.getPlaytime();
                    List<Placeholders> placeholders = new ArrayList<>();
                    placeholders.add(new Placeholders("{prefix}", Locale.PREFIX.getColorized()));
                    placeholders.add(new Placeholders("{playtime}", utilsManager.playtime.formatted(playtime)));
                    utilsManager.message.send(Locale.PLAYER_PLAYTIME, commandSender, placeholders);
                }
                break;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return null;
    }
}

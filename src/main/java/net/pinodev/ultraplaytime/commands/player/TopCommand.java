package net.pinodev.ultraplaytime.commands.player;

import net.pinodev.ultraplaytime.commands.Subcommands;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.List;

public class TopCommand implements Subcommands {
    @Override
    public String getName() {
        return "top";
    }

    @Override
    public String getDescription() {
        return "Check the top players based on their playtime";
    }

    @Override
    public Permission getPermissions() {
        return new Permission("ultraplaytime.top");
    }

    @Override
    public void handle(CommandSender commandSender, Command cmd, String[] args) {

    }

    @Override
    public List<String> onTableComplete(CommandSender commandSender, Command cmd, String[] args) {
        return null;
    }
}

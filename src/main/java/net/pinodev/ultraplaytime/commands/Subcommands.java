package net.pinodev.ultraplaytime.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.List;

public interface Subcommands {

    String getName();

    String getDescription();

    Permission getPermissions();

    void handle(CommandSender commandSender, Command cmd, String[] args);

    List<String> onTableComplete(CommandSender commandSender, Command cmd, String[] args);
}

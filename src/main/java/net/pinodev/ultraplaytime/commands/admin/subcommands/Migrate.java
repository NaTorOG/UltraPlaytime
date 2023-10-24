package net.pinodev.ultraplaytime.commands.admin.subcommands;

import net.pinodev.ultraplaytime.commands.SubCommand;
import org.bukkit.command.CommandSender;

import java.util.List;

public class Migrate extends SubCommand {
    public Migrate(String name, String permission, boolean consoleAllowed) {
        super(name, permission, consoleAllowed);
    }

    @Override
    public void execute(CommandSender executor, String[] args) {

    }

    @Override
    public List<String> tabComplete(CommandSender executor, String[] args) {
        return null;
    }
}

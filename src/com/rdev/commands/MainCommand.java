package com.rdev.commands;

import com.rdev.consts.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(Constants.Command.NOT_PLAYER_ERROR);
            return true;
        }

        Player p = (Player) commandSender;

        String subCommand;

        if (args.length == 0) {
            p.sendMessage(Constants.Command.MAIN_COMMAND_USAGE);
            return true;
        }

        subCommand = args[0];

        switch (subCommand) {
            case "test":
                Commands.testCommand(p.getLocation().add(0,1,0));
                commandSender.sendMessage(Constants.Command.SPAWN_MOB_SUCCESS.replace("%mob%", "TestMob"));
                //Commands.spawnDuck(p.getLocation().add(0,1,0));
                break;

            case "test2":
                Commands.checkList();
                //Commands.spawnDuck(p.getLocation().add(0,1,0));
                break;

            default:
                p.sendMessage(Constants.Command.MAIN_COMMAND_USAGE);
                break;
        }

        return false;
    }
}

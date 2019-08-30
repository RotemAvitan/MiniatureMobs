package com.rdev.commands;

import com.rdev.consts.Constants;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The main command, handling subcommands and arguments.
 */
public class MainCommand implements CommandExecutor {

    /**
     * {@inheritDoc}
     */
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

        /* /mm spawnmob <mobname> <amount> */
        subCommand = args[0];

        switch (subCommand) {
            case "spawnmob":
                String nameID = args[1];

                int amount = 0;

                try {
                    amount = Integer.parseInt(args[2]);
                } catch (NumberFormatException e) {
                    p.sendMessage(Constants.Command.NOT_A_NUMBER);
                    return true;
                }

                Commands.spawnMob(nameID, amount, p.getLocation().add(0,1,0), commandSender);
                break;
            case "test3":
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

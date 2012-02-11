package com.github.Monofraps.MonoBoxel.CommandExecutors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.Monofraps.MonoBoxel.MonoBoxel;


public class MBBoxellookupCommandExecutor implements CommandExecutor {

	private MonoBoxel master;

	public MBBoxellookupCommandExecutor(MonoBoxel plugin) {
		master = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String lable, String[] args) {

		if (!(sender instanceof Player)) {
			sender.sendMessage("You must be a player!");
			return true;
		}

		Player player = (Player) sender;

		if(!master.getMBWorldManager().isBoxel(player.getWorld().getName())[0])
		{
			player.sendMessage("You are not in a Boxel!");
			return true;
		}
		
		String boxelOwner = player.getWorld().getName().substring(master.getBoxelPrefix().length());		
		player.sendMessage("This boxel belongs to " + boxelOwner);
		

		return false;
	}

}
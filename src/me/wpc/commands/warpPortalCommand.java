package me.wpc.commands;

import me.wpc.main;
import me.wpc.methods.WPCUtili;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class warpPortalCommand implements CommandExecutor {

	static main plugin;
	public warpPortalCommand(main i){
	plugin = i;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (sender.hasPermission("wpc.reload") == false){ WPCUtili.smsg(sender, "&cYou do not have permission to use this command.");return false;}	
	
	//wpc
	if (args.length == 0){
	WPCUtili.smsg(sender, "&c/wpc reload &f- Reloads the configuration file for WPC.");
	WPCUtili.smsg(sender, "&c/awpc <warp portal name> <true|false> <true|false> <command> &f- Adds commands to warp portals");
	WPCUtili.smsg(sender, "&c/dwpc <warp portal name> <command line> [delall] &f- Deletes commands from warp portals");
	WPCUtili.smsg(sender, "&c/lwpc <warp portal name> &f- Lists commands of the warp portal");
	WPCUtili.smsg(sender, "&c/awpp <warp portal name> <price> &f- Adds price to warp portals");
	return true;	
	}
	
	//wpc reload
	if (args.length == 1){
	if (args[0].equalsIgnoreCase("reload")){
	plugin.reloadConfig();
	WPCUtili.smsg(sender, "&aWPC configuration file has been reloaded.");
	}
	else {
	WPCUtili.smsg(sender, "&4Invalid Argument: &c" + args[0]);
	}
	return true;
	}
	
	return false;
	}

}

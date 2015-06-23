package me.wpc.commands;

import java.util.HashMap;
import java.util.List;

import me.wpc.main;
import me.wpc.methods.WPCUtili;
import me.wpc.methods.WPortalFile;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class addWarpPortalCommand implements CommandExecutor {

	static main plugin;
	public addWarpPortalCommand(main i){
	plugin = i;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (sender.hasPermission("wpc.add.commands") == false){ WPCUtili.smsg(sender, "&cYou do not have permission to use this command.");return false;}	
	
	//awpc
	if (args.length == 0){
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Incorrect Usage: &c/awpc <warp-portal-name> <true|false> <true|false> <command>");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;	
	}
	
	//awpc <warp name>
	if (args.length == 1){
	if (WPortalFile.getConfig().getString("portals." + args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist."); return false;}	
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Usage: &c/awpc <warp-portal-name> <true|false> <true|false> <command> ");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	
	//awpc <warp name> <true|false>
	if (args.length == 2){
	if (WPortalFile.getConfig().getString("portals." + args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist."); return false;}	
	if (args[1].equals(true)){
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Usage: &c/awpc <warp-portal-name> <true|false> <true|false> <command>");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	else if (args[1].equals(false)){
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Usage: &c/awpc <warp-portal-name> <true|false> <true|false> <command>");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	else {
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&cInvalid boolean value &4" + args[1]);	
	WPCUtili.smsg(sender, "&4Usage: &c/awpc <warp-portal-name> <true|false> <true|false> <command> ");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	}
	
	//awpc <warp name> <true|false> <true|false>
	if (args.length == 3){
	if (WPortalFile.getConfig().getString("portals." + args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist."); return false;}	
	if (args[2].equals(true)){
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Usage: &c/awpc <warp-portal-name> <true|false>  <command>");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	else if (args[2].equals(false)){
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Usage: &c/awpc <warp-portal-name> <true|false> <true|false> <command>");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	else {
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&cInvalid boolean value &4" + args[2]);	
	WPCUtili.smsg(sender, "&4Usage: &c/awpc <warp-portal-name> <true|false> <true|false> <command> ");
	WPCUtili.smsg(sender, "&6Variables: &c%player - Will be replaced with the player entering the portal.");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	}
	
	//awpc <warp name> <true|false> <true|false> <command>
	if (args.length >= 4){
	if (WPortalFile.getConfig().getString("portals." + args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist."); return false;}	
	HashMap<CommandSender,StringBuilder> buildString = new HashMap<CommandSender,StringBuilder>();
	HashMap<CommandSender, String> stringBuilt = new HashMap<CommandSender,String>();
	HashMap<CommandSender, List<String>> commandList = new HashMap<CommandSender,List<String>>();
	HashMap<CommandSender, Integer> loop = new HashMap<CommandSender,Integer>();
	
	buildString.put(sender, new StringBuilder());
	commandList.put(sender, plugin.getConfig().getStringList(args[0] + ".commands"));
	
	if (plugin.getConfig().getString(args[0]) == null){
	plugin.getConfig().set(args[0] + ".teleport", Boolean.parseBoolean(args[1])); 
	plugin.getConfig().set(args[0] + ".console-only", Boolean.parseBoolean(args[2]));
	plugin.saveConfig();
	}
	
	for (loop.put(sender, 3); loop.get(sender) < args.length; loop.put(sender, loop.get(sender) + 1)){
	buildString.get(sender).append(args[loop.get(sender)] + " ");	
	}
	stringBuilt.put(sender, buildString.get(sender).toString());
	
	commandList.get(sender).add(stringBuilt.get(sender));
	plugin.getConfig().set(args[0] + ".commands", commandList.get(sender));
	plugin.saveConfig();
	
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&bCommand &c/" + stringBuilt.get(sender) + " &badded to warp portal: &c" + args[0]);
	WPCUtili.smsg(sender, "&b" + args[0] + " Teleportation: &c" + args[1]);
	WPCUtili.smsg(sender, "&b" + args[0] + " Console-Only: &c" + args[2]);
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	
	buildString.clear();
	return true;
	}
	
	
	return false;
	}

}

package me.wpc.commands;

import java.util.HashMap;
import java.util.List;

import me.wpc.main;
import me.wpc.methods.WPCUtili;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class delWarpPortalCommand implements CommandExecutor {

	static main plugin;
	public delWarpPortalCommand(main i){
	plugin = i;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (sender.hasPermission("wpc.delete.commands") == false){ WPCUtili.smsg(sender, "&cYou do not have permission to use this command.");return false;}	
	
	HashMap<CommandSender, List<String>> commandList = new HashMap<CommandSender,List<String>>();
	
	//dwpc
	if (args.length == 0){
	WPCUtili.smsg(sender, "&4Incorrect Usage: &c/dwpc <warp-portal-name> <command line> [delall]");
	return true;	
	}
	
	//dwpc <warp name>
	if (args.length == 1){
	if (plugin.getConfig().getString(args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist in WPC."); return false;}	
	WPCUtili.smsg(sender, "&4Usage: &c/dwpc <warp-portal-name> <command line> [delall]");
	return true;
	}
	
	//dwpc <warp name> <command line>
	if (args.length == 2){
	if (plugin.getConfig().getString(args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist in WPC."); return false;}	
	try {
	if (Integer.parseInt(args[1]) > plugin.getConfig().getStringList(args[0] + ".commands").size()){WPCUtili.smsg(sender, "&cThat command line doesn't exist."); return false;}	
	commandList.put(sender, plugin.getConfig().getStringList(args[0] + ".commands"));
	if (Integer.parseInt(args[1]) < 1){WPCUtili.smsg(sender, "&cCommand lines start from &41."); return false;}
	commandList.get(sender).remove(Integer.parseInt(args[1]) - 1);
	plugin.getConfig().set(args[0] + ".commands", commandList.get(sender));
	plugin.saveConfig();
	WPCUtili.smsg(sender, "&aCommand line &c" + Integer.parseInt(args[1]) + " &aremoved from &c" + args[0]);
	WPCUtili.smsg(sender, "&aTo view command lines of warp portals type &c/lwpc.");
	}
	catch (NumberFormatException exc){
	WPCUtili.smsg(sender, "&4Invalid Command Line: &c" + args[1]);	
	}
	return true;
	}
	
	//dwpc <warp name> <command line> [dellall]
	if (args.length == 3){
	if (args[2].equalsIgnoreCase("delall")){
	if (plugin.getConfig().getString(args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist in WPC."); return false;}	
	plugin.getConfig().set(args[0], null);
	plugin.saveConfig();
	WPCUtili.smsg(sender, "&bWarp portal &c" + args[0] + " &bcommands have been deleted.");
	}
	else {
	WPCUtili.smsg(sender, "&cInvalid argument &4" + args[2]);	
	}
	return true;
	}
	
	if (args.length >= 4){
	WPCUtili.smsg(sender, "&cToo many arguments.");	
	return true;	
	}
	
	
	

	
	
	return false;
	}

}

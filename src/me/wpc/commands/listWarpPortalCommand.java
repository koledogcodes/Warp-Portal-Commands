package me.wpc.commands;

import java.util.HashMap;
import java.util.List;

import me.wpc.main;
import me.wpc.methods.WPCUtili;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class listWarpPortalCommand implements CommandExecutor {

	static main plugin;
	public listWarpPortalCommand(main i){
	plugin = i;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (sender.hasPermission("wpc.list.commands") == false){ WPCUtili.smsg(sender, "&cYou do not have permission to use this command.");return false;}	
	
	//lwpc
	if (args.length == 0){
	WPCUtili.smsg(sender, "&4Incorrect Usage: &c/lwpc <warp-portal-name>");
	return true;	
	}
	
	//lwpc <warp name>
	if (args.length == 1){
	if (plugin.getConfig().getString(args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist in WPC."); return false;}	
	HashMap<CommandSender, List<String>> commandList = new HashMap<CommandSender,List<String>>();
	HashMap<CommandSender, Integer> loop = new HashMap<CommandSender,Integer>();
	commandList.put(sender, plugin.getConfig().getStringList(args[0] + ".commands"));
	
	WPCUtili.smsg(sender, "&b" + args[0] + " commands:");
	
	if (commandList.get(sender).size() < 1){
	WPCUtili.smsg(sender, "&eNo Commands." );
	WPCUtili.smsg(sender, "&b" + args[0] + " teleport: &c" + plugin.getConfig().getBoolean(args[0] + ".teleport"));
	WPCUtili.smsg(sender, "&b" + args[0] + " console-only: &c" + plugin.getConfig().getBoolean(args[0] + ".console-only"));
	return true;
	}
	
	for (loop.put(sender, 0); loop.get(sender) < (commandList.size() + 1); loop.put(sender, loop.get(sender) + 1) ){
	WPCUtili.smsg(sender, "&e" + (loop.get(sender) + 1) + ". /" + commandList.get(sender).get(loop.get(sender)));	
	}
	
	WPCUtili.smsg(sender, "&b" + args[0] + " teleport: &c" + plugin.getConfig().getBoolean(args[0] + ".teleport"));
	WPCUtili.smsg(sender, "&b" + args[0] + " console-only: &c" + plugin.getConfig().getBoolean(args[0] + ".console-only"));
	
	return true;
	}
	
	
	if (args.length >= 2){
	WPCUtili.smsg(sender, "&cToo many arguments.");	
	return true;	
	}
	return false;
	}

}

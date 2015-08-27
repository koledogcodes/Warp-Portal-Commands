package me.wpc.methods;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import me.wpc.main;

public class WPCUtili {

	static main plugin;
	public WPCUtili(main i){
	plugin = i;
	}
	
	//Message CommanderSender Method
	public static void smsg(CommandSender sender, String string){
	sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"&8[&aWPC&8] " + string));
	}
	
	//WPC Config generation.
	public static void generateWPCConfiguration(){
	if (plugin.getConfig().getString("Developer-Join") != null){
		plugin.getConfig().set("Developer-Join", true);
		plugin.saveConfig();	
	}
	if (plugin.getConfig().getString("WPC") != null){return;}
		plugin.getConfig().set("WPC.Enabled", true);
		plugin.getConfig().set("Auto-Update", true);
		plugin.saveConfig();	
	}
	
	
}

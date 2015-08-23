package me.wpc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import me.wpc.main;
import me.wpc.methods.WPCUtili;
import me.wpc.methods.WPortalFile;

public class addWarpPortalPriceCommand implements CommandExecutor {

	static main plugin;
	public addWarpPortalPriceCommand(main i){
	plugin = i;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

	if (sender.hasPermission("wpc.add.commands") == false){ WPCUtili.smsg(sender, "&cYou do not have permission to use this command.");return false;}	
	
	//awpp
	if (args.length == 0){
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Incorrect Usage: &c/awpp <warp-portal-name> <price>");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;	
	}
	
	//awpp <warp name>
	if (args.length == 1){
	if (WPortalFile.getConfig().getString("portals." + args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist."); return false;}	
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&4Usage: &c/awpp <warp-portal-name> <price> ");
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	return true;
	}
	
	//awpp <warp name> <price>
	if (args.length == 2){
	if (WPortalFile.getConfig().getString("portals." + args[0]) == null){WPCUtili.smsg(sender, "&cSorry warp portal &4" + args[0] + " &cdoes not exist."); return false;}	
	try {
	plugin.getConfig().set(args[0] + ".price", Double.parseDouble(args[2]));
	plugin.saveConfig();
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	WPCUtili.smsg(sender, "&bPrice &c$" + args[2] + " &bset to warp portal: &c" + args[0]);
	WPCUtili.smsg(sender, "&d-----------------------------------------");
	}
	catch (NumberFormatException e){
		
	}
	}
	
	return false;
	}

}

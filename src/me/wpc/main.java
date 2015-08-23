package me.wpc;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import me.wpc.commands.addWarpPortalCommand;
import me.wpc.commands.addWarpPortalPriceCommand;
import me.wpc.commands.delWarpPortalCommand;
import me.wpc.commands.listWarpPortalCommand;
import me.wpc.commands.warpPortalCommand;
import me.wpc.events.WPCPortalEnterEvent;
import me.wpc.methods.WPCUtili;
import me.wpc.methods.WPortalFile;
import net.gravitydevelopment.updater.Updater;
import net.gravitydevelopment.updater.Updater.UpdateType;
import net.milkbowl.vault.economy.Economy;

public class main extends JavaPlugin implements Listener {

	public static Economy econ;
	
	@SuppressWarnings("unused")
	public void onEnable(){
	//Classes
	new WPCUtili (this);	
	new WPortalFile(this);	
	setupEconomy();
	
	//Configuration files	
	reloadConfig();		
	WPCUtili.generateWPCConfiguration();
	
	//Events	
	Bukkit.getServer().getPluginManager().registerEvents(new WPCPortalEnterEvent (this), this);
	
	//Commands
	getCommand("set-warp-portal-command").setExecutor(new addWarpPortalCommand (this));
	getCommand("set-warp-portal-price").setExecutor(new addWarpPortalPriceCommand (this));
	getCommand("del-warp-portal-command").setExecutor(new delWarpPortalCommand (this));
	getCommand("list-warp-portal-command").setExecutor(new listWarpPortalCommand (this));
	getCommand("wpc").setExecutor(new warpPortalCommand (this));
	
	if (getConfig().getBoolean("Auto-Update") == false){
	Updater updater = new Updater(this, 92528, getFile(), UpdateType.NO_DOWNLOAD, true);
	}
	else {
	Updater updater = new Updater(this, 92528, getFile(), UpdateType.NO_VERSION_CHECK, false);	
	}
	
	wpExists();
	
	}
	
	
	public void onDisable(){

	}
	
	public void wpExists(){
	if (Bukkit.getServer().getPluginManager().getPlugin("WarpPortals") != null){
	String pn = Bukkit.getServer().getPluginManager().getPlugin("WarpPortals").getName();
	getLogger().info("WarpPortals found.");
	getLogger().info("WarpPortalCommands hooked into " + pn + ".");	
	}
	else {
	String pn = Bukkit.getServer().getPluginManager().getPlugin("WarpPortals").getName();
	getLogger().info(pn + " not found.");
	}
	}
	
    private boolean setupEconomy() {
    if (getServer().getPluginManager().getPlugin("Vault") == null) {
    return false;
    }
    RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
    if (rsp == null) {
    return false;
    }
    econ = rsp.getProvider();
    return econ != null;
    }


}

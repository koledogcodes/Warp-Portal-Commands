package me.wpc.methods;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import me.wpc.main;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class WPortalFile {

	
	static main plugin;
	public WPortalFile(main i){
	plugin = i;
	}
	
	private static FileConfiguration customConfig = null;
	private static File customConfigFile = null;
	 
	public static void reloadConfig() {
	if (customConfigFile == null) {
		
	if (new File("plugins\\WarpPortals").exists()){
	customConfigFile = new File("plugins\\WarpPortals", "portals.yml");	
	}
	else {
	customConfigFile = new File("plugins//WarpPortals", "portals.yml");	
	}
	 
	}
	customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	 
	InputStream defConfigStream = plugin.getResource("portals.yml");
	if (defConfigStream != null) {
	@SuppressWarnings("deprecation")
	YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	customConfig.setDefaults(defConfig);
	}
	}
	 
	public static FileConfiguration getConfig() {
	if (customConfig == null) {
	reloadConfig(); 
	}
	return customConfig;
	}
	 
	public static void saveConfig() {
	if (customConfig == null || customConfigFile == null) {
	return;
	}
	try {
	getConfig().save(customConfigFile);
	} catch (IOException ex) {
	plugin.getLogger().log(Level.SEVERE, "Could not save config to " + customConfigFile, ex);
	}
	}
	 
}

package me.wpc.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.mccraftaholics.warpportals.api.WarpPortalsEvent;

import me.wpc.main;
import me.wpc.methods.WPCUtili;

public class WPCPortalEnterEvent implements Listener {

	static main plugin;
	public WPCPortalEnterEvent (main i){
	plugin = i;
	}
	
	@EventHandler
	public void onDeveloperJoin(PlayerJoinEvent e){
	Player player = e.getPlayer();
	if (player.getName().equalsIgnoreCase("_KoleNinja_")){
	if (plugin.getConfig().getBoolean("Developer-Join")){
	WPCUtili.smsg(player, "&aThis server has &cWarp-Portal-Commands &a" + plugin.getDescription().getVersion() + "v.");
	}
	}
	}
	
	@SuppressWarnings({ "rawtypes", "deprecation" })
	@EventHandler
	public void onWPCPortalEnterEvent(WarpPortalsEvent e){
	Player player = e.getPlayer();
	
	if (plugin.getConfig().getString(e.getPortal().name + ".price") != null){
	if (plugin.getConfig().getDouble(e.getPortal().name + ".price") <= main.econ.getBalance(player.getName())){
	main.econ.withdrawPlayer(player.getName(), plugin.getConfig().getDouble(e.getPortal().name + ".price"));	
	WPCUtili.smsg(player, "&a$" + plugin.getConfig().getDouble(e.getPortal().name + ".price") + " has been taken from your balance.");
	}
	else {
	e.setCancelled(true);
	WPCUtili.smsg(player, "&cYou need &4$" + (plugin.getConfig().getDouble(e.getPortal().name + ".price") - main.econ.getBalance(player.getName())) + " &cto use this portal.");
	return;
	}
	}
	
	if (plugin.getConfig().getString(e.getPortal().name + ".commands") == null){
	return;
	}
	
	if (plugin.getConfig().getBoolean(e.getPortal().name + ".teleport") == false){
	e.setCancelled(true);
	}
	
	HashMap<CommandSender, List<String>> commandList = new HashMap<CommandSender,List<String>>();
	HashMap<CommandSender, Iterator> loopCommands = new HashMap<CommandSender,Iterator>();
	
	commandList.put(player, plugin.getConfig().getStringList(e.getPortal().name + ".commands"));
	
	if (plugin.getConfig().getBoolean(e.getPortal().name + ".console-only")){
	
	loopCommands.put(player, commandList.get(player).iterator());	
	while (loopCommands.get(player).hasNext()){
	Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), loopCommands.get(player).next().toString().replace("%player", player.getName()) );	
	}
	
	}
	else {
			
	loopCommands.put(player, commandList.get(player).iterator());	
	while (loopCommands.get(player).hasNext()){
	Bukkit.getServer().dispatchCommand(player, loopCommands.get(player).next().toString().replace("%player", player.getName()) );	
	}
	}
	
	}
	
	
}

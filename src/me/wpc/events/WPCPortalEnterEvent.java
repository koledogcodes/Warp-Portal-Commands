package me.wpc.events;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import me.wpc.main;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.mccraftaholics.warpportals.api.WarpPortalsEvent;

public class WPCPortalEnterEvent implements Listener {

	static main plugin;
	public WPCPortalEnterEvent (main i){
	plugin = i;
	}
	
	@SuppressWarnings("rawtypes")
	@EventHandler
	public void onWPCPortalEnterEvent(WarpPortalsEvent e){
	Player player = e.getPlayer();
	String portal = e.getPortal().name;
	
	if (plugin.getConfig().getString(portal + ".commands") == null){
	return;
	}
	
	if (plugin.getConfig().getBoolean(portal + ".teleport") == false){
	e.setCancelled(true);
	}
	
	HashMap<CommandSender, List<String>> commandList = new HashMap<CommandSender,List<String>>();
	HashMap<CommandSender, Iterator> loopCommands = new HashMap<CommandSender,Iterator>();
	
	commandList.put(player, plugin.getConfig().getStringList(portal + ".commands"));
	
	if (plugin.getConfig().getBoolean(portal + ".console-only")){
	
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

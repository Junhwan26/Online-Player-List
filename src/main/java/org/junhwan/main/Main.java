package org.junhwan.main;


import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import org.junhwan.main.Events.EventsClass;

public class Main extends JavaPlugin implements Listener{

	
	@Override
	public void onEnable() {
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN+"[Plugin On] Online Player List");
		getServer().getPluginManager().registerEvents(new EventsClass(), this);
		
	}
	
	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED+"[Plugin Off] Online Player List");
	}

	
}


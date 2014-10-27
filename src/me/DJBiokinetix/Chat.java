package me.DJBiokinetix;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Chat extends JavaPlugin{
	
	public Logger l = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable(){
		l.info("Plugin enabled!");
	}
	public void onDisable(){
		saveDefaultConfig();
		l.info("Plugin disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		String string1 = getConfig().getString("Message").replaceAll("&", "§");
		String prefix = getConfig().getString("Prefix").replaceAll("&", "§");
		String linesmsg = getConfig().getString("Lines");
		String BukkitMSG = getConfig().getString("CMessage").replaceAll("&", "§");
		
		int lines = getConfig().getInt("Lines");
		
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("cc") || cmd.getName().equalsIgnoreCase("clearchat") || cmd.getName().equalsIgnoreCase("cchat")){
			if(args.length == 0){
			if(p.hasPermission("cc.clear")){
				for(int i = 0; i < lines; i++){
					Bukkit.broadcastMessage(BukkitMSG);
					}
				Bukkit.broadcastMessage(prefix + " " + p.getName() + " " + ChatColor.GRAY + string1);
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
				}
			} else if (args.length == 1){
				if (args[0].equalsIgnoreCase("reload")){
					if(p.hasPermission("cc.reload")){
					reloadConfig();
					p.sendMessage(ChatColor.GREEN + "Configuration has been reloaded!");
					} else {
						sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
					}
				} else if (args.length == 1){
					if(args[0].equalsIgnoreCase("lines")){
						if(p.hasPermission("cc.lines")){
							p.sendMessage(ChatColor.RED  + "Lines: " + linesmsg);
						} else {
							sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
						}
					} else if (args.length == 1){
						if(args[0].equalsIgnoreCase("help")){
							p.sendMessage(ChatColor.DARK_GRAY + "===============================");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/cc help - View this menu!");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/cc lines - View the lines of messages!");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/cc reload - Reload the config files.");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.RED + "/cc - Clear the chat!");
							p.sendMessage(ChatColor.DARK_GRAY + "===============================");
						}
					}
				}
			}
		}
		return false;
	}
}
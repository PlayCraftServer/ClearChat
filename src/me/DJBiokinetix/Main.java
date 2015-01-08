/*
 *  This document is authored from DJBiokinetix (Sergio Armando Casares Valero).
 *  Copyright (c) All Rights Reserved.
 */

package me.DJBiokinetix;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public static final Logger l = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable(){
		l.log(Level.INFO, "Plugin enabled!");
	}
	public void onDisable(){
		saveDefaultConfig();
		l.log(Level.INFO, "Plugin disabled!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		String string1 = getConfig().getString("Message").replaceAll("&", "§");
		String prefix = getConfig().getString("Prefix").replaceAll("&", "§");
		String linesmsg = getConfig().getString("Lines");
		String BukkitMSG = getConfig().getString("CMessage").replaceAll("&", "§");
		
		int lines = getConfig().getInt("Lines");
		
		Player p = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("cc") || cmd.getName().equalsIgnoreCase("clearchat") || cmd.getName().equalsIgnoreCase("cchat")) {
			if(args.length == 0) {
				if(p.hasPermission("cc.clear")) {
					for(int i = 0; i < lines; i++) {
						Bukkit.broadcastMessage(BukkitMSG);
						}
					Bukkit.broadcastMessage(prefix + " " + p.getName() + " " + ChatColor.GRAY + string1);
				} else {
					sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
				}
			} else if (args.length == 1){
				if (args[0].equalsIgnoreCase("reload")) {
					if (p.hasPermission("cc.reload")) {
						reloadConfig();
						p.sendMessage(ChatColor.GREEN + "Configuration has been reloaded!");
					} else {
						sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
					}
				} else if (args.length == 1) {
					if (args[0].equalsIgnoreCase("lines")) {
						if (p.hasPermission("cc.lines")) {
							p.sendMessage(ChatColor.RED  + "Lines: " + linesmsg);
						} else {
							sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
						}
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("help")) {
							p.sendMessage(ChatColor.DARK_GRAY + "===============================");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/cc help - This menu.");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/cc lines - Lines of message.");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/cc reload - Reload config files.");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.GRAY + "/cc personal - Clear personal chat.");
							p.sendMessage(ChatColor.GREEN + "Command: " + ChatColor.RED + "/cc - Clear all chat.");
							p.sendMessage(ChatColor.DARK_GRAY + "===============================");
						} else if (args.length == 1) {
							if (args[0].equalsIgnoreCase("personal")) {
								if (p.hasPermission("cc.personal")) {
									for(int i = 0;
											i < lines; 
											i++){
										p.sendMessage("");
									}
									p.sendMessage(prefix + " " + ChatColor.RED + "You cleaned your chat!");
								} else {
									sender.sendMessage(ChatColor.RED + "You don't have permissions for this!");
								}
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		String web = ChatColor.GOLD + "http://www.spigotmc.org/resources/clearchat.1362/";
		String oriprefix = ChatColor.DARK_GRAY + "[" + ChatColor.GOLD + "ClearChat" + ChatColor.DARK_GRAY + "]" + ChatColor.GRAY + " ";
		if (p.isOp() && p.hasPermission("cc.updates")) {
			p.sendMessage(ChatColor.DARK_GRAY + "===============================");
			p.sendMessage(oriprefix + "Check more updates in:" + " " + web);
			p.sendMessage(ChatColor.DARK_GRAY + "===============================");
		} else {
			p.sendMessage(oriprefix + "This server is now protected for: ClearChat v" + getDescription().getVersion());
			return;
		}
		
		if (p.getName().contains("DJBiokinetix")) {
			p.sendMessage(ChatColor.DARK_GRAY + "================================");
			p.sendMessage(oriprefix + ChatColor.RED + ChatColor.BOLD + "¡Este servidor usa tu plugin!" + " " + oriprefix);
			p.sendMessage(ChatColor.DARK_GRAY + "================================");
		}
		
	}
	
}
package io.github.brettcox.minecraftminigames;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MinecraftMinigamesCommandExecutor implements CommandExecutor {
	private final MinecraftMinigames plugin;
	 
	public MinecraftMinigamesCommandExecutor(MinecraftMinigames plugin) {
		this.plugin = plugin; // Store the plugin in situations where you need it.
	}
 
//	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		if (sender instanceof Player) {
//	           Player player = (Player) sender;
//	           // do something
//	        } else {
//	           sender.sendMessage("You must be a player!");
//	           return false;
//	        }
//	        // do something
//	        return false;
        if (args.length > 1) {
	           sender.sendMessage("Too many arguments!");
	           return false;
        } 
//	        Player target = (Bukkit.getServer().getPlayer(args[0]));
//	        if (target == null) {
//	           sender.sendMessage(args[0] + " is not online!");
//	           return false;
//	        }
		if (cmd.getName().equalsIgnoreCase("basic")) { // If the player typed /basic then do the following...
			// do something...
			Player player = (Player) sender;
			Location loc = player.getLocation();
			loc.setY(loc.getY() + 5);
			Block b = loc.getBlock();
			b.setType(Material.STONE);
			return true;
		} else if (cmd.getName().equalsIgnoreCase("basic2")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("This command can only be run by a player.");
				
			} else {
				Player player = (Player) sender;
				Location playerloc = player.getLocation();
				Location newloc = playerloc;
				Block b = newloc.getBlock();
				for (int i = 0; i < 3; ++i) {
					newloc.setX(newloc.getX() + 3);
					b = newloc.getBlock();
					b.setType(Material.STONE);
				}
			}
			return true;
		}
		return false;
	}
}

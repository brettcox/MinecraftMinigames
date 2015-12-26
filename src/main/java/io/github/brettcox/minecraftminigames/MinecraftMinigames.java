package io.github.brettcox.minecraftminigames;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public final class MinecraftMinigames extends JavaPlugin{
	@Override
	public void onEnable() {
//		this.getCommand("basic").setExecutor(new MinecraftMinigamesCommandExecutor(this));
//		getLogger().info("onEnable has been invoked!");
//		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
//		    playerList.put(player.getName(), playerData(player));
//		}
	}
	
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");

	}
	
//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//		return false;
//	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
	    // Get the player's location.
	    Location loc = event.getPlayer().getLocation();
	    // Sets loc to five above where it used to be. Note that this doesn't change the player's position.
	    loc.setY(loc.getY() + 5);
	    // Gets the block at the new location.
	    Block b = loc.getBlock();
	    // Sets the block to type id 1 (stone).
	    b.setType(Material.STONE);
	}
	
	public void generateCube(Location loc, int length) {
	    // Set one corner of the cube to the given location.
	    // Uses getBlockN() instead of getN() to avoid casting to an int later.
	    int x1 = loc.getBlockX(); 
	    int y1 = loc.getBlockY();
	    int z1 = loc.getBlockZ();
	 
	    // Figure out the opposite corner of the cube by taking the corner and adding length to all coordinates.
	    int x2 = x1 + length;
	    int y2 = y1 + length;
	    int z2 = z1 + length;
	 
	    World world = loc.getWorld();
	 
	    // Loop over the cube in the x dimension.
	    for (int xPoint = x1; xPoint <= x2; xPoint++) { 
	        // Loop over the cube in the y dimension.
	        for (int yPoint = y1; yPoint <= y2; yPoint++) {
	            // Loop over the cube in the z dimension.
	            for (int zPoint = z1; zPoint <= z2; zPoint++) {
	                // Get the block that we are currently looping over.
	                Block currentBlock = world.getBlockAt(xPoint, yPoint, zPoint);
	                // Set the block to type 57 (Diamond block!)
	                currentBlock.setType(Material.DIAMOND_BLOCK);
	            }
	        }
	    }
	}
	
	public void onPlayerJoin(PlayerJoinEvent evt) {
	    Player player = evt.getPlayer(); // The player who joined
	    PlayerInventory inventory = player.getInventory(); // The player's inventory
	    ItemStack itemstack = new ItemStack(Material.DIAMOND, 64); // A stack of diamonds
	 
	    if (inventory.contains(itemstack)) {
	        inventory.addItem(itemstack); // Adds a stack of diamonds to the player's inventory
	        player.sendMessage("Welcome! You seem to be reeeally rich, so we gave you some more diamonds!");
	    }
	}
	
	public void setMetadata(Metadatable object, String key, Object value, Plugin plugin) {
		  object.setMetadata(key, new FixedMetadataValue(plugin,value));
		}
		 
	public Object getMetadata(Metadatable object, String key, Plugin plugin) {
	  List<MetadataValue> values = object.getMetadata(key);  
	  for (MetadataValue value : values) {
	     // Plugins are singleton objects, so using == is safe here
	     if (value.getOwningPlugin() == plugin) {
	        return value.value();
	     }
	  }
	  return null;
	}
}

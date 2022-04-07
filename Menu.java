package xyz.sxmuray.aquatags.tools;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import xyz.sxmuray.aquatags.Main;

public abstract class Menu implements Listener {
	
	public Inventory inv;
	private int taskID;
	
	public Menu(String name, int rows) {
		try {
			this.inv = Bukkit.createInventory(null, 9 * rows, ChatColor.translateAlternateColorCodes('&', name));
		} catch(Exception ex) {
			Utils.getLoggs("&cHas Been Ocurred A Error. Please Contact A Admin The Config Is Invalid >> &f" + ex, true);
		}
		
	    Main.getInstance().getServer().getPluginManager().registerEvents(this, Main.getInstance());
	}
	
	public void setItem(int i, ItemStack stack) { 
		this.inv.setItem(i, stack); 
	}
	
	public Inventory getInventory() { 
		return this.inv; 
	}
	
	public void open(Player p) {
		try {
			p.openInventory(this.inv);
			
			BukkitScheduler sh = Bukkit.getServer().getScheduler();
			taskID = sh.scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
				@Override
				public void run() {
					if(!onUpdate(p)) {
						sh.cancelTask(taskID);
						return;
					}
				}
			}, 0L, 20L);
		} catch(Exception ex) {
			Utils.getLoggs("&cHas Been Ocurred A Error. Please Contact A Admin The Config Is Invalid >> &f" + ex, true);
		}
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (event.getInventory().equals(getInventory()) && event.getCurrentItem() != null && getInventory().contains(event.getCurrentItem()) && event.getWhoClicked() instanceof Player) {
			onClick((Player)event.getWhoClicked(), event.getCurrentItem(), event.getSlot());
			event.setCancelled(true);
	    } 
	}
	
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent event) {
		if (event.getInventory().equals(getInventory()) && event.getPlayer() instanceof Player)
			onClose((Player)event.getPlayer()); 
	}
	  
	public void onClose(Player player) {}
	  
	public abstract void onClick(Player p, ItemStack item, int slot);
	  
	protected abstract boolean onUpdate(Player p);

}

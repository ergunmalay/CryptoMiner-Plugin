package me.lownzy.cryptominer.Listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PrinterInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            // Check if it's a Money Printer
            if (event.getClickedBlock() != null &&
                    event.getClickedBlock().hasMetadata("Printer") &&
                    event.getClickedBlock().getMetadata("Printer").get(0).asBoolean()) {

                // Cancel opening the anvil inventory
                event.setCancelled(true);

                // Open custom GUI
                openCustomGUI(event.getPlayer());
            }
        }
    }

    private void openCustomGUI(Player player) {
        // Create a new inventory with 3 rows
        Inventory customGUI = Bukkit.createInventory(null, 3 * 9, Component.text("Money Printer GUI"));

        // Collect Money
        ItemStack limeGlassPane = createNamedItem(Material.LIME_STAINED_GLASS_PANE, Component.text("Collect Money").color(NamedTextColor.GREEN));
        customGUI.setItem(11, limeGlassPane);

        // Balance
        ItemStack balancePaper = createNamedItem(Material.PAPER, Component.text("Balance: $0").color(NamedTextColor.GOLD));
        customGUI.setItem(13, balancePaper);

        //Exit
        ItemStack exitItem = createNamedItem(Material.RED_STAINED_GLASS_PANE, Component.text("Exit").color(NamedTextColor.DARK_RED));
        customGUI.setItem(15, exitItem);

        // Open the custom GUI for the player
        player.openInventory(customGUI);

    }

    private ItemStack createNamedItem(Material material, Component displayName) {
        ItemStack item = new ItemStack(material);
        item.editMeta(meta -> meta.displayName(displayName));
        return item;
    }

    // Add an InventoryClickEvent to handle interactions with the custom GUI
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        // Check if the clicked inventory is the custom GUI
        if (event.getView().title().equals(Component.text("Money Printer GUI"))) {
            // Handle clicks or interactions in the custom GUI

            if (event.getRawSlot() == 11) {
                // Clicked lime glass pane, handle "Collect Money"
            } else if (event.getRawSlot() == 13) {
                // Clicked paper, handle balance info
            } else if (event.getRawSlot() == 15) {
                // Clicked red glass pane, close the GUI
                player.closeInventory();
            }

            event.setCancelled(true); // Prevent interaction with the GUI
        }
    }
}

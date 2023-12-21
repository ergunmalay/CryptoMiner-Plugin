package me.lownzy.cryptominer.Blocks;

import me.lownzy.cryptominer.cryptoMiner;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.persistence.PersistentDataType;

public class printerPlace implements Listener {

    @EventHandler(priority = EventPriority.MONITOR)
    private void onBlockPlace(BlockPlaceEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        // Check if the player is holding a money printer
        if (item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(cryptoMiner.getPrinterKey(), PersistentDataType.BYTE)) {
            // Run if it's a money printer
            Component message = Component.text("You placed a Money Printer!").color(NamedTextColor.GOLD);
            player.sendMessage(message);

            //Set CustomTag on the block to identify it from different anvils
            block.setMetadata("Printer", new FixedMetadataValue(cryptoMiner.getInstance(), true));

            return;
        }

        // If it's not a Money Printer, check if it's a normal anvil
        if (block.getType() == Material.ANVIL) {
            // Run if it's a  normal Anvil
            Component message = Component.text("You placed a normal Anvil").color(NamedTextColor.DARK_PURPLE);
            player.sendMessage(message);
        }
    }
}

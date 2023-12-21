package me.lownzy.cryptominer.Commands;

import me.lownzy.cryptominer.cryptoMiner;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import static net.kyori.adventure.text.Component.text;

public class printerGiver implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            // Create Money Printer item
            ItemStack moneyPrinter = createMoneyPrinter();

            // Give Money Printer to the player
            player.getInventory().addItem(moneyPrinter);

            sender.sendMessage((text("You received a printer!").color(NamedTextColor.GREEN)));

            return true;
        } else {
            sender.sendMessage((text("Only players can execute this command!").color(NamedTextColor.RED)));
            return false;
        }
    }

    private ItemStack createMoneyPrinter() {
        ItemStack moneyPrinter = new ItemStack(Material.ANVIL);

        // Set custom name
        ItemMeta meta = moneyPrinter.getItemMeta();
        meta.displayName(text("Money Printer").color(NamedTextColor.GREEN));


        // Set custom attribute to identify the printer
        meta.getPersistentDataContainer().set(cryptoMiner.getPrinterKey(), PersistentDataType.BYTE, (byte) 1);

        moneyPrinter.setItemMeta(meta);
        return moneyPrinter;
    }
}

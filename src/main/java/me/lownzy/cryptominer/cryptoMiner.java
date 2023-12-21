package me.lownzy.cryptominer;

import me.lownzy.cryptominer.Blocks.printerPlace;
import me.lownzy.cryptominer.Commands.printerGiver;
import me.lownzy.cryptominer.Listeners.PrinterInteract;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class cryptoMiner extends JavaPlugin {

    //Instance variable to hold the plugin instance
    private static cryptoMiner instance;

    //Create a NamespacedKey instance (initially null)
    private static NamespacedKey PRINTER_KEY;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        // Initialize NamespacedKey after plugin is enabled
        PRINTER_KEY = new NamespacedKey(instance, "isPrinter");

        getServer().getPluginManager().registerEvents(new printerPlace(), this);
        getCommand("printer").setExecutor(new printerGiver());
        getServer().getPluginManager().registerEvents(new PrinterInteract(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Get the plugin instance
    public static cryptoMiner getInstance() {
        return instance;
    }

    // Get the NamespacedKey instance
    public static NamespacedKey getPrinterKey() {
        return PRINTER_KEY;
    }
}

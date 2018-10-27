package com.cavetale.itemmarker;

import org.bukkit.plugin.java.JavaPlugin;

public final class ItemMarkerPlugin extends JavaPlugin {
    private ItemListener itemListener;
    private ItemMarkerCommand itemMarkerCommand;

    @Override
    public void onEnable() {
        this.itemListener = new ItemListener(this);
        this.itemMarkerCommand = new ItemMarkerCommand(this);
        getCommand("itemmarker").setExecutor(this.itemMarkerCommand);
    }

    @Override
    public void onDisable() {
    }
}

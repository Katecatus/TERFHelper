package dev.kate.terf;

import org.bukkit.plugin.java.JavaPlugin;

public final class TERFHelper extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("attributegc").setExecutor(new dev.kate.terf.commands.AttributeGCCommand());
    }

    @Override
    public void onDisable() {

    }
}

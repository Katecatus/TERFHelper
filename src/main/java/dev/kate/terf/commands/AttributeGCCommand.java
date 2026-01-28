package dev.kate.terf.commands;

import org.bukkit.Bukkit;
import org.bukkit.Registry;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AttributeGCCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!sender.isOp()) {
            sender.sendMessage("§cOnly server operators can use this command.");
            return true;
        }

        Player target;
        if (args.length > 0) {
            target = Bukkit.getPlayer(args[0]);
        } else {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Usage: /attributegc <player>");
                return true;
            }
            target = (Player) sender;
        }

        if (target == null) {
            sender.sendMessage("§cPlayer not found.");
            return true;
        }
        cleanAllAttributes(target);

        sender.sendMessage("§a[GC] Attributes of " + target.getName() + " sanitized and reset to base values.");
        return true;
    }

    private void cleanAllAttributes(Player player) {
        for (Attribute attribute : Registry.ATTRIBUTE) {
            AttributeInstance instance = player.getAttribute(attribute);
            if (instance == null) continue;

            for (AttributeModifier modifier : instance.getModifiers()) {
                instance.removeModifier(modifier);
            }

            instance.setBaseValue(instance.getDefaultValue());
        }
    }
}
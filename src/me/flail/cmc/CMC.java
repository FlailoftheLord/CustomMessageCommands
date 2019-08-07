package me.flail.cmc;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.flail.cmc.tools.Logger;

public class CMC extends JavaPlugin {

	public Server server = getServer();
	public Settings settings;
	public List<String> commands = new LinkedList<>();

	@Override
	public void onEnable() {
		settings = new Settings();
		settings.load();

		registerCommands();
	}

	public void registerCommands() {
		new Logger().console("&aRegistered Custom Message Commands:");

		for (String cmd : settings.file().keySet()) {

			command(cmd, this).setExecutor(this);
			new Logger().console("  &7- &e/" + cmd);
		}

	}

	protected PluginCommand command(String name, Plugin plugin) {
		try {
			Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class,
					Plugin.class);
			constructor.setAccessible(true);

			return constructor.newInstance(name, plugin);
		} catch (Throwable t) {
			return null;
		}

	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		return new CmdListener(label).run(sender);
	}

}

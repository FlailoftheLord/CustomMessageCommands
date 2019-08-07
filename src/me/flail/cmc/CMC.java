package me.flail.cmc;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
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
			PluginCommand command = command(cmd, this);
			command.setDescription("A custom message command.");

			registerCommandToServer(command);
			command.setExecutor(this);

			new Logger().console("  &7- &e/" + cmd);
			commands.add(cmd);
		}

	}



	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		return new CmdListener(command.getName().toLowerCase()).run(sender);
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

	public static void registerCommandToServer(Command command) {
		try {
			serverCommandMap().register("m" + command.getName(), command);

		} catch (Throwable t) {
		}
	}

	private static CommandMap serverCommandMap() {
		try {
			if (Bukkit.getPluginManager() instanceof SimplePluginManager) {
				Field f = SimplePluginManager.class.getDeclaredField("commandMap");
				f.setAccessible(true);
				CommandMap commandMap = (CommandMap) f.get(Bukkit.getPluginManager());

				return commandMap;
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}

		return null;
	}

}

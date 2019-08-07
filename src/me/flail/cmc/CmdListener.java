package me.flail.cmc;

import java.util.List;

import org.bukkit.command.CommandSender;

import me.flail.cmc.tools.Logger;

public class CmdListener extends Logger {

	private String cmdLabel;

	public CmdListener(String cmdLabel) {
		this.cmdLabel = cmdLabel;
	}

	public boolean run(CommandSender sender) {
		if (plugin.commands.contains(cmdLabel.toLowerCase())) {

			List<String> cmdMessage = plugin.settings.file().getList(cmdLabel);
			for (String line : cmdMessage) {

				sender.sendMessage(chat(line));
			}

		}

		return true;
	}

}

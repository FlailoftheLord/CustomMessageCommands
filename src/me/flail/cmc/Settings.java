package me.flail.cmc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.flail.cmc.tools.DataFile;
import me.flail.cmc.tools.Logger;

public class Settings extends Logger {
	private DataFile settings;

	public Settings() {
		settings = new DataFile("Settings.yml");

	}

	public DataFile file() {
		return settings;
	}

	public void load() {
		settings.setHeader(header);

		loadDefaultValues();
	}

	private void loadDefaultValues() {
		Map<String, Object> values = new HashMap<>();
		List<String> list = new ArrayList<>();

		list.add("&aReloaded Custom Message Commands!");
		values.put("cmcreload", list.toArray());

		setValues(settings, values);
	}

	private String header =  "-----------------------------------------------------------------\r\n" +
			"==================================================================#\r\n" +
			"                                                                  #\r\n" +
			"         CustomMessageCommands by FlailoftheLord.                 #\r\n" +
			"         -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-                  #\r\n" +
			"           If you have any Questions or feedback                  #\r\n" +
			"              Join my discord server here:                        #\r\n" +
			"               https://discord.gg/wuxW5PS                         #\r\n" +
			"   ______               __        _____                           #\r\n" +
			"   |       |           /  \\         |        |                    #\r\n" +
			"   |__     |          /____\\        |        |                    #\r\n" +
			"   |       |         /      \\       |        |                    #\r\n" +
			"   |       |_____   /        \\    __|__      |______              #\r\n" +
			"                                                                  #\r\n" +
			"==================================================================#\r\n" +
			"-----------------------------------------------------------------\r\n" +
			"- - - Supports Chat Color codes.\r\n";

	protected void setValues(DataFile file, Map<String, Object> values) {
		for (String key : values.keySet()) {
			if (!file.hasValue(key)) {
				file.setValue(key, values.get(key));
			}
		}
	}

}


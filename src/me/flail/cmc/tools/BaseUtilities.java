package me.flail.cmc.tools;

import java.util.TreeMap;

import org.bukkit.Bukkit;

public class BaseUtilities extends LegacyUtils {

	/**
	 * Grabs the color code which modifies the substring <code>before</code> in the string
	 * <code>string</code>
	 * 
	 * @param string
	 * @param before
	 */
	public String getColor(String string, String before) {
		String first = string.split(before)[0];
		char c = first.charAt(first.lastIndexOf("&") + 1);
		return "&" + c;
	}

	private TreeMap<Integer, String> map = new TreeMap<>();

	public String romanNumeral(int number) {
		if (number == 0) {
			return "0";
		}

		map.clear();
		map.put(i(1000), "M");
		map.put(i(900), "CM");
		map.put(i(500), "D");
		map.put(i(400), "CD");
		map.put(i(100), "C");
		map.put(i(90), "XC");
		map.put(i(50), "L");
		map.put(i(40), "XL");
		map.put(i(10), "X");
		map.put(i(9), "IX");
		map.put(i(5), "V");
		map.put(i(4), "IV");
		map.put(i(1), "I");

		int n = map.floorKey(i(number)).intValue();
		if (number == n) {
			return map.get(i(number));
		}
		return map.get(i(n)) + romanNumeral(number - n);
	}

	private Integer i(int n) {
		return Integer.valueOf(n);
	}

	/**
	 * Methods for retrieving base NMS classes.
	 * 
	 * @author FlailoftheLord
	 */
	public static class Reflection {

		public static Class<?> getClass(String className) {
			return getNMSClass(className);
		}

		protected static Class<?> getNMSClass(String classDef) {
			try {

				return Class.forName(
						"net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "."
								+ classDef);
			} catch (Exception e) {

				e.printStackTrace();
				return null;
			}

		}

	}

}

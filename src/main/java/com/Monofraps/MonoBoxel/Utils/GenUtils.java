package com.Monofraps.MonoBoxel.Utils;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.bukkit.entity.Player;

import com.Monofraps.MonoBoxel.MonoBoxel;


/**
 * Some more or less generic utilities.
 * 
 * @author Monofraps
 * 
 */
public class GenUtils {
	
	/**
	 * Will insert the Boxel prefix, if it was not present in the string.
	 * 
	 * @param boxelName
	 * @param plugin
	 * @return The Boxel name with the prefix.
	 */
	public static String boxelizeName(String boxelName, MonoBoxel plugin) {
	
		if (!boxelName.startsWith(plugin.getBoxelPrefix()))
			boxelName = plugin.getBoxelPrefix() + boxelName;
		
		return boxelName;
	}
	
	/**
	 * Removes the Boxel prefix from the name.
	 * 
	 * @param boxelName
	 * @param plugin
	 * @return The Boxel name without the prefix.
	 */
	public static String deboxelizeName(String boxelName, MonoBoxel plugin) {
	
		if (boxelName.startsWith(plugin.getBoxelPrefix()))
			boxelName = boxelName.substring(plugin.getBoxelPrefix().length());
		
		return boxelName;
	}
	
	/**
	 * Checks if [boxelName] belongs to [player].
	 * 
	 * @param player
	 * @param boxelName
	 * @param plugin
	 * @return true if [player] is the owner of [boxelName], otherwise false
	 */
	public static boolean checkBoxelAffiliation(Player player, String boxelName, MonoBoxel plugin) {
	
		boxelName = deboxelizeName(boxelName, plugin);
		
		if (boxelName.equals(player.getName()))
			return true;
		
		return false;
	}
	
	/**
	 * Saves objects in a binary format.
	 * 
	 * @param obj
	 * @param path
	 * @throws Exception a java exception
	 */
	public static void saveObject(Object obj, String path) throws Exception {
	
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
		oos.writeObject(obj);
		oos.flush();
		oos.close();
	}
	
	/**
	 * Loads objects from a binary format.
	 * 
	 * @param path
	 * @return The loaded object.
	 * @throws Exception a java exception
	 */
	public static Object loadObject(String path) throws Exception {
	
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
		Object result = ois.readObject();
		ois.close();
		return result;
	}
}

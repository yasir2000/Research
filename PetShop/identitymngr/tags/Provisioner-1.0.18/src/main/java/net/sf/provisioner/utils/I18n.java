package net.sf.provisioner.utils;

import java.util.*;
import java.text.*;
import org.apache.log4j.Logger;

/**
 * Internationalization aka I18n
 * Sets the current locale and provides corresponding resources
 *
 * @author     <a href="mailto:martin-m@users.sourceforge.net">Martin Mierse</a>
 * @version $ $
 * @created    Dec 21, 2007
 */
public class I18n {
	private static Locale locale;
	private static ResourceBundle logBundle;
	private static MessageFormat formatter;
	
	/**
	 * Sets the current locale
	 * @param language a valid ISO Language Code. These codes are the lower-case, 
	 * two-letter codes as defined by ISO-639
	 * @param country a valid ISO Country Code. These codes are the upper-case, 
	 * two-letter codes as defined by ISO-3166
	 */
	public static void setLocale(String language, String country) {
		locale = new Locale(language, country);
		
		logBundle = ResourceBundle.getBundle("LogBundle", locale);
		
		formatter = new MessageFormat("");
		formatter.setLocale(locale);
		
		Logger.getLogger("Provisioner").info(getLogString("I18n.locale") + locale.toString());
	}

	/**
	 * Gets a string for the given key from the resource bundle containing the 
	 * log messages
	 * @param key the key for the desired string
	 * @return the string for the given key
	 */
	public static String getLogString(String key) {
		return logBundle.getString(key);
	}

	/**
	 * Creates a compound message using a pattern for the given key from the resource 
	 * bundle containing the log messages
	 * @param key the key for the desired pattern
	 * @param arguments the arguments for the compound message
	 * @return the formatted message
	 */
	public static String getFormattedLogString(String key, Object[] arguments) {
		formatter.applyPattern(getLogString(key));
		return formatter.format(arguments);
	}
}

package edu.tce.cse.obe.configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This Class provides access to config.properties file located
 * in package edu.tce.cse.obe.configuration.
 *
 * Use getProperty(String property) method of base class Properties
 * to access properties.
 *
 */
public class Configuration extends Properties {

	private static final long serialVersionUID = -6664521140580724749L;

	public Configuration() throws FileNotFoundException, IOException{ 
		this.load(Configuration.class.getResourceAsStream("config.properties"));
	}
	
}

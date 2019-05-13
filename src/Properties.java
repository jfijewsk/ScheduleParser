import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Properties {
	
	static Properties instance;
	private static Configuration config;
	
	private Properties() {
		
	}
	
	public static Properties getInstance() {
		if(instance == null) {
			getProp();
			return instance;
		}
		
		else {
			return instance;
		}
	}


	/**
	 * Retrieves the properties file.
	 * @return Configuration
	 */
	private static void getProp() {
		
		 Parameters params = new Parameters();
	
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties()
		        .setFileName("config.properties")
        .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));

		try
		{
		    config = builder.getConfiguration();
		}
		catch(ConfigurationException cex)
		{
		    // loading of the configuration file failed
		}
		
		
	}
	
	
	/**
	 * @return file location of the word document containing the schedule
	 */
	public static String getScheduleFileName() {
		return config.getString("scheduleLocation");

	}
	
	/**
	 * @return file location of pdf containing the door sign templete
	 */
	public static String getDoorSignFileName() {
		return config.getString("doorSignPDFLocation");

	}
	
	/**
	 * @return all trainers 
	 */
	public static List getAllTrainers() {
		return config.getList("allTrainers");

	}
	
	/**
	 * @return all trainers 
	 */
	public static String getDefaultTrainer() {
		return config.getString("trainerName");

	}

    
}

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.PropertiesConfigurationLayout;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;

public class Properties {
	
	static Configuration config;


	/**
	 * Retrieves the properties file.
	 * @return Configuration
	 */
	public static Configuration getProp() {
		
		 Parameters params = new Parameters();

		if (config == null) {
	
		FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
		    new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties()
		        .setFileName("config.properties")
        .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));

		try
		{
		    config = builder.getConfiguration();
		    return config;		    
		}
		catch(ConfigurationException cex)
		{
		    // loading of the configuration file failed
		}
		}
		
		else {
			return config;
		}
		
		return config;
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
		return config.getString("doorSignPDFLocation ");

	}

    
}

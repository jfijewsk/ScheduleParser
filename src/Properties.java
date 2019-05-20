import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JOptionPane;

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
	private static Configuration configNonList;

	private static FileBasedConfigurationBuilder<FileBasedConfiguration> builder;
	private static FileBasedConfigurationBuilder<FileBasedConfiguration> builderNonList;

	
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
	
		builder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
		    .configure(params.properties()
		        .setFileName("config.properties")
        .setListDelimiterHandler(new DefaultListDelimiterHandler(',')));
		
		builderNonList = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
			    .configure(params.properties()
			        .setFileName("config.properties"));

		try
		{
		    config = builder.getConfiguration();
		    configNonList = builderNonList.getConfiguration();

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
	 * @return all available training rooms
	 */
	public static String[] getAllTrainingRooms() {
		List rooms = config.getList("allTrainingRooms");
		Object[] roomArray =  rooms.toArray();
		String[] result = new String[roomArray.length];
		System.arraycopy(roomArray, 0, result, 0, roomArray.length);
		return result;

	}
	
	/**
	 * @return all trainers 
	 */
	public static String getDefaultTrainer() {
		return config.getString("trainerName");

	}
	
	/**
	 * @return Door sign class title
	 */
	public static String getClassTitle(int sessionNum) {
		
		switch (sessionNum) {
		
		case 1 : {
			return configNonList.getString("session1Description");
		}
		
		case 2 : {
			return configNonList.getString("session2Description");
		}
		
		case 3 : {
			return configNonList.getString("session3Description");
		}
		
		case 4 : {
			return configNonList.getString("session4Description");
		}
		
		default : {
			return configNonList.getString("session1Description");

		}
		
		
		}

	}
	
	/**
	 * @return file name location for the name tent pdf 
	 */
	public static String getNameTentFileName() {
		return config.getString("nameTentPDFLocation");

	}
	
	public static String[] getDoorSignSettings() {
		
		String[] result = new String[6];
		result[0] = configNonList.getProperty("doorSignPDFLocation").toString();
		result[1] = configNonList.getProperty("session1Description").toString();
		result[2] = configNonList.getProperty("session2Description").toString();
		result[3] = configNonList.getProperty("session3Description").toString();
		result[4] = configNonList.getProperty("session4Description").toString();
		result[5] = configNonList.getProperty("allTrainingRooms").toString();
		
		return result;
	}
	

	/**
	 * Saves new file location of the word document containing the schedule
	 */
	public static void saveScheduleFileName(String newLocation) {
		config.setProperty("scheduleLocation", newLocation);
		saveList();

	}
	
	/**
	 * Saves new file location of the word document containing the schedule
	 */
	public static void saveDoorSign(String[] values) {
		configNonList.setProperty("doorSignPDFLocation", values[0]);
		configNonList.setProperty("session1Description", values[1]);
		configNonList.setProperty("session2Description", values[2]);
		configNonList.setProperty("session3Description", values[3]);
		configNonList.setProperty("session4Description", values[4]);
		configNonList.setProperty("allTrainingRooms", values[5]);

		saveNonList();

	}
	
	/**
	 * Saves the config file with List values.
	 */
	private static void saveList() {
		try {
			builder.save();
		} catch (ConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Error saving settings. Make sure config.properties is not open.", 
					"Save Settings Failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Saves the config file without listing values by commas.
	 */
	private static void saveNonList() {
		try {
			builderNonList.save();
		} catch (ConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Error saving settings. Make sure config.properties is not open.", 
					"Save Settings Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

    
}

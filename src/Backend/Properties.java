package Backend;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
	private static Configuration configBranches;
	private static Configuration configBranchesNonList;

	private static FileBasedConfigurationBuilder<FileBasedConfiguration> builder;
	private static FileBasedConfigurationBuilder<FileBasedConfiguration> builderNonList;
	private static FileBasedConfigurationBuilder<FileBasedConfiguration> branchLocationBuilder;
	private static FileBasedConfigurationBuilder<FileBasedConfiguration> branchLocationBuilderNonList;


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

		branchLocationBuilder = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
				.configure(params.properties()
						.setFileName("branches.properties")
						.setListDelimiterHandler(new DefaultListDelimiterHandler('/')));

		branchLocationBuilderNonList = new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
				.configure(params.properties()
						.setFileName("branches.properties"));


		// Try/catch block for getting the config.properties file
		try
		{
			config = builder.getConfiguration();
			configNonList = builderNonList.getConfiguration();

		}
		catch(ConfigurationException cex)
		{
			JOptionPane.showMessageDialog(null,
					"Could not load properties file to retrieve setup settings. Make sure the config.properties file is "
							+ "in the root folder of the program.",
							"Error",
							JOptionPane.PLAIN_MESSAGE);
			System.exit(1);
		}


		// Try/catch block for getting the branches.properties file
		try
		{
			configBranches = branchLocationBuilder.getConfiguration();
			configBranchesNonList = branchLocationBuilderNonList.getConfiguration();


		}
		catch(ConfigurationException cex)
		{
			JOptionPane.showMessageDialog(null,
					"Could not load properties file to retrieve setup settings. Make sure the branches.properties file is "
							+ "in the root folder of the program.",
							"Error",
							JOptionPane.PLAIN_MESSAGE);
			System.exit(1);
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
	 * @return branch address
	 */
	public static List getShippingAddressText(String branch, Technician tech) {
		String orginalBranch = branch;
		branch = branch.toLowerCase();
		branch = branch.replaceAll("\\s+","");
		List<String> address = new ArrayList<String>();
		List<Object> rawAddress = configBranches.getList(branch);
		
		if (rawAddress == null) {
			JOptionPane.showMessageDialog(null, "Error retrieving the branch address for the " + orginalBranch 
					+ "\nMake sure that branch is in the \"branches.properties\" file and that it is named \n"
					+ "exactly: " + branch, 
					"Error Retrieving Branch Address", JOptionPane.ERROR_MESSAGE);
		}
		
		address.add(0, "Cummins Allison");
		int i = 0;
		
		for (Object line: rawAddress) {
			address.add((rawAddress.get(i)).toString());
			i++;
		}
		
		address.add(i+1, "Attn: " + tech.getName());
		System.out.println("adding to line: " + (i) + " Size is now: " + address.size());
				
		int size = address.size() ;
		if (size <= 6) {
			for (int j = 6; j > size; j--) {
				address.add("");
			}
			
		}
		

		return address;

	}

	/**
	 * @return branch address
	 */
	public static String getBranchNumber(String branch) {
		String orginalBranch = branch;
		branch = branch.toLowerCase();
		branch = branch.replaceAll("\\s+","");

		if (configBranchesNonList.getString(branch + "BranchNum") == null) {
			JOptionPane.showMessageDialog(null, "Error retrieving the branch number for the " + orginalBranch 
					+ "\nMake sure that branch is in the \"branches.properties\" file and that it is named \n"
					+ "exactly: " + branch, 
					"Error Retrieving Branch Number", JOptionPane.ERROR_MESSAGE);
		}
		return configBranchesNonList.getString(branch + "BranchNum");

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
	 * @return the defualy trainer who is using this software
	 */
	public static String getDefaultTrainer() {
		return config.getString("trainerName");

	}


	/**
	 * @return the default trainers phone ext
	 */
	public static String getDefaultTrainerExt() {
		return config.getString("trainerExt");

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

	/**
	 * @return file name location for the shipping form pdf 
	 */
	public static String getNameShippingFileName() {
		return config.getString("shippingFormPDFLocation");

	}


	/**
	 * @return root location for the shipping forms to be saved at.
	 */
	public static String getShippingSaveLocation() {
		return config.getString("shippingRootSaveLocation");

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

	public static String[] getTechReviewSettings() {

		String[] result = new String[5];
		result[0] = configNonList.getProperty("techReviewSession1Location").toString();
		result[1] = configNonList.getProperty("techReviewSession2Location").toString();
		result[2] = configNonList.getProperty("techReviewSession3Location").toString();
		result[3] = configNonList.getProperty("techReviewSession4Location").toString();
		result[4] = configNonList.getProperty("techReviewRootSaveLocation").toString();


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
	 * Saves new file location of the name tent PDF
	 */
	public static void saveNameTentFileName(String newLocation) {
		config.setProperty("nameTentPDFLocation", newLocation);
		saveNonList();

	}

	/**
	 * Saves new file location of the pdf door sign
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
	 * Saves new file location of the pdfs containing the tech review forms
	 */
	public static void saveTechReviewsPDF(String[] values) {
		configNonList.setProperty("techReviewSession1Location", values[0]);
		configNonList.setProperty("techReviewSession2Location", values[1]);
		configNonList.setProperty("techReviewSession3Location", values[2]);
		configNonList.setProperty("techReviewSession4Location", values[3]);
		configNonList.setProperty("techReviewRootSaveLocation", values[4]);

		saveNonList();

	}

	/**
	 * Saves new file location of the shipping form and shipping form save location
	 */
	public static void saveShippingFormPDF(String[] values) {
		configNonList.setProperty("shippingFormPDFLocation", values[0]);
		configNonList.setProperty("shippingRootSaveLocation", values[1]);


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

package Backend;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.PrintService;
import javax.swing.JOptionPane;

import org.apache.commons.configuration2.Configuration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.printing.PDFPageable;

import GUI.ConfigDoorSignDialog;

/**
 * @author fijewskij
 *
 */
public class PDFEditor {

	final static String DOOR_SIGN = "door sign";
	final static String NAME_TENT = "name tent";
	final static String TECH_REVIEW = "tech review";
	final static String SHIPPING = "shipping form";

	// Door sign pdf named fields
	final static String DOOR_SIGN_TECH_NAME_FIELD_NAME = "Tech Names";
	final static String DOOR_SIGN_TECH_BRANCH_FIELD_NAME = "Tech Branch";
	final static String DOOR_SIGN_CLASS_DATES_FIELD_NAME = "Class Dates";
	final static String DOOR_SIGN_CLASS_NAME_FIELD_NAME = "Class Name";
	final static String DOOR_SIGN_TRAINER_COMBO_NAME = "Trainer";
	final static String DOOR_SIGN_TRAINING_ROOM_COMBO_NAME = "Class Room";

	// Name tent pdf named fields
	final static String NAME_TENT_TRAINEE_NAME = "Trainee Name";
	final static String NAME_TENT_TRAINEE_BRANCH = "Trainee Branch";

	// Shipping form pdf named fields
	final static String SHIPPING_DATE = "dateField";
	final static String SHIPPING_TRAINER_NAME =  "trainersName";
	final static String SHIPPING_TRAINER_EXT = "trainerExt";
	final static String SHIPPING_1_DEPT_NUMBER = "firstPositionDepartmentNum";
	final static String SHIPPING_2_DEPT_NUMBER = "secondPositionDepartmentNum";
	final static String SHIPPING_3_DEPT_NUMBER = "thirdPositionDepartmentNum";
	final static String SHIPPING_1_DESC_CONTENTS = "descriptionOfContents1";
	final static String SHIPPING_2_DESC_CONTENTS = "descriptionOfContents2";
	final static String SHIPPING_3_DESC_CONTENTS = "descriptionOfContents3";
	final static String SHIPPING_4_DESC_CONTENTS = "descriptionOfContents4";
	final static String SHIPPING_5_DESC_CONTENTS = "descriptionOfContents5";
	final static String SHIPPING_COMMERICAL_CHECK = "commercialCheckBox";
	final static String SHIPPING_SHIP_TO_1 = "shipTo1";
	final static String SHIPPING_SHIP_TO_2 = "shipTo2";
	final static String SHIPPING_SHIP_TO_3 = "shipTo3";
	final static String SHIPPING_SHIP_TO_4 = "shipTo4";
	final static String SHIPPING_SHIP_TO_5 = "shipTo5";
	final static String SHIPPING_SHIP_TO_6 = "shipTo6";
	final static String SHIPPING_GROUND_COMM_CHECK = "UPS GROUND COMMERICAL";
	
	// Review form pdf named fields
	final static String REVIEW_TRAINER_NAME = "Trainer";
	final static String REVIEW_DATE = "Today's Date";
	final static String REVIEW_TECH_NAME = "Name";
	final static String REVIEW_HIRE_DATE = "Date of Hire";
	final static String REVIEW_BRANCH_NAME = "Branch Name";
	final static String REVIEW_CLASS_DATE =  "Date of Class";


	private static Properties prop = Properties.getInstance();

	private static boolean errorOccurred = false;

	/**
	 * Opens the pdf to be editted.
	 * @param fileName location of the pdf to load
	 * @return returns the loaded pdf
	 */
	private static PDDocument openPDFFile(String fileName) {

		try {
			File file = new File(fileName);
			PDDocument pdfDocument = PDDocument.load(file);
			return pdfDocument;
		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error opening pdf file.", 
					"Error opening pdf", JOptionPane.ERROR_MESSAGE);
			return null;
		}


	}

	/**
	 * @param fileName location of the pdf to open within Adobe
	 */
	private static void openPDFInAdobe(String fileName) {

		if (Desktop.isDesktopSupported()) {

			try {
				File myFile = new File(fileName);
				Desktop.getDesktop().open(myFile);

			} catch (IOException ex) {
				// no application registered for PDFs
			}
		}
	}


	/**
	 * Lets the user pick a printer.
	 * @returns The printer the user wishes to use.
	 */
	private static PrintService choosePrinter() {
		PrinterJob printJob = PrinterJob.getPrinterJob();
		if(printJob.printDialog()) {
			return printJob.getPrintService();          
		}
		else {
			return null;
		}
	}


	/**
	 * Prints the inputed PDF file.
	 * @param printer the user wishes to use
	 * @param doc the document needing to be printed
	 * @return boolean if the print job was successfully sent to the printer
	 */
	private static boolean printPDF(PrintService printer, PDDocument doc) {

		try {
			PrinterJob job = PrinterJob.getPrinterJob();
			job.setPrintService(printer);

			job.setPageable(new PDFPageable(doc));
			job.print();
			return true;

		}

		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Holy flying squirrels, the print job somehow failed. How the hell did you manage that!?", 
					"Print Failed", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}


	public static void fillDoorSign(String fileName, Class classInfo, 
			Session session, ArrayList<Technician> selectedTechs) {

		errorOccurred = false;

		String selectedTrainingRoom = ConfigDoorSignDialog.askTrainingRoom();

		if (selectedTrainingRoom == null) {
			return;
		}
		PDDocument pdfDocument = openPDFFile(fileName);


		PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();

		String allTechs =""; 
		String allBranches =""; 


		if (acroForm != null)
		{

			PDField techNameField = createPDFField(acroForm, DOOR_SIGN_TECH_NAME_FIELD_NAME, DOOR_SIGN);
			PDField techBranchField = createPDFField(acroForm, DOOR_SIGN_TECH_BRANCH_FIELD_NAME, DOOR_SIGN);
			PDField startDateField = createPDFField(acroForm, DOOR_SIGN_CLASS_DATES_FIELD_NAME, DOOR_SIGN);
			PDField classNameField = createPDFField(acroForm, DOOR_SIGN_CLASS_NAME_FIELD_NAME, DOOR_SIGN);

			PDComboBox trainerCombo = createPDFCombo(acroForm, DOOR_SIGN_TRAINER_COMBO_NAME, DOOR_SIGN);
			PDComboBox trainingRoomCombo = createPDFCombo(acroForm, DOOR_SIGN_TRAINING_ROOM_COMBO_NAME, DOOR_SIGN);

			// If there was an error on retrieving the pdf fields then do not proceed.
			if (errorOccurred) {
				return;
			}

			// Changing the techs names on the pdf
			ArrayList<Technician> enrolledTechs = classInfo.getTechnicans();
			for (Technician t: selectedTechs) {

				allTechs = allTechs.concat(t.getName() +  "\n");
				allBranches = allBranches.concat(formatString(t.getBranch()) +  "\n");


			}

			try {
				// Fill out pdf
				techNameField.setValue(allTechs);
				techBranchField.setValue(allBranches);
				startDateField.setValue(session.getDateRange());
				classNameField.setValue(classInfo.getClassName() + " " + session.getSessionName() 
				+ "\n" + prop.getClassTitle(session.getSessionNumber()));

				List allTrainers = prop.getAllTrainers();
				List<String> allTrainingRooms = Arrays.asList(prop.getAllTrainingRooms());

				trainingRoomCombo.setOptions(allTrainingRooms);
				trainerCombo.setOptions(allTrainers);

				trainingRoomCombo.setValue(selectedTrainingRoom);
				trainerCombo.setValue(prop.getDefaultTrainer());

				pdfDocument.save(fileName);
				pdfDocument.close();

			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error saving pdf. Make sure that the pdf is not already open.", 
						"Error saving pdf", JOptionPane.ERROR_MESSAGE);
			}

			openPDFInAdobe(fileName);

		}


	}

	public static void fillNameTent(ArrayList<Technician> techs) {

		errorOccurred = false;

		String nameTentFileLocation = prop.getNameTentFileName();
		PDDocument pdfDocument = openPDFFile(nameTentFileLocation);

		PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();


		if (acroForm != null)
		{


			PDField techNameField = createPDFField(acroForm, NAME_TENT_TRAINEE_NAME, NAME_TENT);
			PDField techBranchField = createPDFField(acroForm, NAME_TENT_TRAINEE_BRANCH, NAME_TENT );

			// If there was an error on retrieving the pdf fields then do not proceed.
			if (errorOccurred) {
				return;
			}

			try {

				boolean printWasSucessful = true;

				// Select printer
				PrintService printer = choosePrinter();

				for (Technician t : techs) {

					if (printWasSucessful && printer != null) {
						
						
						techBranchField.setValue(formatString(t.getBranch()));
						techNameField.setValue(t.getName());
						
						pdfDocument.save(nameTentFileLocation);
						printWasSucessful = printPDF(printer, pdfDocument);
					}
				}


				pdfDocument.close();
			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error saving pdf. Make sure the pdf is not already open.", 
						"Error saving pdf", JOptionPane.ERROR_MESSAGE);
			}

		}
	}

	public static void fillShippingForms(Class classInfo, 
			Session session, ArrayList<Technician> selectedTechs) {

		String shippingFormFileLocation = prop.getNameShippingFileName();

		PDDocument pdfDocument = openPDFFile(shippingFormFileLocation);

		PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();


		if (acroForm != null)
		{

			PDField dateField = createPDFField(acroForm, SHIPPING_DATE, SHIPPING);
			PDField trainerName = createPDFField(acroForm, SHIPPING_TRAINER_NAME, SHIPPING );
			PDField trainerExt = createPDFField(acroForm, SHIPPING_TRAINER_EXT, SHIPPING);

			PDField deptNum1 = createPDFField(acroForm, SHIPPING_1_DEPT_NUMBER, SHIPPING);
			PDField deptNum2 = createPDFField(acroForm, SHIPPING_2_DEPT_NUMBER, SHIPPING );
			PDField deptNum3 = createPDFField(acroForm, SHIPPING_3_DEPT_NUMBER, SHIPPING);

			PDField desc1 = createPDFField(acroForm, SHIPPING_1_DESC_CONTENTS, SHIPPING );
			PDField desc2 = createPDFField(acroForm, SHIPPING_2_DESC_CONTENTS, SHIPPING );
			PDField desc3 = createPDFField(acroForm, SHIPPING_3_DESC_CONTENTS, SHIPPING );
			PDField desc4 = createPDFField(acroForm, SHIPPING_4_DESC_CONTENTS, SHIPPING );
			PDField desc5 = createPDFField(acroForm, SHIPPING_5_DESC_CONTENTS, SHIPPING );

			PDCheckBox commercialCheckbox = (PDCheckBox) createPDFField(acroForm, SHIPPING_COMMERICAL_CHECK, SHIPPING );
			PDCheckBox groundCommercialShipCheckbox = (PDCheckBox) createPDFField(acroForm, SHIPPING_GROUND_COMM_CHECK, SHIPPING );

			PDField shipTo1 = createPDFField(acroForm, SHIPPING_SHIP_TO_1, SHIPPING );
			PDField shipTo2 = createPDFField(acroForm, SHIPPING_SHIP_TO_2, SHIPPING );
			PDField shipTo3 = createPDFField(acroForm, SHIPPING_SHIP_TO_3, SHIPPING );
			PDField shipTo4 = createPDFField(acroForm, SHIPPING_SHIP_TO_4, SHIPPING );
			PDField shipTo5 = createPDFField(acroForm, SHIPPING_SHIP_TO_5, SHIPPING );
			PDField shipTo6 = createPDFField(acroForm, SHIPPING_SHIP_TO_6, SHIPPING );

			// If there was an error on retrieving the pdf fields then do not proceed.
			if (errorOccurred) {
				return;
			}

			try {

				for (int i = 0; i < selectedTechs.size(); i++) {

					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
					LocalDate localDate = LocalDate.now();

					String branchNum = prop.getBranchNumber(selectedTechs.get(i).getBranch());
					List<String> address = prop.getShippingAddressText(selectedTechs.get(i).branch, selectedTechs.get(i));

					// Fill out pdf
					dateField.setValue(dtf.format(localDate));
					trainerName.setValue(prop.getDefaultTrainer());
					trainerExt.setValue(prop.getDefaultTrainerExt());

					deptNum1.setValue("0");
					deptNum2.setValue(branchNum.substring(0, 1));
					deptNum3.setValue(branchNum.substring(1));

					desc1.setValue("Workbook");

					shipTo1.setValue(address.get(0));
					shipTo2.setValue(address.get(1));
					shipTo3.setValue(address.get(2));
					shipTo4.setValue(address.get(3));
					shipTo5.setValue(address.get(4));
					shipTo6.setValue(address.get(5));		

					commercialCheckbox.check();
					groundCommercialShipCheckbox.check();


					// Save to directory
					String rootSaveFolder = prop.getShippingSaveLocation();
					String yearFolderName = classInfo.getClassYear() + " GTC Classes";
					String className = classInfo.getClassNameWithoutYear();

					String fullFileName = rootSaveFolder + "\\" + yearFolderName + "\\" + className 
							+ "\\" + selectedTechs.get(i).getName() + " " + session.getSessionName() + " Shipping.pdf";

					File form = new File(fullFileName);

					try {

						if (!form.exists()) {
							new File(fullFileName).getParentFile().mkdirs();
							pdfDocument.save(fullFileName);
						}

						else {
							JOptionPane.showMessageDialog(null, "Duplicate PDF Found. Changes NOT saved."
									+ "Duplicate PDF will open after closing this message. Please edit manually to prevent overwritting files. "
									+ "\nDuplicate file is located here: " + fullFileName, 
									"Error review already exists", JOptionPane.ERROR_MESSAGE);
						}

						openPDFInAdobe(fullFileName);

					}

					catch (Exception e) {
						JOptionPane.showMessageDialog(null, "How did you screw this up!? Honestly, I'm not even mad. "
								+ "\n You somehow managed to get this to fail when trying to populate the pdf with new "
								+ "\n values. Make sure the pdf is not already open? I'm really not sure what the fix"
								+ "\n here is. Do NOT buy a lotto ticket today.", 
								"Error saving pdf", JOptionPane.ERROR_MESSAGE);
					}
				}
			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error opening and editting the PDF. For the record, this is never "
						+ "supposed to happen.\nLook outside and make sure pigs are not currently flying.", 
						"Error editting pdf", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			//			openPDFInAdobe(shippingFormFileLocation);

		}
	}
	
	public static void fillTechReviewForms(Class classInfo, 
			Session session, ArrayList<Technician> selectedTechs) {
			
		// Get the correct session review form for that specific session
		int sessionNumber = session.getSessionNumber();
		String reviewFileLocation;
		String[] allReviewLocations = prop.getTechReviewSettings();
		
		switch (sessionNumber) {
		
		case 1 : 
			reviewFileLocation = allReviewLocations[0];
			break;
			
		case 2 :
			reviewFileLocation = allReviewLocations[1];
			break;
			
		case 3: 
			reviewFileLocation = allReviewLocations[2];
			break;
			
		case 4: 
			reviewFileLocation = allReviewLocations[3];
			break;
			
		default :
			JOptionPane.showMessageDialog(null, "Could not identify the session number \"sessionNumber\""
					+ "\nto match it with the correct PDF file."
					 , "Error Identifing Session Number", JOptionPane.ERROR_MESSAGE);
			return;
		}

		System.out.println("Trying to open: " + reviewFileLocation);
		
		PDDocument pdfDocument = openPDFFile(reviewFileLocation);

		PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();
	
		if (acroForm != null)
		{

			PDComboBox trainerName = createPDFCombo(acroForm, REVIEW_TRAINER_NAME, TECH_REVIEW );

			PDField todayDate = createPDFField(acroForm, REVIEW_DATE, TECH_REVIEW);
			PDField techName = createPDFField(acroForm, REVIEW_TECH_NAME, TECH_REVIEW );
			PDField techHireDate = createPDFField(acroForm, REVIEW_HIRE_DATE, TECH_REVIEW);
			PDField branchName = createPDFField(acroForm, REVIEW_BRANCH_NAME, TECH_REVIEW );
			PDField classDate = createPDFField(acroForm, REVIEW_CLASS_DATE, TECH_REVIEW );


			// If there was an error on retrieving the pdf fields then do not proceed.
			if (errorOccurred) {
				return;
			}
			
			try {

				for (int i = 0; i < selectedTechs.size(); i++) {
					
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy");
					LocalDate localDate = LocalDate.now();

					String branchNum = prop.getBranchNumber(selectedTechs.get(i).branch);
					List<String> address = prop.getShippingAddressText(selectedTechs.get(i).branch, selectedTechs.get(i));
					
					trainerName.setOptions(prop.getAllTrainers());
					trainerName.setValue(prop.getDefaultTrainer());
					todayDate.setValue(dtf.format(localDate));
					techName.setValue(selectedTechs.get(i).getName());
					techHireDate.setValue(selectedTechs.get(i).getStartDate());
					branchName.setValue(selectedTechs.get(i).getBranch());
					classDate.setValue(session.getFullStartDate());
					

					// Save to directory
					String rootSaveFolder = allReviewLocations[4];
					String classFolderName = classInfo.getClassYear() + " " + classInfo.getClassNameWithoutYear();
					String fileName = selectedTechs.get(i).getName() + " " + session.getSessionName();

					String fullFileName = rootSaveFolder + "\\" + classFolderName + "\\" + fileName + " Review.pdf";

					File form = new File(fullFileName);
					
					try {

						if (!form.exists()) {
							new File(fullFileName).getParentFile().mkdirs();
							pdfDocument.save(fullFileName);
						}

						else {
							JOptionPane.showMessageDialog(null, "Duplicate PDF Found. Changes NOT saved."
									+ "Duplicate PDF will open after closing this message. Please edit manually to prevent overwritting files. "
									+ "\nDuplicate file is located here: " + fullFileName, 
									"Error review already exists", JOptionPane.ERROR_MESSAGE);
						}

						openPDFInAdobe(fullFileName);

					}

					catch (Exception e) {
						JOptionPane.showMessageDialog(null, "How did you screw this up!? Honestly, I'm not even mad. "
								+ "\n You somehow managed to get this to fail when trying to populate the pdf with new "
								+ "\n values. Make sure the pdf is not already open? I'm really not sure what the fix"
								+ "\n here is. Do NOT buy a lotto ticket today.", 
								"Error saving pdf", JOptionPane.ERROR_MESSAGE);
					
				}
			}
		}
			
			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error opening and editting the PDF. For the record, this is never "
						+ "supposed to happen.\nLook outside and make sure pigs are not currently flying.", 
						"Error editting pdf", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
		}
	}


	private static PDField createPDFField(PDAcroForm acroForm, String fieldName, String pdfName) {
		PDField field = (PDField) acroForm.getField(fieldName);
		if (field == null) {
			JOptionPane.showMessageDialog(null, "Could not find the field named \"" + fieldName
					+ "\" in the "  +pdfName +  " templete pdf", 
					"Error finding text field", JOptionPane.ERROR_MESSAGE);
			errorOccurred = true;
			return null;
		}

		return field;
	}

	private static PDComboBox createPDFCombo(PDAcroForm acroForm, String fieldName, String pdfName) {
		PDComboBox combo = (PDComboBox) acroForm.getField(fieldName);
		if (combo == null) {
			JOptionPane.showMessageDialog(null, "Could not find the field named \"" + fieldName
					+ "\" in the "  +pdfName +  " templete pdf", 
					"Error finding text field", JOptionPane.ERROR_MESSAGE);
			errorOccurred = true;

			return null;
		}

		return combo;
	}
	
	
	/**
	 * @return The String back with only the first letter and any letter after an ' symbol in upper case
	 */
	private static String formatString(String input) {
		
		String[] splitInput = input.split("\\s+|'+");
		
		for(int i = 0; i < splitInput.length; i++) {
			splitInput[i] = splitInput[i].substring(0,1).toUpperCase()
					+ splitInput[i].substring(1).toLowerCase();
		}
		
		return String.join(" ", splitInput);
	}

}

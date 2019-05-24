package Backend;
import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.print.PrintService;
import javax.swing.JOptionPane;

import org.apache.commons.configuration2.Configuration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.printing.PDFPageable;

import GUI.ConfigDoorSignDialog;

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
	final static String SHIPPING_GROUND_COMM_CHECK = "UPS GROUND COMMERICAL";


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
				allBranches = allBranches.concat(t.getBranch() +  "\n");


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

		String techName =""; 
		String techBranch =""; 

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
						techNameField.setValue(t.getName());
						techBranchField.setValue(t.getBranch());

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

		String techName =""; 
		String techBranch =""; 

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
						techNameField.setValue(t.getName());
						techBranchField.setValue(t.getBranch());

						pdfDocument.save(shippingFormFileLocation);
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
}

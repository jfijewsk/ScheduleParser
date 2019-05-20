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

public class PDFEditor {
	
	final static String TECH_NAME_FIELD_NAME = "Tech Names";
	final static String TECH_BRANCH_FIELD_NAME = "Tech Branch";
	final static String CLASS_DATES_FIELD_NAME = "Class Dates";
	final static String CLASS_NAME_FIELD_NAME = "Class Name";
	final static String TRAINER_COMBO_NAME = "Trainer";
	final static String TRAINING_ROOM_COMBO_NAME = "Class Room";

	private static Properties prop = Properties.getInstance();

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
			
			PDField techNameField = createPDFField(acroForm, TECH_NAME_FIELD_NAME);
			PDField techBranchField = createPDFField(acroForm, TECH_BRANCH_FIELD_NAME);
			PDField startDateField = createPDFField(acroForm, CLASS_DATES_FIELD_NAME);
			PDField classNameField = createPDFField(acroForm, CLASS_NAME_FIELD_NAME);
			
			PDComboBox trainerCombo = createPDFCombo(acroForm, TRAINER_COMBO_NAME);
			PDComboBox trainingRoomCombo = createPDFCombo(acroForm, TRAINING_ROOM_COMBO_NAME);
						
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
				JOptionPane.showMessageDialog(null, "Error saving pdf. If there was no additional errors about missing fields in the "
						+ "pdf, make sure that the pdf is not already open.", 
						"Error saving pdf", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}

			openPDFInAdobe(fileName);

		}


	}

	public static void fillNameTent(String fileName, ArrayList<Technician> techs) {
		String nameTentFileLocation = prop.getNameTentFileName();
		PDDocument pdfDocument = openPDFFile(fileName);

		PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();

		String techName =""; 
		String techBranch =""; 

		if (acroForm != null)
		{
			PDField techNameField = (PDField) acroForm.getField( "Trainee Name" );
			PDField techBranchField = (PDField) acroForm.getField( "Trainee Branch" );

			try {

				boolean printWasSucessful = true;
				
				// Select printer
				PrintService printer = choosePrinter();

				for (Technician t : techs) {

					if (printWasSucessful && printer != null) {
						techNameField.setValue(t.getName());
						techBranchField.setValue(t.getBranch());

						pdfDocument.save(fileName);
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
	
	private static PDField createPDFField(PDAcroForm acroForm, String fieldName) {
		PDField field = (PDField) acroForm.getField(fieldName);
		if (field == null) {
			JOptionPane.showMessageDialog(null, "Could not find the field named \"" + fieldName
				+ "\" in the door sign templete pdf", 
					"Error finding text field", JOptionPane.ERROR_MESSAGE);
			return field;
		}
		
		return null;
	}
	
	private static PDComboBox createPDFCombo(PDAcroForm acroForm, String fieldName) {
		PDComboBox combo = (PDComboBox) acroForm.getField(TRAINER_COMBO_NAME);
		if (combo == null) {
			JOptionPane.showMessageDialog(null, "Could not find the field named \"" + fieldName
				+ "\" in the door sign templete pdf", 
					"Error finding text field", JOptionPane.ERROR_MESSAGE);
			return combo;
		}
		
		return null;
	}
}

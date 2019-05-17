import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.commons.configuration2.Configuration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class PDFEditor {

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


	public static void fillDoorSign(String fileName, Class classInfo, 
			Session session, ArrayList<Technician> selectedTechs) {

		PDDocument pdfDocument = openPDFFile(fileName);


		PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();

		String allTechs =""; 
		String allBranches =""; 


		if (acroForm != null)
		{
			PDField techNameField = (PDField) acroForm.getField( "Tech Names" );
			PDField techBranchField = (PDField) acroForm.getField( "Tech Branch" );
			PDField startDateField = (PDField) acroForm.getField( "Class Dates" );
			PDComboBox trainerCombo = (PDComboBox) acroForm.getField( "Trainer" );


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

				List allTrainers = prop.getAllTrainers();
				trainerCombo.setOptions(allTrainers);
				trainerCombo.setValue(prop.getDefaultTrainer());


				pdfDocument.save(fileName);
				pdfDocument.close();

			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error saving pdf. Make sure the pdf is not already open.", 
						"Error saving pdf", JOptionPane.ERROR_MESSAGE);
			}

			openPDFInAdobe(fileName);

		}


	}

	public static void fillNameTent(String fileName, Technician tech) {
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
				techNameField.setValue(tech.getName());
				techBranchField.setValue(tech.getBranch());
				
				pdfDocument.save(fileName);
				pdfDocument.close();
			}

			catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error saving pdf. Make sure the pdf is not already open.", 
						"Error saving pdf", JOptionPane.ERROR_MESSAGE);
			}
			openPDFInAdobe(fileName);

		}
	}
}

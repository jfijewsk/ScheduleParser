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

	public static void fillDoorSign(String fileName, Class classInfo) {
		
		Properties prop = Properties.getInstance();
		
		try {
			
			File file = new File(fileName);
			PDDocument pdfDocument = PDDocument.load(file);
						
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
			    for (int i = 0; i < enrolledTechs.size(); i++) {
			    	
			    	allTechs = allTechs.concat(enrolledTechs.get(i).getName() +  "\n");
			    	allBranches = allBranches.concat(enrolledTechs.get(i).getBranch() +  "\n");

				    
			    }
			    
			    // Fill out pdf
			    techNameField.setValue(allTechs);
			    techBranchField.setValue(allBranches);
			    startDateField.setValue(classInfo.getSessions().get(0).getDateRange());
		    	
			    List allTrainers = prop.getAllTrainers();
			    trainerCombo.setOptions(allTrainers);
			    trainerCombo.setValue(prop.getDefaultTrainer());
			    
			    try {
			    pdfDocument.save(fileName);
			    }
			    
			    catch (FileNotFoundException e) {
			    	JOptionPane.showMessageDialog(null, "Error saving pdf. Make sure the pdf is not already open.", 
			    			"Error saving pdf", JOptionPane.ERROR_MESSAGE);
			    }
			    pdfDocument.close();
			}
			
			if (Desktop.isDesktopSupported()) {
			    try {
			        File myFile = new File(fileName);
			        Desktop.getDesktop().open(myFile);
			    } catch (IOException ex) {
			        // no application registered for PDFs
			    }
			}
			
			
		}
		
		catch (Exception ex) {
			ex.printStackTrace();
	}

	}
}

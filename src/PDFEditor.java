import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.configuration2.Configuration;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDComboBox;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class PDFEditor {

	public static void fillDoorSign(String fileName, Class classInfo) {
		
		try {
			
			Configuration config = Properties.getProp();

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
			    PDComboBox trainer = (PDComboBox) acroForm.getField( "Trainer" );

			    
			    // Changing the techs names on the pdf
			    ArrayList<Technician> enrolledTechs = classInfo.getTechnicans();
			    for (int i = 0; i < enrolledTechs.size(); i++) {
			    	
			    	allTechs = allTechs.concat(enrolledTechs.get(i).getName() +  "\n");
			    	allBranches = allBranches.concat(enrolledTechs.get(i).getBranch() +  "\n");

				    
			    }
			    
			    techNameField.setValue(allTechs);
			    techBranchField.setValue(allBranches);
			    startDateField.setValue(classInfo.getSessions().get(0).getDateRange());
		    	
			    List allTrainers = config.getList("allTrainers");
			    System.out.println(allTrainers.get(1).toString());
			    trainer.setOptions(allTrainers);
			    
			    
			    pdfDocument.save(fileName);
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

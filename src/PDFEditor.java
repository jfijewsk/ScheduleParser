import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class PDFEditor {

	public static void fillDoorSign(String fileName, ArrayList<Class> classes) {
		
		try {
			File file = new File(fileName);
			PDDocument pdfDocument = PDDocument.load(file);
						
			PDDocumentCatalog docCatalog = pdfDocument.getDocumentCatalog();
			PDAcroForm acroForm = docCatalog.getAcroForm();
			
			String allTechs =""; 
			
			if (acroForm != null)
			{
			    PDField field = (PDField) acroForm.getField( "Tech Names" );
			    
			    // Changing the techs names on the pdf
			    ArrayList<Technician> enrolledTechs = classes.get(0).getTechnicans();
			    for (int i = 0; i < enrolledTechs.size(); i++) {
			    	
			    	allTechs = allTechs.concat(enrolledTechs.get(i).getName() +  "\n");
				    
			    }
			    
			    field.setValue(allTechs);
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

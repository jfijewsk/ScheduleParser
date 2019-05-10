import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.BodyElementType;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;



/**
 * @author fijewskij
 * This class parses through the training schedule word file looking for classes
 */
public class WordParser {
	
	public static ArrayList<Class> findClases(String fileName) {
				
	try {
		FileInputStream fis = new FileInputStream(fileName);
		XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
		Iterator bodyElementIterator = xdoc.getBodyElementsIterator();
		IBodyElement element = (IBodyElement) bodyElementIterator.next();
		java.util.List<XWPFParagraph> paragraphList;
		
		ArrayList sessions = new ArrayList<Session>();
		ArrayList classes = new ArrayList<Class>();

		
		XWPFTable table = null;
		String className = null;

		while (bodyElementIterator.hasNext()) {
			
//			System.out.println(element.getElementType().name());
//			System.out.println(element.getClass());

			// If next is a paragraph increase paragraph index
		    element = (IBodyElement) bodyElementIterator.next();
			
		    // Get the class name from the paragraph before the class table. Looks for 
		    // the keyword "Class"
			if ("PARAGRAPH".equalsIgnoreCase(element.getElementType().name())) {
				if (((XWPFParagraph)element).getParagraphText().startsWith("Class") 
						|| ((XWPFParagraph)element).getParagraphText().startsWith("class")) {
					
					
					className = (((XWPFParagraph)element).getParagraphText());
					System.out.println("className: " + className);
					
				}
			}
			//System.out.println(element.getElementType().name());

			if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
				
				
				table = (XWPFTable) element;
				
				  XWPFTableRow[] rows = table.getRows().toArray(new XWPFTableRow[0]);
				  
					for (int i = 0; i < table.getRows().size(); i++) {

						for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
							
							// Find the different sessions and their dates.
							// If statement looks for the key word "session" within the first column of the table.
							if ((table.getRow(i).getCell(j).getText().startsWith("Session")
									|| table.getRow(i).getCell(j).getText().startsWith("session"))
									&& j == 0) {
								
								String sessionName = table.getRow(i).getCell(j).getText();
								System.out.println("sessionName: " + sessionName);
																
								String startDate = table.getRow(i).getCell(j+1).getText();
								System.out.println("startDate: " + startDate);
								
								String endDate = table.getRow(i).getCell(j+2).getText();
								System.out.println("endDate: " + endDate);
								
								Session session = new Session(sessionName, startDate, endDate);
								sessions.add(session);
							}
							
							// Find the Technician Name column
							ArrayList students = new ArrayList<Technician>();
					
							if (table.getRow(i).getCell(j).getText().equals("Technician Name")) {
								// Get all techs
								for(int k = i+1; k < table.getRows().size(); k++) {
								
									// make sure not to add a blank tech
									if (table.getRow(k).getCell(j).getText() != null 
											|| !table.getRow(k).getCell(j).getText().equals("")) {
										
										String name = table.getRow(k).getCell(j).getText();
										String branch = table.getRow(k).getCell(j - 1).getText();
										String startDate = table.getRow(k).getCell(j + 1).getText();
										
										students.add(new Technician(name, branch, startDate));
										System.out.println("Added student: " + table.getRow(k).getCell(j).getText());
									}

								}
								
								Class classGTC = new Class(students, className, sessions);
								classes.add(classGTC);
								System.out.println("");

								//POIXMLDocumentPart className =  prevElement.getPart().getPackagePart().getContentType();
								
							}
							
/*								// Dont print if empty
							if (table.getRow(i).getCell(j).getText() != null || 
									!table.getRow(i).getCell(j).getText().equals("")) {
								System.out.println(table.getRow(i).getCell(j).getText());
								
							}
							*/
						}
					}
				}

			}
		
		return classes;

	} catch (Exception ex) {
		ex.printStackTrace();
	}

	return null;
}
}

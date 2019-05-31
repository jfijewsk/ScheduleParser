package Backend;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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

import GUI.ConfigWordDialog;



/**
 * @author fijewskij
 * This class parses through the training schedule word file looking for classes
 */
public class WordParser {

	public static ArrayList<Class> findGTCClases(String fileName) {

		ArrayList<Class> classes = new ArrayList<Class>();

		try {
			FileInputStream fis = new FileInputStream(fileName);
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			Iterator<?> bodyElementIterator = xdoc.getBodyElementsIterator();
			IBodyElement element = (IBodyElement) bodyElementIterator.next();
			java.util.List<XWPFParagraph> paragraphList;
			
			

			XWPFTable table = null;
			String className = null;

			while (bodyElementIterator.hasNext()) {

				// If next is a paragraph increase paragraph index
				element = (IBodyElement) bodyElementIterator.next();


				// Get the class name from the paragraph before the class table. Looks for 
				// the keyword "Class"
				if ("PARAGRAPH".equalsIgnoreCase(element.getElementType().name())) {
					if (((XWPFParagraph)element).getParagraphText().startsWith("Class") 
							|| ((XWPFParagraph)element).getParagraphText().startsWith("class")) {


						className = (((XWPFParagraph)element).getParagraphText());

					}
				}

				if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {

					table = (XWPFTable) element;

					XWPFTableRow[] rows = table.getRows().toArray(new XWPFTableRow[0]);

					ArrayList<Session> sessions = new ArrayList<Session>();

					// If the table does not have the keyword "Session" in it, skip it						
					if (checkIfGTCClass(table) == true) {
						for (int i = 0; i < table.getRows().size(); i++) {

							for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {

								// Find the different sessions and their dates.
								// If statement looks for the key word "session" within the first column of the table.
								if ((table.getRow(i).getCell(j).getText().startsWith("Session")
										|| table.getRow(i).getCell(j).getText().startsWith("session"))
										&& j == 0) {

									String sessionName = table.getRow(i).getCell(j).getText();

									String startDate = table.getRow(i).getCell(j+1).getText();

									String endDate = table.getRow(i).getCell(j+2).getText();

									Session session = new Session(sessionName, startDate, endDate);
									sessions.add(session);
								}

								// Find the Technician Name column
								ArrayList<Technician> students = new ArrayList<Technician>();

								if (table.getRow(i).getCell(j).getText().equals("Technician Name")) {
									// Get all techs
									for(int l = i+1; l < table.getRows().size(); l++) {

										// make sure not to add a blank tech
										if ( !table.getRow(l).getCell(j).getTextRecursively().isEmpty()) {
										
							
											String name = table.getRow(l).getCell(j).getText();
											String branch = table.getRow(l).getCell(j - 1).getText();
											String startDate = table.getRow(l).getCell(j + 1).getText();

											System.out.println("(word parser) Adding tech: " + name);
											students.add(new Technician(name, branch, startDate));

										}

									}

									Class classGTC = new Class(students, className, sessions);
									classes.add(classGTC);

								}
							}

						}

					} 
				} 

			}

			fis.close();
			xdoc.close();
			return classes;
		} 

		catch (Exception ex) {
			Properties prop = Properties.getInstance();
			JOptionPane.showMessageDialog(null,
					"Invaild file to load the schedule. Please pick the correct file.",
					"Error",
					JOptionPane.PLAIN_MESSAGE);

			JFrame frame = new JFrame("");
			frame.setLocationRelativeTo(null);
			ConfigWordDialog wordDiag = new ConfigWordDialog(frame , false);
			wordDiag.setLocationRelativeTo(frame);
			wordDiag.setVisible(true);




		}
		return classes;

	}

	public static boolean checkIfGTCClass(XWPFTable table ) {
		for (int k = 0; k < table.getRows().size(); k++) {

			for (int j = 0; j < table.getRow(k).getTableCells().size(); j++) {
				if ((table.getRow(k).getCell(j).getText().startsWith("Session")
						|| table.getRow(k).getCell(j).getText().startsWith("session"))){
					return true;
				}
			}
		}

		return false;
	}
}

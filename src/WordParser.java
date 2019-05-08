import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;

/**
 * @author fijewskij
 * This class parses through the training schedule word file looking for classes
 */
public class WordParser {
	
	public static void findClases(String fileName) {
				
	int counter = 0;
	try {
		FileInputStream fis = new FileInputStream("Test_Schedule.docx");
		XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
		Iterator bodyElementIterator = xdoc.getBodyElementsIterator();
		while (bodyElementIterator.hasNext()) {
			IBodyElement element = (IBodyElement) bodyElementIterator.next();
			
			//System.out.println(element.getElementType().name());

			if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
				java.util.List<XWPFTable> tableList =  element.getBody().getTables();
				
				System.out.println(tableList.size());

				
				for (XWPFTable table : tableList) {
					
		
					for (int i = 0; i < table.getRows().size(); i++) {

						for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
							
							if (table.getRow(i).getCell(j).getText().equals("Branch Name & #")) {
								counter++;
							}
							
/*								// Dont print if empty
							if (table.getRow(i).getCell(j).getText() != null || 
									!table.getRow(i).getCell(j).getText().equals("")) {
								System.out.println(table.getRow(i).getCell(j).getText());
								
							}*/
						}
					}
				}
				
				break;
			}
		}
		System.out.println(counter);


	} catch (Exception ex) {
		ex.printStackTrace();
	}
}
}

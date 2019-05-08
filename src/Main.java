import java.io.FileInputStream;
import java.util.Iterator;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;

public class Main {

	public static void main(String[] args) {
		try {
			FileInputStream fis = new FileInputStream("Training_2019_Schedule.docx");
			XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
			Iterator bodyElementIterator = xdoc.getBodyElementsIterator();
			while (bodyElementIterator.hasNext()) {
				IBodyElement element = (IBodyElement) bodyElementIterator.next();

				if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
					java.util.List<XWPFTable> tableList =  element.getBody().getTables();
					for (XWPFTable table : tableList) {
						System.out.println("Total Number of Rows of Table:" + table.getNumberOfRows());
						for (int i = 0; i < table.getRows().size(); i++) {
							System.out.println("");

							for (int j = 0; j < table.getRow(i).getTableCells().size(); j++) {
								
								// Dont print if empty
								if (table.getRow(i).getCell(j).getText() != null || 
										!table.getRow(i).getCell(j).getText().equals("")) {
									System.out.println(table.getRow(i).getCell(j).getText());
								}
							}
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}

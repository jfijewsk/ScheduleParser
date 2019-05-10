import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;

public class Main {

	public static void main(String[] args) {
		
		String fileName = "Test_Schedule_new.docx";
		String doorSignFileName = "Training Door Sign Auto-Fill.pdf";
		
		ArrayList classes = new ArrayList<Class>();
		
		System.out.println("Reading from " + fileName);

		classes = WordParser.findClases(fileName);
		
		PDFEditor.fillDoorSign(doorSignFileName, classes);
	}

}

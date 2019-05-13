import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.commons.configuration2.Configuration;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;

public class Main {

	public static void main(String[] args) {
		
		// init properties file
		Properties.getProp();
		
//		String fileName = Properties.getScheduleFileName();
//		String doorSignFileName = Properties.getDoorSignFileName();
		
//		ArrayList classes = new ArrayList<Class>();
			
//		classes = WordParser.findClases(fileName);
		
//		PDFEditor.fillDoorSign(doorSignFileName, (Class)classes.get(0));
		
		GUI gui = new GUI();
		
	
		
		
	}

}

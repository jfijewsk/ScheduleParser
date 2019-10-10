package Backend;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.commons.configuration2.Configuration;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument.List;

import GUI.GUI;

public class Main {

	public static void main(String[] args) {
		
		// init properties file
		Properties prop = Properties.getInstance();
		
//		String fileName = prop.getScheduleFileName();
//		String doorSignFileName = prop.getDoorSignFileName();
		
//		ArrayList classes = new ArrayList<Class>();
			
//		classes = WordParser.findClases(prop.getScheduleFileName());
		

			GUI gui = new GUI();
			gui.setLocationRelativeTo(null);
		
//		PDFEditor.fillDoorSign(doorSignFileName, (Class)classes.get(0));
		


		
	
		
		
	}

}

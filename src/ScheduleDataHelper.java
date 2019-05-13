import java.util.ArrayList;

/**
 * This class retrieves the class information from the WordParser and manipulates and extracts data.
 * It acts as a helper class for the raw data.
 * @author fijewskij
 *
 */
public class ScheduleDataHelper {

	String fileName = Properties.getScheduleFileName();
	String doorSignFileName = Properties.getDoorSignFileName();
				
	public static String[] getAllGTCClasses() {
		ArrayList<Class> classes = WordParser.findClases(Properties.getScheduleFileName());
		String[] GTCclasses = new String[classes.size()];
		
		for(int i = 0; i < classes.size(); i ++) {
			GTCclasses[i] = classes.get(i).getClassName();
			
		}
		
		return GTCclasses;
	}
	
	public static String[] getAllSessions() {
		return null;
	}
}

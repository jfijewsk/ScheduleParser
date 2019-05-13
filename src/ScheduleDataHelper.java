import java.util.ArrayList;

/**
 * This class retrieves the class information from the WordParser and manipulates and extracts data.
 * It acts as a helper class for the raw data.
 * @author fijewskij
 *
 */
public class ScheduleDataHelper {

	static Properties prop = Properties.getInstance();
	
	String fileName = prop.getScheduleFileName();
	String doorSignFileName = prop.getDoorSignFileName();
				
	
	public static Class[] getAllGTCClasses() {
		ArrayList<Class> classes = WordParser.findClases(prop.getScheduleFileName());
		
//		System.out.println("SCHEDULED class shows " + classes.get(0).getSessions().get(0).getSessionName());
		Class[] GTCclasses = new Class[classes.size()];
		
		for(int i = 0; i < classes.size(); i ++) {
			GTCclasses[i] = classes.get(i);
			
		}
		
		return GTCclasses;
	}
	
	
	public static Session[] getAllSessions(Class inputClass) {
		
		System.out.println("Found class with: " + inputClass.getSessions().size()  +   " sessions");
		Session[] GTCsessions = new Session[inputClass.getSessions().size()];
		
		for(int i = 0; i < inputClass.getSessions().size(); i ++) {
			GTCsessions[i] = inputClass.getSessions().get(i);
			
		}
		return GTCsessions;
	}
}

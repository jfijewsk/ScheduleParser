package Backend;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author fijewskij
 * Class to contain class objects.
 */
public class Class {

	private ArrayList<Technician> technicans;
	private ArrayList<Session> sessions;

	private String startDate;
	private String endDate;
	private String className;

	// Non-GTC class constructor 
	public Class(ArrayList<Technician> technicans, String startDate, String endDate, String className) {
		this.technicans = technicans;
		this.startDate = startDate;
		this.endDate = endDate;
		this.className = className;
	}


	// GTC class constructor
	public Class(ArrayList<Technician> technicans, String className, ArrayList<Session> sessions) {
		this.technicans = technicans;
		this.sessions = sessions;
		this.className = className;
	}
	

	public ArrayList<Technician> getTechnicans() {
		return technicans;
	}


	public ArrayList<Session> getSessions() {
		return sessions;
	}


	public String getStartDate() {
		return startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public String getClassName() {
		return className;
	}
	
	
	/**
	 * @return Returns just the 4 digit year in the class name
	 */
	public String getClassYear() {
		Pattern pattern = Pattern.compile("(\\d{4})");
		Matcher matcher = pattern.matcher(className);
		String result = null;
		if (matcher.find()) {        
		    result = matcher.group(0);  // 4 digit number
		}
		
		System.out.println("Class year = " + result);
		return result;
	}
	
	/**
	 * @return Returns the class name without the year
	 */
	public String getClassNameWithoutYear() {
		Pattern pattern = Pattern.compile("(\\d{4})");
		Matcher matcher = pattern.matcher(className);
		String year = "";
		if (matcher.find()) {        
			year = matcher.group(0);  // 4 digit number
		}
		
		String result = className.replace(" " + year,"");
		return result;
	}
	

	
	@Override
	public String toString() {
		return className;
	}
	

	
}





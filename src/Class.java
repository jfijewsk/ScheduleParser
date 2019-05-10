import java.util.ArrayList;

/**
 * @author fijewskij
 * Class to contain class objects.
 */
public class Class {

	ArrayList technicans = new ArrayList<String>();
	ArrayList sessions = new ArrayList<Session>();

	String startDate;
	String endDate;
	String className;

	// Non-GTC class constructor 
	public Class(ArrayList technicans, String startDate, String endDate, String className) {
		this.technicans = technicans;
		this.startDate = startDate;
		this.endDate = endDate;
		this.className = className;
	}


	// GTC class constructor
	public Class(ArrayList technicans,String className, ArrayList sessions) {
		this.technicans = technicans;
		this.sessions = sessions;
	}
	

	public ArrayList getTechnicans() {
		return technicans;
	}


	public ArrayList getSessions() {
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
	
	

	
}





import java.util.ArrayList;

/**
 * @author fijewskij
 * Class to contain class objects.
 */
public class Class {

	private ArrayList technicans = new ArrayList<String>();
	private ArrayList<Session> sessions = new ArrayList<Session>();

	private String startDate;
	private String endDate;
	private String className;

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
		this.className = className;
	}
	

	public ArrayList getTechnicans() {
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
	
	@Override
	public String toString() {
		return className;
	}
	
	

	
}





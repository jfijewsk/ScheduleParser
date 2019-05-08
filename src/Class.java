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
	}
	

	// GTC class constructor
	public Class(ArrayList technicans,String className, ArrayList sessions) {
		this.technicans = technicans;
		this.sessions = sessions;
	}
	
	/**
	 * @author fijewskij
	 * Internal class to hold the different sessions of GTC Classes
	 */
	public class Session{
		String sessionName;
		String startDate;
		String endDate;
		
		public Session(String sessionName, String startDate, String endDate) {
			this.sessionName = sessionName;
			this.startDate = startDate;
			this.endDate = endDate;
		}
	}
	
}





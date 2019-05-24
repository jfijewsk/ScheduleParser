package Backend;
	
/**
 * @author fijewskij
 * Class to hold the different sessions of GTC Classes.
 */
public class Session{
	String sessionName;
	String startDate;
	String endDate;
	int sessionNumber;

	public Session(String sessionName, String startDate, String endDate) {
		this.sessionName = sessionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sessionNumber = getSessionNumber();
	}
	

	public int getSessionNumber() {
		return Integer.parseInt(sessionName.replaceAll("[\\D]", ""));
	}
	
	public String getSessionName() {
		return sessionName;
	}

	public String getFullStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	public String getDateRange() {
		String[] startDateDashSplit = startDate.split("/");
		String[] endDateDashSplit = endDate.split("/");

		return startDateDashSplit[0] + "/" + startDateDashSplit[1] 
				+ " - " + endDateDashSplit[0] + "/" + endDateDashSplit[1];
	}
	
	@Override
	public String toString() {
		return sessionName;
	}
}
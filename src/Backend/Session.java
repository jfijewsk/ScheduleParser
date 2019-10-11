package Backend;

import javax.swing.JOptionPane;

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
		String result = "";
		
		try {
		String[] startDateDashSplit = startDate.split("/");
		String[] endDateDashSplit = endDate.split("/");

		result = startDateDashSplit[0] + "/" + startDateDashSplit[1] 
				+ " - " + endDateDashSplit[0] + "/" + endDateDashSplit[1];
		 
		}
		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Error getting the session's date from the training schedule Word document. \n"
					+ "Please enter in the date field manually.",
					"Error",
					JOptionPane.PLAIN_MESSAGE);
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		return sessionName;
	}
}

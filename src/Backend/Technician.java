package Backend;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Technician {

	String name;
	String branch;
	String misc;
	
	public Technician(String name, String branch, String misc) {
		this.name = name;
		this.branch = branch;
		this.misc = misc;
	}

	public String getName() {
		return name;
	}

	public String getBranch() {
		return branch;
	}

	public String getMisc() {
		return misc;
	}
	
	public String getStartDate() {
		 Pattern pattern = Pattern.compile( "^\\d{2}-\\d{2}-\\d{4}$");
	        Matcher matcher = pattern.matcher(misc);

	        String result = null;
	        if (matcher.find()) {
	            result = (matcher.group());
	        }
	        
	        return result;
	}
	
	
}

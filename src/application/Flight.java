package application;

import java.io.Serializable;
import java.util.ArrayList;

public class Flight implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3072541613388524574L;
	private String loginName;
	private String password;
	private String flightLetter;
	ArrayList<Recruiter> flightList;
	
	public Flight(String loginName, String password) {
		this.loginName = loginName;
		this.password = password;
		flightLetter = "G";
		flightList = new ArrayList<>();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<Recruiter> getFlightList() {
		return flightList;
	}

	public void setFlightList(ArrayList<Recruiter> flightList) {
		this.flightList = flightList;
	}
	
	public String toString() {
		return loginName + " " + password;
	}

}

package application;

import java.util.ArrayList;

public class Applicant {
	
	
	private String firstName;
	private String lastName;
	private String ssn;
	private String depDate;
	private String availabilityDate;
	private ArrayList<Job> jobList;
	
	public Applicant(String firstName, String lastName, String ssn, String depDate, String availabilityDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.depDate = depDate;
		this.availabilityDate = availabilityDate;
		jobList = new ArrayList<>();
	}
	
	public ArrayList<Job> getJobList(){
		return jobList;
	}
	

}

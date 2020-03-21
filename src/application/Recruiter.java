package application;

import java.util.ArrayList;

public class Recruiter {
	
	private String firstName;
	private String lastName;
	private String ricCode;
	private ArrayList<Applicant> qw;
	private int goal;
	private int numJobsBooked;
	
	public Recruiter(String firstName, String lastName, String ricCode) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.ricCode = ricCode;
		qw = new ArrayList<>();
	}
	
	public ArrayList<Applicant> getQw(){
		return qw;
	}
	
	public void setGoal(int goal) {
		this.goal = goal;
	}
	
	public int getGoal() {
		return goal;
	}
	
	public boolean setNumJobsBooked(int numJobsBooked) {
		this.numJobsBooked = numJobsBooked;
		return madeGoal();
	}
	
	private boolean madeGoal() {
		return numJobsBooked >= goal;
	}

}

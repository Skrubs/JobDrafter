package application;

public class Job {
	
	private String jobName;
	private String eadDate;
	
	public Job(String jobName, String eadDate) {
		this.jobName = jobName;
		this.eadDate = eadDate;
	}
	
	public String getJobName() {
		return jobName;
	}
	
	public String getEadDate() {
		return eadDate;
	}
	
	@Override
	public String toString() {
		return jobName + " " + eadDate;
	}

}

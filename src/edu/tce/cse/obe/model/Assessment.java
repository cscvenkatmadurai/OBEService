package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="assessment")
public class Assessment {

	private String assessmentID;
	private String assessmentName;
	private String assessmentType;
	
	public Assessment(){
		
	}
	
	public Assessment(String assessmentID, String assessmentName,
			String assessmentType) {
		super();
		this.assessmentID = assessmentID;
		this.assessmentName = assessmentName;
		this.assessmentType = assessmentType;
	}
	
	@XmlElement(name="assessmentID")
	public String getAssessmentID() {
		return assessmentID;
	}
	public void setAssessmentID(String assessmentID) {
		this.assessmentID = assessmentID;
	}
	@XmlElement(name="assessmentName")
	public String getAssessmentName() {
		return assessmentName;
	}
	public void setAssessmentName(String assessmentName) {
		this.assessmentName = assessmentName;
	}
	@XmlElement(name="assessmentType")
	public String getAssessmentType() {
		return assessmentType;
	}
	public void setAssessmentType(String assessmentType) {
		this.assessmentType = assessmentType;
	}	
}

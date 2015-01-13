package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="program")
public class Program {

	private String programID;
	private String programName;
	private String departmentID;


	public Program(){
		
	}
	
	public Program(String programID, String programName, String departmentID) {
		super();
		this.programID = programID;
		this.programName = programName;
		this.departmentID = departmentID;
	}
	
	@XmlElement(name="programID")
	public String getProgramID() {
		return programID;
	}
	public void setProgramID(String programID) {
		this.programID = programID;
	}
	
	@XmlElement(name="programName")
	public String getName() {
		return programName;
	}
	public void setName(String programName) {
		this.programName = programName;
	}
	
	@XmlElement(name="departmnetID", required=true)
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
}
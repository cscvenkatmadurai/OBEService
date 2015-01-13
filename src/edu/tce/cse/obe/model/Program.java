package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="program")
public class Program {

	private String programID;
	private String name;
	private String departmentID;


	public Program(){
		
	}
	
	public Program(String programID, String name, String departmentID) {
		super();
		this.programID = programID;
		this.name = name;
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
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@XmlElement(name="departmnetID", required=true)
	public String getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(String departmentID) {
		this.departmentID = departmentID;
	}
}
package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="department")
public class Department {
	
	private String departmentID;	
	private String departmentName;
	
	public Department(){
		
	}
	
	public Department(String departmentID, String departmentName) {
		super();
		this.departmentID = departmentID;
		this.departmentName = departmentName;
	}
	
	
	@XmlElement(name="departmentID")
	public String getId() {
		return this.departmentID;
	}
	public void setId(String departmentID) {
		this.departmentID = departmentID;
	}
	
	@XmlElement(name="departmentName")
	public String getName() {
		return this.departmentName;
	}
	public void setName(String departmentName) {
		this.departmentName = departmentName;
	}

}

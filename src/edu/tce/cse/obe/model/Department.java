package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="department")
public class Department {
	
	private String departmentID;	
	private String departmentName;
	private int year;
	
	public Department(){
		
	}
	
	public Department(String departmentID, String departmentName,int year) {
		super();
		this.departmentID = departmentID;
		this.departmentName = departmentName;
		this.year = year;
	}
	
	
	@XmlElement(name="departmentID", required = true)
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

	@XmlElement(name="year", required = true)
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}

package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="department")
public class Department {
	
	private String id;	
	private String name;
	
	@XmlElement(name="departmentID")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@XmlElement(name="departmentName")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}

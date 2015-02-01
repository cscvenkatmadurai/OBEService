package edu.tce.cse.obe.model;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="COPOMapping")

public class COPOMapping {

	private String coID;
	private String courseID;
	private int year;
	@XmlElement(name="courseID")
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	@XmlElement(name="coID")
	public String getCoID() {
		return coID;
	}
	public void setCoID(String coID) {
		this.coID = coID;
	}@XmlElement(name="year")
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	@XmlElementWrapper(name="POMappings")
	@XmlElement(name="POMapping")
	private List<COPOMappingElement> list;
	public List<COPOMappingElement> getMappingList() {
		return this.list;
	}

	public void setMappingList(List<COPOMappingElement> mappingList) {
		this.list = mappingList;
	}
	
}
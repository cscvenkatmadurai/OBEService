package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="CourseOutcome")
public class CourseOutcome {
		
	private String coID;
	private String coName;
	private String courseID;
	private int year;
	
	public CourseOutcome(){
		
	}
	
	public CourseOutcome(String coID, String coName, String courseID, int year) {
		super();
		this.coID = coID;
		this.coName = coName;
		this.courseID = courseID;
		this.year = year;
	}
	
	@XmlElement(name="coID")
	public String getCoID() {
		return coID;
	}
	public void setCoID(String coID) {
		this.coID = coID;
	}
	
	@XmlElement(name="coName")
	public String getCoName() {
		return coName;
	}
	public void setCoName(String coName) {
		this.coName = coName;
	}
	
	@XmlElement(name="courseID")
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	@XmlElement(name="year")
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coID == null) ? 0 : coID.hashCode());
		result = prime * result + ((coName == null) ? 0 : coName.hashCode());
		result = prime * result
				+ ((courseID == null) ? 0 : courseID.hashCode());
		result = prime * result + year;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CourseOutcome other = (CourseOutcome) obj;
		if (coID == null) {
			if (other.coID != null)
				return false;
		} else if (!coID.equals(other.coID))
			return false;
		if (coName == null) {
			if (other.coName != null)
				return false;
		} else if (!coName.equals(other.coName))
			return false;
		if (courseID == null) {
			if (other.courseID != null)
				return false;
		} else if (!courseID.equals(other.courseID))
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	
}
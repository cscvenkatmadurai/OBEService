package edu.tce.cse.obe.model;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="course")

public class Course {

	private String courseID;
	private String courseName;
	private int numOfStudents;
	private int year;
	public Course()
	{
		
	}
	public Course(String courseId, String courseName, int num, int year)
	{
		this.courseID=courseId;
		this.courseName=courseName;
		this.numOfStudents=num;
		this.year=year;
	}
	@XmlElement(name="courseID", required = true)
	public String getCourseID() {
		return courseID;
	}
	public void setCourseID(String id) {
		this.courseID = id;
	}
	@XmlElement(name="courseName")
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String name) {
		this.courseName = name;
	}
    @XmlElement(name="numOfStudents")
    public int getCount()
    {
    	return numOfStudents;
    }
    public void setCount(int num){
    	this.numOfStudents=num;
    }
    @XmlElement(name="year", required = true)
    public void setYear(int year){
    	this.year=year;
    }
    public int getYear(){
    	return year;
    }
}
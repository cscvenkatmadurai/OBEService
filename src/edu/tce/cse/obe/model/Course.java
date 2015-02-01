package edu.tce.cse.obe.model;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="Course")

public class Course {

	private String id;
	private String name;
	private int numOfStudents;
	private int year;
	
	@XmlElement(name="CourseId")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@XmlElement(name="CourseName")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
    @XmlElement(name="NumOfStudents")
    public int getCount()
    {
    	return numOfStudents;
    }
    public void setCount(int num){
    	this.numOfStudents=num;
    }
    @XmlElement(name="year")
    public void setYear(int year){
    	this.year=year;
    }
    public int getYear(){
    	return year;
    }
}


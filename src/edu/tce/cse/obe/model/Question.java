package edu.tce.cse.obe.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="question")
public class Question {

	private int id;
	private String courseID;
	private String assessmentID;
	private String questionNo;
	private String questionStatement;
	private int rightAnswerCount;
	private int totalAnswerCount;
	private String section;
	private int year;
	private List<String> COList;
	
	public Question(){
		
	}
	

	public Question(int id, String courseID, String assessmentID,
			String questionNo, String questionStatement, int rightAnswerCount,
			int totalAnswerCount, String section, int year, List<String> cOList) {
		super();
		this.id = id;
		this.courseID = courseID;
		this.assessmentID = assessmentID;
		this.questionNo = questionNo;
		this.questionStatement = questionStatement;
		this.rightAnswerCount = rightAnswerCount;
		this.totalAnswerCount = totalAnswerCount;
		this.section = section;
		this.year = year;
		COList = cOList;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement(name="courseID")
	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	@XmlElement(name="assessmentID")
	public String getAssessmentID() {
		return assessmentID;
	}

	public void setAssessmentID(String assessmentID) {
		this.assessmentID = assessmentID;
	}

	@XmlElement(name="questionNo")
	public String getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	@XmlElement(name="questionStatement")
	public String getQuestionStatement() {
		return questionStatement;
	}

	public void setQuestionStatement(String questionStatement) {
		this.questionStatement = questionStatement;
	}

	@XmlElement(name="rightAnswerCount")
	public int getRightAnswerCount() {
		return rightAnswerCount;
	}

	public void setRightAnswerCount(int rightAnswerCount) {
		this.rightAnswerCount = rightAnswerCount;
	}

	@XmlElement(name="totalAnswerCount")
	public int getTotalAnswerCount() {
		return totalAnswerCount;
	}

	public void setTotalAnswerCount(int totalAnswerCount) {
		this.totalAnswerCount = totalAnswerCount;
	}

	@XmlElement(name="section")
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	@XmlElement(name="year")
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@XmlElementWrapper(name = "COList") 
	@XmlElement(name="co")
	public List<String> getCOList() {
		return COList;
	}

	public void setCOList(List<String> cOList) {
		COList = cOList;
	}
	
}

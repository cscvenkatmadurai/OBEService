package edu.tce.cse.obe.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="question")
public class Question {

	private int id;
	private String questionNo;
	private String questionStatement;
	private String courseId;
	private String assesmentId;
	private int noOfRightAnswer;
	private int totalNoAnswer;
	private String section;
	private int year;
	private List<String> coList;
	
	
	
	public Question(){
		
	}
	

	public Question(int id, String questionNo, String questionStatement,
			String courseId, String assesmentId, int noOfRightAnswer,
			int totalNoAnswer, String section, int year) {
		super();
		this.id = id;
		this.questionNo = questionNo;
		this.questionStatement = questionStatement;
		this.courseId = courseId;
		this.assesmentId = assesmentId;
		this.noOfRightAnswer = noOfRightAnswer;
		this.totalNoAnswer = totalNoAnswer;
		this.section = section;
		this.year = year;
		this.coList = null;
	}
	
	@XmlElement(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

	@XmlElement(name="courseId")
	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	@XmlElement(name="assesmentId")
	public String getAssesmentId() {
		return assesmentId;
	}

	
	public void setAssesmentId(String assesmentId) {
		this.assesmentId = assesmentId;
	}

	@XmlElement(name="totalAnswer")
	public int getTotalNoAnswer() {
		return totalNoAnswer;
	}

	public void setTotalNoAnswer(int totalNoAnswer) {
		this.totalNoAnswer = totalNoAnswer;
	}

	@XmlElement(name="rightAnswer")
	public int getNoOfRightAnswer() {
		return noOfRightAnswer;
	}

	public void setNoOfRightAnswer(int noOfRightAnswer) {
		this.noOfRightAnswer = noOfRightAnswer;
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

	@XmlElement(name="coList")
	public List<String> getCoList() {
		return coList;
	}


	public void setCoList(List<String> coList) {
		this.coList = coList;
	}



		
}

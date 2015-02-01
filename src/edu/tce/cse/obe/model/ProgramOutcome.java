package edu.tce.cse.obe.model;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ProgramOutcome")

public class ProgramOutcome {
	private String poID;
	private String poName;
	private float poAttainment;
	private int year;
	private String programID;
	public ProgramOutcome(){
		
	}
	public ProgramOutcome(String poID, String poName, String programID, int year) {
		super();
		this.poID = poID;
		this.poName = poName;
		this.programID = programID;
		this.year = year;
	}
	
	@XmlElement(name="poID")
	public String getPoID() {
		return poID;
	}
	public void setPoID(String id) {
		this.poID = id;
	}
	@XmlElement(name="poName")
	public String getPoName() {
		return poName;
	}
	public void setPoName(String name) {
		this.poName = name;
	}
    @XmlElement(name="poAttainment")
    public float getPoAttainment()
    {
    	return poAttainment;
    }
    public void setPoAttainment(float num){
    	this.poAttainment=num;
    }
    @XmlElement(name="year", required = true)
    public void setYear(int year){
    	this.year=year;
    }
    public int getYear(){
    	return year;
    }
    @XmlElement(name="porgramID", required = true)
	public String getProgramID() {
		return programID;
	}
	public void setProgramID(String programID) {
		this.programID = programID;
	}

}



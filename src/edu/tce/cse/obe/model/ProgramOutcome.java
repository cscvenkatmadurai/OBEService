package edu.tce.cse.obe.model;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="PO")

public class ProgramOutcome {
	private String po_id;
	private String po_name;
	private float po_attainment;
	private int year;
	private String progrmaID;
	
	@XmlElement(name="PO_Id")
	public String getId() {
		return po_id;
	}
	public void setId(String id) {
		this.po_id = id;
	}
	@XmlElement(name="PO_Name")
	public String getName() {
		return po_name;
	}
	public void setName(String name) {
		this.po_name = name;
	}
    @XmlElement(name="PO_Attainment")
    public float getAttainment()
    {
    	return po_attainment;
    }
    public void setAttainment(float num){
    	this.po_attainment=num;
    }
    @XmlElement(name="year")
    public void setYear(int year){
    	this.year=year;
    }
    public int getYear(){
    	return year;
    }
	public String getProgrmaID() {
		return progrmaID;
	}
	public void setProgrmaID(String progrmaID) {
		this.progrmaID = progrmaID;
	}

}



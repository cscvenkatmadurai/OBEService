package edu.tce.cse.obe.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="POMapping")
public class COPOMappingElement {
	
	private String poID;
	private String value;
	@XmlElement(name="poID")
	public String getPoID() {
		return poID;
	}
	public void setPoID(String poID) {
		this.poID = poID;
	}
	@XmlElement(name="value")
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}

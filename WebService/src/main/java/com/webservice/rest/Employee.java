package com.webservice.rest;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@XmlRootElement(name = "employee")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee implements Serializable {

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", designation=" + designation + ", dob=" + dob + ", gendar="
				+ gendar + ", status=" + status + ", address=" + address + "]";
	}

	private static final long serialVersionUID = -5027069363654489786L;
	
	private int id;
	private String name;
	private String designation;
	private Date dob;
	private String gendar;
	private String status;
	private String address;
	
	public Employee(){}
	
	public Employee(int id, String name,String designation,Date dob,String gendar,String status,String address){
		this.id=id;
		this.name=name;
		this.designation=designation;
		this.dob=dob;
		this.gendar=gendar;
		this.status=status;
		this.address=address;
	}
	public Employee(String name,String designation,Date dob,String gendar,String status,String address){
		this.name=name;
		this.designation=designation;
		this.dob=dob;
		this.gendar=gendar;
		this.status=status;
		this.address=address;
	}
	
	public Employee(int id) {
		this.id=id;
	}

	public String getDesignation() {
		return designation;
	}

	@XmlElement
	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Date getDob() {
		return dob;
	}

	@XmlElement
	@XmlSchemaType(name="date")
	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGendar() {
		return gendar;
	}

	@XmlElement
	public void setGendar(String gendar) {
		this.gendar = gendar;
	}

	public String getStatus() {
		return status;
	}

	@XmlElement
	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	@XmlElement
	public void setAddress(String address) {
		this.address = address;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Employee other = (Employee) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

}

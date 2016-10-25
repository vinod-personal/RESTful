package com.webservice.restclient;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.bean.ManagedBean;


import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.webservice.rest.Emp;
import com.webservice.rest.Employee;

public class EmployeeBean {
	
	static final String getEmpDtlURI="http://localhost:8089/WebService/rest/empService/employee";
	static final String createEmpURI="http://localhost:8089/WebService/rest/empService/createEmp";
	static final String updateEmpURI="http://localhost:8089/WebService/rest/empService/updateEmp";
	static final String deleteEmpURI="http://localhost:8089/WebService/rest/empService/deleteEmp";
	
	private List<Emp> empList=new ArrayList<Emp>();
	
	public List<Emp> empList() {
		if(empList==null)
		{
			empList=new ArrayList<Emp>();
			loadEmployee();
		}
		return empList;
	}

	public void setEmpList(List<Emp> empList) {
		this.empList = empList;
	}

	public void init(){}
	
	public void loadEmployee()
	{
		System.out.println("::: Started loading employee");
		List<Employee> empl = new ArrayList<Employee>();
		RestTemplate rt = new RestTemplate();
		Gson gson = new Gson();
		String response =rt.getForObject(getEmpDtlURI, String.class);
		JsonParser ppar = new JsonParser();
		JsonObject obj = (JsonObject) ppar.parse(response);
		JsonArray array = obj.getAsJsonArray("employee");
		Type collectionType = new TypeToken<Collection<Employee>>() {}.getType();
		empl = gson.fromJson(array,  collectionType);
		for(Employee emp : empl)
		{
			Emp e = new Emp();
			e.setId(emp.getId());
			e.setName(emp.getName());
			e.setDesignation(emp.getDesignation());
			//e.setDob(emp.getDob());
			e.setGendar(emp.getGendar());
			e.setStatus(emp.getStatus());
			e.setAddress(emp.getAddress());
			empList.add(e);
		}
		System.out.println(":::: response size ::::"+response.toString());
	}
	
	public void addEmployee()
	{
		
	}
	
	public void updateEmployee()
	{
		
	}
	
	public void deleteEmployee()
	{
		
	}

}

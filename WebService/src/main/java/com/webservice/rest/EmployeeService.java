package com.webservice.rest;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Path("/empService")
public class EmployeeService {
	
	 EmployeeDao employeedao = new EmployeeDao();
	 private static final String SUCCESS_RESULT="<result>success</result>";
	  private static final String FAILURE_RESULT="<result>failure</result>";
	
	   @GET
	   @Path("/employee")
	   @Produces(MediaType.APPLICATION_JSON)
		public  List<Employee> getEmployee(){
			return employeedao.getEmpList();
		}
	   
	   @GET
	   @Path("/oneemployee")
	   @Produces(MediaType.APPLICATION_JSON)
		public  String oneEmployee(){
			return new Gson().toJson(employeedao.getEmpList().get(1));
		}
	   
	 @PUT
	 @Path("/updateEmp")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	public String updateEmployee(Employee emp)
	 {
		// System.out.println("post params:::::: "+id+" "+name);
		 Employee employee= new Employee(emp.getId(),emp.getName(),emp.getDesignation(),emp.getDob(),emp.getGendar(),emp.getStatus(),emp.getAddress());
		 System.out.println("id ::::: "+employee.getId()+" name ::::"+employee.getName());
		 int result=employeedao.updateEmployee(employee);
		 if(result==1){
			 return SUCCESS_RESULT;
		 }
		return FAILURE_RESULT;
		
	 }
	 
	 @POST
	 @Path("/createEmp")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public String createEmployee(List<Employee> empList)
	 {
			// Employee employee= new Employee(empDtl.getName(),empDtl.getDesignation(),empDtl.getDob(),empDtl.getGendar(),empDtl.getStatus(),empDtl.getAddress());
				//System.out.println("name ::::"+employee.getName());
				int result=employeedao.addEmployee(empList);
		if(result==1)
		{
			 return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	 }
	 
	 @DELETE
	 @Path("/deleteEmp")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Consumes(MediaType.APPLICATION_JSON)
	 public String deleteEmployee(Employee empDtl)
	 {
		Employee employee= new Employee(empDtl.getId());
		System.out.println("name ::::"+employee.getId());
		int result=employeedao.deleteEmployee(empDtl);
		if(result==1)
		{
			 return SUCCESS_RESULT;
		}
		return FAILURE_RESULT;
	 }
	 

}

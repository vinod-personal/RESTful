package com.webservice.rest;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;


import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

public class EmployeeClient {
	
	EmployeeDao employeedao = new EmployeeDao();
	
	public static void main(String[] args)
	{
		//new EmployeeClient().postRequest();
		new EmployeeClient().loadEmployeeDtl();
		new Date();
	}
	
	public void postRequest()
	{
		System.out.println("started post method");
		RestTemplate  restTemplate = new RestTemplate();
		String url="http://localhost:8089/WebService/rest/empService/updateEmp";
		List<Employee> empList = employeedao.getEmpList();
		Gson gson = new Gson();
		String requestjson = gson.toJson(empList);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(requestjson,headers);
		String response = restTemplate.postForObject(url, entity, String.class);
		System.out.println(":: response type:::: "+response);
	}

	public void loadEmployeeDtl()
	{
		System.out.println("::: Started loading employee");
		String url="http://localhost:8089/WebService/rest/empService/employee";
		RestTemplate rt = new RestTemplate();
		
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);*/
List<Emp> list1 = new ArrayList<Emp>();
		//rt.getMessageConverters().add(new StringHttpMessageConverter());
		String response =rt.getForObject(url, String.class);
		JsonParser ppar = new JsonParser();
		JsonObject obj = (JsonObject) ppar.parse(response);
		JsonArray array = obj.getAsJsonArray("employee");
		Gson gson = new Gson();
		
		Type collectionType = new TypeToken<Collection<Employee>>() {}.getType();
		List<Employee> list = gson.fromJson(array, collectionType);
		for(Employee emp:list ){
			
			Emp e = new Emp();
			e.setId(emp.getId());
			e.setName(emp.getName());
			e.setDesignation(emp.getDesignation());
//			e.setDob(emp.getDob());
			e.setGendar(emp.getGendar());
			e.setStatus(emp.getStatus());
			e.setAddress(emp.getAddress());
			list1.add(e);
		}
		System.out.println(":::: response size ::::"+response);
	}
	
}

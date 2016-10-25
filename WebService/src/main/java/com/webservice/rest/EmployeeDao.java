package com.webservice.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.DataBinder;

import com.mysql.jdbc.PreparedStatement;

public class EmployeeDao {
	
	private static List<Employee> empList = new ArrayList<Employee>();
	
	 DBConnection dbconn = new DBConnection();
	
	public List<Employee> getEmpList() {
		 Connection conn=null;
		 Statement stat =null;
		 List<Employee> employeeList = new ArrayList<Employee>();
		try{
			/*File file = new File("employee.dat");
			if(!file.exists())
			{
				Employee employee = new Employee(1,"Janardhan");
				empList.add(employee);
				saveUserList(empList);
			}
			else
			{
				FileInputStream fInputStream = new FileInputStream(file);
				ObjectInputStream objectInputStream = new ObjectInputStream(fInputStream);
				empList = (List<Employee>) objectInputStream.readObject();
				objectInputStream.close();*/
			conn=dbconn.connect();
			stat=conn.createStatement();
			String sql = "select * from employee";
			ResultSet rs=stat.executeQuery(sql);
			while(rs.next())
			{
				Employee emp = new Employee();
				emp.setId(rs.getInt("id"));
				emp.setName(rs.getString("name"));
				emp.setDesignation(rs.getString("designation"));
				emp.setDob(rs.getDate("dob"));
				emp.setGendar(rs.getString("gendar"));
				emp.setStatus(rs.getString("status"));
				emp.setAddress(rs.getString("address"));
				employeeList.add(emp);
			}
			System.out.println("::: emp list size:::: "+employeeList.size());
		}catch(Exception e){
				e.printStackTrace();
		}
		return employeeList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}

	public int addEmployee(List<Employee> empList2){
		//List<Employee> empList = getEmpList();
		 Connection conn=null;
		 Statement stat =null;
		 PreparedStatement pt = null;
		 int resultStatus=0;
		try{
			 //employee = new Employee(1,"Vinod");
			/* empList.add(employee);
			 saveUserList(empList);*/
			 conn=dbconn.connect();
			// stat=conn.createStatement();
			 String saveEmp ="INSERT INTO employee(name,designation,dob,gendar,status,address) "
			 		+ " VALUES(?,?,?,?,?,?)";
			 //resultStatus= stat.executeUpdate(saveEmp);
			 pt=(PreparedStatement) conn.prepareStatement(saveEmp);
			 for(Employee empDtl:empList2)
			 {
				 pt.setString(1, empDtl.getName());
				 pt.setString(2, empDtl.getDesignation());
				 java.sql.Date sqlUpdatedDateDate = new java.sql.Date(empDtl.getDob().getTime());
				 pt.setDate(3,sqlUpdatedDateDate);
				 //pt.setDate(3, (Date)employee.getDob());
				 pt.setString(4, empDtl.getGendar());
				 pt.setString(5, empDtl.getStatus());
				 pt.setString(6, empDtl.getAddress());
				 resultStatus=pt.executeUpdate();
			 }
			 pt.close();
			 conn.close();
			 System.out.println("insert satus::: "+resultStatus);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultStatus;
	}

	public int updateEmployee(Employee employee) {
		//List<Employee> empList = getEmpList();
		 Connection conn=null;
		 Statement stat =null;
		 PreparedStatement pt = null;
		 int resultStatus=0;
		try{
			 //employee = new Employee(1,"Vinod");
			/* empList.add(employee);
			 saveUserList(empList);*/
			 conn=dbconn.connect();
			 stat=conn.createStatement();
			 String updateEmp ="UPDATE employee SET name = ?,designation =? ,dob = ?, gendar = ?,status = ?,address =?  WHERE id=? ";
			 //resultStatus= stat.executeUpdate(updateEmp);
			 pt=(PreparedStatement) conn.prepareStatement(updateEmp);
			 pt.setString(1, employee.getName());
			 pt.setString(2, employee.getDesignation());
			 java.sql.Date sqlUpdatedDateDate = new java.sql.Date(employee.getDob().getTime());
			 pt.setDate(3, sqlUpdatedDateDate);
			 pt.setString(4, employee.getGendar());
			 pt.setString(5, employee.getStatus());
			 pt.setString(6, employee.getAddress());
			 pt.setInt(7, employee.getId());
			 resultStatus=pt.executeUpdate();
			 pt.close();
			 conn.close();
			 System.out.println("update status::: "+resultStatus);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultStatus;
	}
	
	public int deleteEmployee(Employee employee){
		
		 Connection conn=null;
		 Statement stat =null;
		 int resultStatus=0;
		try{
			 //employee = new Employee(1,"Vinod");
			/* empList.add(employee);
			 saveUserList(empList);*/
			 conn=dbconn.connect();
			 stat=conn.createStatement();
			 String deleteEmp = "DELETE FROM employee WHERE id="+employee.getId()+" ";
			 resultStatus= stat.executeUpdate(deleteEmp);
			 System.out.println("update status::: "+resultStatus);
		}catch(Exception e){
			e.printStackTrace();
		}
		return resultStatus;
	}

	private void saveUserList(List<Employee> empList2) {
		
		try{
			File file= new File("employee.dat");
			System.out.println(file.exists());
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(empList2);
			oos.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
}

package com.webservice.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import com.mysql.jdbc.PreparedStatement;

public class DBConnection {
	
	private static final String driver_class="com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://localhost:3306/restprj";
	private static final String username="root";
	private static final String password="india123";
	
	public Connection connect(){
		
		Connection conn=null;
		Statement stat=null;
		try{
			Class.forName(driver_class);
			conn=DriverManager.getConnection(url,username,password);  
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	/*public static void main(String... args){
		
		Connection conn=null;
		Statement stat=null;
		DBConnection db = new  DBConnection();
		conn=db.connect();
		Date date = new Date();
		PreparedStatement pt=null;
	    try {
			stat=conn.createStatement();
			System.out.println(stat.isClosed());
			ResultSet sql = stat.executeQuery("select * from employee");
			//Object params[] = new Object[3];
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    dateForMySql = sdf.format(date);
			 
			String saveEmp ="INSERT INTO employee(name,designation,dob) "
			 		+ " VALUES(?,?,?)";
			 //resultStatus= stat.executeUpdate(saveEmp);
			java.sql.Date sqlUpdatedDateDate = new java.sql.Date(date.getTime());
			 pt=(PreparedStatement) conn.prepareStatement(saveEmp);
			 pt.setString(1, "Vinod");
			 pt.setString(2, "Java Developer");
			 pt.setDate(3,sqlUpdatedDateDate);
			 //pt.setDate(3, (Date)employee.getDob());
			 pt.setString(4, employee.getGendar());
			 pt.setString(5, employee.getStatus());
			 pt.setString(6, employee.getAddress());
			 pt.executeUpdate();
			System.out.println(sql.next());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		
	}*/
}

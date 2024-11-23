package demo.jsp.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeesBean {
	public List<Employees> fetchEmployees(){
		List<Employees> empLst = new ArrayList<>();
		String url="jdbc:mysql://localhost:3306/test";
		String username="root";
		String password="farhanmySQL";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		try(Connection conn = DriverManager.getConnection(url,username,password); 
			Statement stmt=conn.createStatement()){
			ResultSet resultSet =stmt.executeQuery("select *from EMP");
			
			while(resultSet.next()) {
				Employees employees =new Employees();
				employees.setEmpNo(resultSet.getInt(1));
				employees.setEname(resultSet.getString(2));
                employees.setSal(resultSet.getDouble(3));
                empLst.add(employees);
			}
			resultSet.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return empLst;
	}

}

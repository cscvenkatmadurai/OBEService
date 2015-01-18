package edu.tce.cse.obe.database_abstraction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

import edu.tce.cse.obe.configuration.Configuration;
import edu.tce.cse.obe.model.Department;

public class DepartmentRelation {

	public static List<Department> getDepartments(int year)
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException, ParseException {
		
		List<Department> departmentList = new LinkedList<Department>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
		
			String sql;
			sql = "SELECT * FROM department WHERE year = ? ";	
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, year);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getString("department_id"));
				department.setName(rs.getString("department_name"));
				department.setYear(rs.getInt("year"));
				departmentList.add(department);
			}

			rs.close();
			stmt.close();
			conn.close();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return departmentList;
	}

	
	public static void addDepartment(Department department) 
		throws IOException, ClassNotFoundException, SQLException {
			Connection conn = null;
			PreparedStatement addDepartment = null;
			try {
				Configuration config = new Configuration();
				Class.forName(config.getProperty("dbDriver"));
				conn = DriverManager.getConnection(config.getProperty("dbUrl"),
						config.getProperty("dbUser"),
						config.getProperty("dbPassword"));

				String sql;
				sql = "INSERT INTO department VALUES ( ?,?,?)";
				addDepartment = conn.prepareStatement(sql);
				addDepartment.setString(1, department.getId());
				addDepartment.setString(2, department.getName());
				addDepartment.setInt(3, department.getYear());
				addDepartment.executeUpdate();

			} finally {
				try {
					if (addDepartment != null) {
						addDepartment.close();
					}
				} catch (SQLException se2) {
					se2.printStackTrace();
				}
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException se) {
					se.printStackTrace();
				}
			}

	}

	public static boolean deleteDepartment(String departmentId,int year)
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "DELETE FROM department WHERE department_id= ? AND year = ? ";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, departmentId);
			stmt.setInt(2, year);
			
			int status = stmt.executeUpdate();
			return !(status == 0);
			
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}

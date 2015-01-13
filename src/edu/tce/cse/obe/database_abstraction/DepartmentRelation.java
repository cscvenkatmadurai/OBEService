package edu.tce.cse.obe.database_abstraction;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import edu.tce.cse.obe.configuration.Configuration;
import edu.tce.cse.obe.model.Department;
import edu.tce.cse.obe.model.Program;

public class DepartmentRelation {

	public static List<Department> getDepartments()
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException {
		List<Department> departmentList = new LinkedList<Department>();
		Connection conn = null;
		Statement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id,name FROM Department";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getString("id"));
				department.setName(rs.getString("name"));
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

	public static Department getDepartmentById(String deptartmentId) {

		Department department = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			stmt = conn.createStatement();
			String sql;
			sql = "SELECT id,name FROM Department WHERE id='" + deptartmentId
					+ "'";
			ResultSet rs = stmt.executeQuery(sql);

			if (rs.next()) {
				department = new Department();
				department.setId(rs.getString("id"));
				department.setName(rs.getString("name"));

				sql = "SELECT id FROM Program WHERE departmentId='"
						+ deptartmentId + "'";
				rs = stmt.executeQuery(sql);

				/*
				 * List<Program> programList = new LinkedList<Program>(); while
				 * (rs.next()) { Program p = new Program();
				 * p.setPId(rs.getString("id")); programList.add(p); }
				 * department.setProgramList(programList);
				 */

			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
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

		return department;
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
				sql = "INSERT INTO department VALUES ( ?,?)";
				addDepartment = conn.prepareStatement(sql);
				addDepartment.setString(1, department.getId());
				addDepartment.setString(2, department.getName());

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

	public static boolean deleteDepartment(String departmentId)
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			stmt = conn.createStatement();
			String sql;
			sql = "DELETE FROM department WHERE id='" + departmentId + "'";
			
			
			int status = stmt.executeUpdate(sql);
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

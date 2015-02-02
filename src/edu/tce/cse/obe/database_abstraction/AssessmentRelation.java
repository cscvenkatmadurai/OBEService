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
import edu.tce.cse.obe.model.Assessment;
import edu.tce.cse.obe.model.Department;

public class AssessmentRelation {

	public static List<Assessment> getAssessments()
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException, ParseException {

		List<Assessment> assessmentList = new LinkedList<Assessment>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM assessment";
			stmt = conn.prepareStatement(sql);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Assessment assessment = new Assessment(
						rs.getString("assessment_id"), rs.getString("name"),
						rs.getString("type"));
				assessmentList.add(assessment);
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

		return assessmentList;
	}

	public static void addDepartment(Department department) throws IOException,
			ClassNotFoundException, SQLException {
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

	public static boolean modifyDepartment(String departmentID, int year,
			Department department) throws IOException, ClassNotFoundException,
			SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "UPDATE department SET department_id = ?, department_name=?,"
					+ "year = ? WHERE department_id = ? AND year = ?";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, department.getId());
			stmt.setString(2, department.getName());
			stmt.setInt(3, department.getYear());
			stmt.setString(4, departmentID);
			stmt.setInt(5, year);

			int status = stmt.executeUpdate();
			return !(status == 0);

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
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

	public static boolean deleteDepartment(String departmentId, int year)
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

	public static void addAssessment(Assessment assessment) throws IOException,
			ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "INSERT INTO assessment VALUES ( ?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, assessment.getAssessmentID());
			stmt.setString(2, assessment.getAssessmentName());
			stmt.setString(3, assessment.getAssessmentType());
			stmt.executeUpdate();

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
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

	public static boolean modifyAssessment(String assessmentID,
			Assessment assessment) throws IOException, ClassNotFoundException,
			SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "UPDATE assessment SET assessment_id = ?, name=?,"
					+ "type = ? WHERE assessment_id = ? ";

			stmt = conn.prepareStatement(sql);

			stmt.setString(1, assessment.getAssessmentID());
			stmt.setString(2, assessment.getAssessmentName());
			stmt.setString(3, assessment.getAssessmentType());
			stmt.setString(4, assessmentID);

			int status = stmt.executeUpdate();
			return !(status == 0);

		} finally {
			try {
				if (stmt != null) {
					stmt.close();
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

	public static boolean deleteAssessment(String assessmentID)
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
			sql = "DELETE FROM assessment WHERE assessment_id= ?";
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, assessmentID);

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

package edu.tce.cse.obe.database_abstraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import edu.tce.cse.obe.configuration.Configuration;
import edu.tce.cse.obe.model.CourseOutcome;

public class CourseOutcomeRelation {
	
	public static List<CourseOutcome> getCourseOutcomes(String courseID, int year) {
		List<CourseOutcome> courseOutcomeList = new LinkedList<CourseOutcome>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM course_outcome WHERE course_id = ? AND year = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			stmt.setInt(2, year);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				CourseOutcome courseOutcome = new CourseOutcome(
						rs.getString("co_id"),
						rs.getString("co_name"),
						rs.getString("course_id"),
						rs.getInt("year")
						);
				courseOutcomeList.add(courseOutcome);
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

		return courseOutcomeList;
	}
	/* public static CourseOutcome addCourseOutcome(CourseOutcome CourseOutcome, String CourseId){
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
			sql = "SELECT * FROM CourseOutcome WHERE id='"+CourseOutcome.getId()+"'";
			ResultSet rs = stmt.executeQuery(sql);

			if(!rs.next()) {
				sql="INSERT INTO CourseOutcome(id,name,CourseId) VALUES('"+CourseOutcome.getId()+"','"+CourseOutcome.getName()+"','"+CourseId+"')";
				stmt.executeUpdate(sql);
				
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
		return CourseOutcome;
	}
	public static void deleteCourseOutcome(String CourseOutcomeId, String courseId){
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
			sql = "DELETE FROM CourseOutcome WHERE id='"+CourseOutcomeId+"' AND CourseId='"+courseId+"'";
	        stmt.executeUpdate(sql);
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
	}
	
	public static void updateCourseOutcome(String CourseOutcomeId, String courseId, CourseOutcome CourseOutcome){
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
			sql = "UPDATE CourseOutcome SET name='"+CourseOutcome.getName()+"' WHERE id='"+CourseOutcomeId+"' AND CourseId='"+courseId+"'";
	        stmt.executeUpdate(sql);
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
	}
*/
}

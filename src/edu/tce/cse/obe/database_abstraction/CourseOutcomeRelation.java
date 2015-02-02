package edu.tce.cse.obe.database_abstraction;

import java.io.IOException;
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
	
	public static List<CourseOutcome> getCourseOutcomes(String courseID, int year)throws IOException, ClassNotFoundException, SQLException {
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
	public static void addCourseOutcome(CourseOutcome CourseOutcome, String CourseId)throws IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
			String sql;
			PreparedStatement addCourseOutcome;
			sql="INSERT INTO course_outcome(co_id,co_name,course_id, year) VALUES(?,?,?,?)";
			addCourseOutcome=conn.prepareStatement(sql);
			addCourseOutcome.setString(1, CourseOutcome.getCoID());
			addCourseOutcome.setString(2, CourseOutcome.getCoName());
			addCourseOutcome.setString(3, CourseId);
			addCourseOutcome.setInt(4,CourseOutcome.getYear());
		    addCourseOutcome.executeUpdate();

			//rs.close();
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
	public static boolean deleteCourseOutcome(final String coID,final String courseID, int year)
			throws IOException, ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement deleteCourseOutcome = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "DELETE FROM course_outcome WHERE co_id = ? AND course_id = ? AND year = ?";
			deleteCourseOutcome = conn.prepareStatement(sql);
			deleteCourseOutcome.setString(1, coID);
			deleteCourseOutcome.setString(2,courseID);
			deleteCourseOutcome.setInt(3, year);
			
			int status = deleteCourseOutcome.executeUpdate();
				
			return !(status == 0);

		} finally {
			try {
				if (deleteCourseOutcome != null) {
					deleteCourseOutcome.close();
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

	public static boolean modifyCourseOutcome(CourseOutcome CourseOutcome,String coID, String courseID, int year)throws IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
			String sql;
			sql = "UPDATE course_outcome SET co_id = ?,co_name = ?,course_id = ?,year = ? WHERE co_id = ? AND course_id = ? AND year = ?";
	        stmt=conn.prepareStatement(sql);
	        stmt.setString(1, CourseOutcome.getCoID());
	        stmt.setString(2, CourseOutcome.getCoName());
	        stmt.setString(3, CourseOutcome.getCourseID());
	        stmt.setInt(4, CourseOutcome.getYear());
	        stmt.setString(5, coID);
	        stmt.setString(6, courseID);
	        stmt.setInt(7,year);
	        stmt.executeUpdate();
			stmt.close();
			conn.close();
			int status = stmt.executeUpdate();
			return !(status == 0);
			
		} 
		finally {
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
}

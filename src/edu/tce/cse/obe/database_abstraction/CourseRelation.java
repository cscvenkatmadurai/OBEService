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
import edu.tce.cse.obe.model.Course;

public class CourseRelation {

	public static List<Course> getCourses(String ProgramID,int year)
			throws IOException, ClassNotFoundException, SQLException {
		List<Course> CourseList = new LinkedList<Course>();
		Connection conn = null;
		PreparedStatement getCourses = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM course_list WHERE program_id = ? AND year = ?";
			getCourses = conn.prepareStatement(sql);
			getCourses.setString(1, ProgramID);
			getCourses.setInt(2,year);

			ResultSet rs = getCourses.executeQuery();

			while (rs.next()) {
				Course p = new Course(rs.getString("course_id"), rs.getString("course_name"), rs.getInt("number_of_students"), rs.getInt("year"));
				CourseList.add(p);
			}
			rs.close();
			getCourses.close();
			conn.close();
		} finally {
			try {
				if (getCourses != null) {
					getCourses.close();
				}
			} catch (SQLException se2) {
			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return CourseList;

	}

	public static boolean deleteCourse(final String CourseID, int year)
			throws IOException, ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement getCourses = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "DELETE FROM course_list WHERE course_id = ? AND year = ?";
			getCourses = conn.prepareStatement(sql);
			getCourses.setString(1, CourseID);
			getCourses.setInt(2, year);
			
			int status = getCourses.executeUpdate();
				
			return !(status == 0);

		} finally {
			try {
				if (getCourses != null) {
					getCourses.close();
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

	public static void addCourse(Course Course, String programId) throws IOException,
		ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement addCourse = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			//System.out.println(Course.getCourseID());
			sql = "INSERT INTO course_list VALUES ( ?,?,?,?,?)";
			addCourse = conn.prepareStatement(sql);
			addCourse.setString(1, Course.getCourseID());
			addCourse.setString(2, Course.getCourseName());
			addCourse.setString(3, programId);
			addCourse.setInt(4,Course.getCount());
			addCourse.setInt(5, Course.getYear());
		
			addCourse.executeUpdate();

		} finally {
			try {
				if (addCourse != null) {
					addCourse.close();
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

	public static boolean modifyCourse(String CourseID,Course Course,int year,String programId)
			throws IOException, ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement modifyCourse = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "UPDATE course_list SET course_id =?, course_name =?, program_id = ?, year = ? "
					+ " WHERE course_id = ? AND year = ?";
			modifyCourse = conn.prepareStatement(sql);
			modifyCourse.setString(1, Course.getCourseID());
			modifyCourse.setString(2, Course.getCourseName());
			modifyCourse.setString(3, programId);
			modifyCourse.setInt(4, Course.getYear());
			modifyCourse.setString(5, CourseID);
			modifyCourse.setInt(6, year);
			
			int status = modifyCourse.executeUpdate();
			return !(status == 0);
			
		} finally {
			try {
				if (modifyCourse != null) {
					modifyCourse.close();
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

	public static Course getCourseObject(String courseID,int year)
			throws IOException, ClassNotFoundException, SQLException {

		Course course = null;
		Connection conn = null;
		PreparedStatement getCourse = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM course_list WHERE course_id = ? AND year = ?";
			getCourse = conn.prepareStatement(sql);
			getCourse.setString(1, courseID);
			getCourse.setInt(2,year);

			ResultSet rs = getCourse.executeQuery();

			if(rs.next()) {
				course = new Course(rs.getString("course_id"), rs.getString("course_name"), rs.getInt("number_of_students"), rs.getInt("year"));				
			}
			
			rs.close();
			getCourse.close();			
			conn.close();
			
			return course;
			
		} finally {
			try {
				if (getCourse != null) {
					getCourse.close();
				}
			} catch (SQLException se2) {
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

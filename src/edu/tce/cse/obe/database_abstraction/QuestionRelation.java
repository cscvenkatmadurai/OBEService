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

import javax.ws.rs.DELETE;

import edu.tce.cse.obe.configuration.Configuration;
import edu.tce.cse.obe.model.Department;
import edu.tce.cse.obe.model.Question;

public class QuestionRelation {

	public static List<Question> getQuestions(String courseID, int year)
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException, ParseException {

		List<Question> questionList = new LinkedList<Question>();
		Connection conn = null;
		PreparedStatement stmt = null, getCOList = null;

		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM question WHERE course_id = ? AND year = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			stmt.setInt(2, year);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Question question = new Question(rs.getInt("id"),
						rs.getString("question_no"),
						rs.getString("question_statement"),
						rs.getString("course_id"),
						rs.getString("assessment_id"),
						rs.getInt("no_of_right_answers"),
						rs.getInt("total_no_of_answers"),
						rs.getString("section"), rs.getInt("year"));

				getCOList = conn
						.prepareStatement("SELECT * FROM co_for_question WHERE id = ?");
				getCOList.setString(1, rs.getString("id"));
				ResultSet coListResultSet = getCOList.executeQuery();
				List<String> coList = new LinkedList<String>();
				while (coListResultSet.next()) {
					coList.add(coListResultSet.getString("co_id"));
				}
				question.setCoList(coList);

				questionList.add(question);
			}

			rs.close();
			stmt.close();
			conn.close();

			return questionList;
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
	public static List<Question> getQuestions(String courseID)
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException, ParseException {

		List<Question> questionList = new LinkedList<Question>();
		Connection conn = null;
		PreparedStatement stmt = null, getCOList = null;

		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM question WHERE course_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Question question = new Question(rs.getInt("id"),
						rs.getString("question_no"),
						rs.getString("question_statement"),
						rs.getString("course_id"),
						rs.getString("assessment_id"),
						rs.getInt("no_of_right_answers"),
						rs.getInt("total_no_of_answers"),
						rs.getString("section"), rs.getInt("year"));

				getCOList = conn
						.prepareStatement("SELECT * FROM co_for_question WHERE id = ?");
				getCOList.setString(1, rs.getString("id"));
				ResultSet coListResultSet = getCOList.executeQuery();
				List<String> coList = new LinkedList<String>();
				while (coListResultSet.next()) {
					coList.add(coListResultSet.getString("co_id"));
				}
				question.setCoList(coList);

				questionList.add(question);
			}

			rs.close();
			stmt.close();
			conn.close();

			return questionList;
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
	
	public static List<Question> getQuestions( String courseID,String assementId,int year)
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException, ParseException {

		List<Question> questionList = new LinkedList<Question>();
		Connection conn = null;
		PreparedStatement stmt = null, getCOList = null;

		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM question WHERE course_id = ? AND assessment_id=? AND year=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			stmt.setString(2, assementId);
			stmt.setInt(3, year);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Question question = new Question(rs.getInt("id"),
						rs.getString("question_no"),
						rs.getString("question_statement"),
						rs.getString("course_id"),
						rs.getString("assessment_id"),
						rs.getInt("no_of_right_answers"),
						rs.getInt("total_no_of_answers"),
						rs.getString("section"), rs.getInt("year"));

				getCOList = conn
						.prepareStatement("SELECT * FROM co_for_question WHERE id = ?");
				getCOList.setString(1, rs.getString("id"));
				ResultSet coListResultSet = getCOList.executeQuery();
				List<String> coList = new LinkedList<String>();
				while (coListResultSet.next()) {
					coList.add(coListResultSet.getString("co_id"));
				}
				question.setCoList(coList);

				questionList.add(question);
			}

			rs.close();
			stmt.close();
			conn.close();

			return questionList;
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
	
	public static List<Question> getQuestions(int year)
			throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException, ParseException {

		List<Question> questionList = new LinkedList<Question>();
		Connection conn = null;
		PreparedStatement stmt = null, getCOList = null;

		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM question WHERE  year=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, year);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Question question = new Question(rs.getInt("id"),
						rs.getString("question_no"),
						rs.getString("question_statement"),
						rs.getString("course_id"),
						rs.getString("assessment_id"),
						rs.getInt("no_of_right_answers"),
						rs.getInt("total_no_of_answers"),
						rs.getString("section"), rs.getInt("year"));

				getCOList = conn
						.prepareStatement("SELECT * FROM co_for_question WHERE id = ?");
				getCOList.setString(1, rs.getString("id"));
				ResultSet coListResultSet = getCOList.executeQuery();
				List<String> coList = new LinkedList<String>();
				while (coListResultSet.next()) {
					coList.add(coListResultSet.getString("co_id"));
				}
				question.setCoList(coList);

				questionList.add(question);
			}

			rs.close();
			stmt.close();
			conn.close();

			return questionList;
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



	public static void addQuestion(Question question) throws IOException,
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

			sql = "INSERT INTO question (course_id,assessment_id,question_no,"
					+ "question_statement,no_of_right_answers,"
					+ "total_no_of_answers,section,year)VALUES ( ?,?,?,?,?,?,?,?)";

			stmt = conn.prepareStatement(sql);
			stmt.setString(1, question.getCourseId());
			stmt.setString(2, question.getAssesmentId());
			stmt.setString(3, question.getQuestionNo());
			stmt.setString(4, question.getQuestionStatement());
			stmt.setInt(5, question.getNoOfRightAnswer());
			stmt.setInt(6, question.getTotalNoAnswer());
			stmt.setString(7, question.getSection());
			stmt.setInt(8, question.getYear());

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
	public static boolean  deleteQuestionCourseId(String courseId) throws IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
	
			String sql;
		
			sql = "DELETE FROM question WHERE course_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,courseId);
			int status = stmt.executeUpdate();
		
			return!(status == 0);
		
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static boolean deleteQuestionCourseIdYear(String courseId,int year) throws IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
	
			String sql;
			
			sql = "DELETE FROM question WHERE course_id=? AND year=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,courseId);
			stmt.setInt(2,year);
			int status = stmt.executeUpdate();
			return !(status == 0);
		
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}

	
	
	
	public static boolean deleteQuestionCourseIdYearAssessmentId(String courseId,int year,String assessmentId) throws IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
	
			String sql;
			
			sql = "DELETE FROM question WHERE course_id=? AND year=? AND assessment_id=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,courseId);
			stmt.setInt(2,year);
			stmt.setString(3,assessmentId);
			int status = stmt.executeUpdate();
			return !(status == 0);
		
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			
		}
		
	}
	public static boolean update(Question question,String courseId,int year,String assessmentId,String questionNO,String section) throws ClassNotFoundException, FileNotFoundException, IOException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try{
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
			
			String sql;
			sql ="UPDATE question SET question_statement=?,no_of_right_answers=?,total_no_of_answers=? "+"WHERE course_id=? AND year=? AND assessment_id=? AND section=? AND question_no=?";
			stmt  = conn.prepareStatement(sql);
			stmt.setString(1, question.getQuestionStatement());
			stmt.setInt(2,question.getNoOfRightAnswer());
			stmt.setInt(3,question.getTotalNoAnswer());
			stmt.setString(4, courseId);
			stmt.setInt(5, year);
			stmt.setString(6,assessmentId);
			stmt.setString(7, section);
			stmt.setString(8,questionNO);
			
			int status = stmt.executeUpdate();
			return !( status == 0);
		}finally{
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		

	
		
	}
}

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
import edu.tce.cse.obe.model.ExamSubdivision;

public class ExamSubdivisionRelation {

	public static List<ExamSubdivision> getExamSubdivision(String courseID,
			int year) throws FileNotFoundException, IOException, SQLException,
			ClassNotFoundException, ParseException {

		List<ExamSubdivision> examSubdivisionList = new LinkedList<ExamSubdivision>();
		Connection conn = null;
		PreparedStatement stmt = null, getCOList = null;
		
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM exam_subdivision WHERE course_id = ? AND year = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseID);
			stmt.setInt(2, year);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ExamSubdivision examSubDivision = new ExamSubdivision(
						rs.getInt("id"),
						rs.getString("course_id"), 
						rs.getString("exam_id"), 
						rs.getString("question_no"), 
						rs.getString("question"), 
						rs.getInt("no_of_right_answers"), 
						rs.getInt("total_no_of_answers"),
						rs.getString("section"),
						rs.getInt("year"),null
						);
	
				getCOList = conn.prepareStatement("SELECT * FROM co_for_questions WHERE id = ?");
				getCOList.setString(1, rs.getString("id"));
				ResultSet coListResultSet = getCOList.executeQuery();
				List<String> coList = new LinkedList<String>();
				while(coListResultSet.next()){
					coList.add(coListResultSet.getString("co_id"));
				}
				examSubDivision.setCOList(coList);
				
				examSubdivisionList.add(examSubDivision);
			}

			rs.close();
			stmt.close();
			conn.close();
			
			return examSubdivisionList;
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

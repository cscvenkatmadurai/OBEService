package edu.tce.cse.obe.database_abstraction;

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
import edu.tce.cse.obe.model.ProgramOutcome;

public class ProgramOutcomeRelation {
	public static List<ProgramOutcome> getProgramOutcomes(String programID, int year)throws IOException, ClassNotFoundException, SQLException  {
		List<ProgramOutcome> programOutcomeList = new LinkedList<ProgramOutcome>();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM program_outcome WHERE program_id = ? AND year = ?";
			
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, programID);
			stmt.setInt(2, year);
			
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				ProgramOutcome programOutcome = new ProgramOutcome(
						rs.getString("po_id"),
						rs.getString("po_name"),
						rs.getString("program_id"),
						rs.getInt("year")
						);
				programOutcomeList.add(programOutcome);
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

		return programOutcomeList;
	}
	public static void addProgramOutcome(ProgramOutcome programOutcome, String programId)throws IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
			String sql;
			PreparedStatement addprogramOutcome;
			sql="INSERT INTO program_outcome(po_id,po_name,program_id, year) VALUES(?,?,?,?)";
			addprogramOutcome=conn.prepareStatement(sql);
			addprogramOutcome.setString(1, programOutcome.getPoID());
			addprogramOutcome.setString(2, programOutcome.getPoName());
			addprogramOutcome.setString(3, programId);
			addprogramOutcome.setInt(4,programOutcome.getYear());
			addprogramOutcome.executeUpdate();
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
	public static boolean deleteProgramOutcome(final String poID,final String programID, int year)
			throws IOException, ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement deleteProgramOutcome = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "DELETE FROM program_outcome WHERE po_id = ? AND program_id = ? AND year = ?";
			deleteProgramOutcome = conn.prepareStatement(sql);
			deleteProgramOutcome.setString(1, poID);
			deleteProgramOutcome.setString(2,programID);
			deleteProgramOutcome.setInt(3, year);
			
			int status = deleteProgramOutcome.executeUpdate();
				
			return !(status == 0);

		} finally {
			try {
				if (deleteProgramOutcome != null) {
					deleteProgramOutcome.close();
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

	public static boolean modifyProgramOutcome(ProgramOutcome programOutcome,String poID, String programID, int year)throws IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
			String sql;
			sql = "UPDATE program_outcome SET po_id = ?,po_name = ?,program_id = ?,year = ? WHERE po_id = ? AND program_id = ? AND year = ?";
	        stmt=conn.prepareStatement(sql);
	        stmt.setString(1, programOutcome.getPoID());
	        stmt.setString(2, programOutcome.getPoName());
	        stmt.setString(3, programOutcome.getProgramID());
	        stmt.setInt(4, programOutcome.getYear());
	        stmt.setString(5, poID);
	        stmt.setString(6, programID);
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

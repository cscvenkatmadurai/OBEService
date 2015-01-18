package edu.tce.cse.obe.database_abstraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import edu.tce.cse.obe.configuration.Configuration;
import edu.tce.cse.obe.model.Department;
import edu.tce.cse.obe.model.ProgramOutcome;

public class ProgramOutcomeRelation {
	public static List<ProgramOutcome> getProgramOutcomes(String programId,int year) {
		List<ProgramOutcome> ProgramOutcomeList = new LinkedList<ProgramOutcome>();
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
			sql = "SELECT * FROM program_outcome WHERE program_id='"+programId+"' AND year = "+year;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ProgramOutcome ProgramOutcome = new ProgramOutcome();
				ProgramOutcome.setId(rs.getString("po_id"));
				ProgramOutcome.setName(rs.getString("po_name"));
				ProgramOutcome.setYear(rs.getInt("year"));
				ProgramOutcome.setProgrmaID(rs.getString("program_id"));
				
				ProgramOutcomeList.add(ProgramOutcome);
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

		return ProgramOutcomeList;
	}
	/*
	public static ProgramOutcome addProgramOutcome(ProgramOutcome ProgramOutcome, String programId){
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
			sql = "SELECT * FROM ProgramOutcome WHERE id='"+ProgramOutcome.getId()+"'";
			ResultSet rs = stmt.executeQuery(sql);

			if(!rs.next()) {
				sql="INSERT INTO ProgramOutcome(id,name,ProgramId) VALUES('"+ProgramOutcome.getId()+"','"+ProgramOutcome.getName()+"','"+programId+"')";
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
		return ProgramOutcome;
	}
	public static void deleteProgramOutcome(String ProgramOutcomeId, String programId){
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
			sql = "DELETE FROM ProgramOutcome WHERE id='"+ProgramOutcomeId+"' AND ProgramId='"+programId+"'";
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
	
	public static void updateProgramOutcome(String ProgramOutcomeId, String programId, ProgramOutcome ProgramOutcome){
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
			sql = "UPDATE ProgramOutcome SET name='"+ProgramOutcome.getName()+"' WHERE id='"+ProgramOutcomeId+"' AND ProgramId='"+programId+"'";
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

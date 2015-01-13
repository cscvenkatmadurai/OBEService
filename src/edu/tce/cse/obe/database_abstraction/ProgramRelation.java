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
import edu.tce.cse.obe.model.Program;

public class ProgramRelation {

	public static List<Program> getPrograms(String departmentID)
			throws IOException, ClassNotFoundException, SQLException {
		List<Program> programList = new LinkedList<Program>();
		Connection conn = null;
		PreparedStatement getPrograms = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT * FROM program WHERE departmentID = ?";
			getPrograms = conn.prepareStatement(sql);
			getPrograms.setString(1, departmentID);

			ResultSet rs = getPrograms.executeQuery();

			while (rs.next()) {
				Program p = new Program(rs.getString("programID"),
						rs.getString("programName"),
						rs.getString("departmentID"));
				programList.add(p);
			}
			rs.close();
			getPrograms.close();
			conn.close();
		} finally {
			try {
				if (getPrograms != null) {
					getPrograms.close();
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

		return programList;

	}

	public static boolean deleteProgram(final String programID)
			throws IOException, ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement getPrograms = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "DELETE FROM program WHERE programID = ?";
			getPrograms = conn.prepareStatement(sql);
			getPrograms.setString(1, programID);

			int status = getPrograms.executeUpdate();
				
			return !(status == 0);

		} finally {
			try {
				if (getPrograms != null) {
					getPrograms.close();
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

	public static void addProgram(Program program) throws IOException,
		ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement addProgram = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "INSERT INTO program VALUES ( ?,?,?)";
			addProgram = conn.prepareStatement(sql);
			addProgram.setString(1, program.getProgramID());
			addProgram.setString(2, program.getName());
			addProgram.setString(3, program.getDepartmentID());
			addProgram.executeUpdate();

		} finally {
			try {
				if (addProgram != null) {
					addProgram.close();
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

	public static boolean modifyProgram(String programID, Program program)
			throws IOException, ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement modifyProgram = null;
		try {
			Configuration config = new Configuration();
			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "UPDATE program SET programID=?, programName=?, departmentID = ? "
					+ " WHERE programID = ?";
			modifyProgram = conn.prepareStatement(sql);
			modifyProgram.setString(1, program.getProgramID());
			modifyProgram.setString(2, program.getName());
			modifyProgram.setString(3, program.getDepartmentID());
			modifyProgram.setString(4, programID);
			
			int status = modifyProgram.executeUpdate();
			return !(status == 0);
			
		} finally {
			try {
				if (modifyProgram != null) {
					modifyProgram.close();
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

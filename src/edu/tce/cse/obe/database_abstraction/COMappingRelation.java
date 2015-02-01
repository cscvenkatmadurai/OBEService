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
import edu.tce.cse.obe.model.COPOMapping;
//import edu.tce.cse.obe.model.CO_mapping;
//import edu.tce.cse.obe.model.CO_mapping;

public class COMappingRelation {
	
	public static int getPOCOValue(String courseID, String coID, String poID, int year){
		//not yet complete
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			sql = "SELECT value FROM co_po_mapping WHERE course_id= ? AND co_id = ? AND po_id = ? AND year=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, courseID);
			stmt.setString(2,coID);
			stmt.setString(3, poID);
			stmt.setInt(4, year);
			
			ResultSet rs = stmt.executeQuery();

			
			if (rs.next()) {
				if(rs.getString("value") != null)
					return 0;
				return 1;

				
			}else{
				return 0;
			}

			
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

		return -1;
		
	}
	/*public static List<CO_mapping> getMapping(String courseId) {
		List<CO_mapping> COMappingList = new LinkedList<CO_mapping>();
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
			sql = "SELECT co_id,po_id,value FROM COMapping WHERE courseId='"+courseId+"'";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
			CO_mapping com=new CO_mapping();
			com.setCo_id(rs.getString("co_id"));
			com.setPo_id(rs.getString("po_id"));
			com.setValue(rs.getString("value"));
			COMappingList.add(com);
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

		return COMappingList;
	}
	*/
	public static void addCOMapping(COPOMapping row, String courseId)throws IOException, ClassNotFoundException, SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));

			String sql;
			
			for(int i=0; i<row.getMappingList().size(); i++)
			{
				sql="INSERT INTO co_po_mapping(co_id,po_id,course_id,value,year) VALUES(?,?,?,?,?)";
				stmt=conn.prepareStatement(sql);
				stmt.setString(1,row.getCoID());
				stmt.setString(2,row.getMappingList().get(i).getPoID());
				stmt.setString(3,row.getCourseID());
				stmt.setString(4,row.getMappingList().get(i).getValue());
				stmt.setInt(5,row.getYear());
				
				stmt.executeUpdate();
			}
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
	public static boolean deleteCOMapping(String courseOutcomeID, String courseID, int year) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
            String sql;
            sql = "DELETE FROM co_po_mapping WHERE co_id = ? AND course_id = ? AND year = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, courseOutcomeID);
			stmt.setString(2,courseID);
			stmt.setInt(3, year);
			
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
	
	public static boolean modifyCOMapping(COPOMapping row,String courseId, int year) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException{
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			Configuration config = new Configuration();

			Class.forName(config.getProperty("dbDriver"));
			conn = DriverManager.getConnection(config.getProperty("dbUrl"),
					config.getProperty("dbUser"),
					config.getProperty("dbPassword"));
			String sql;
			for(int i=0; i<row.getMappingList().size(); i++){
			sql = "UPDATE co_po_mapping SET value=? WHERE co_id = ? AND po_id = ? AND course_id = ? AND year = ?";
	        stmt=conn.prepareStatement(sql);
	        stmt.setString(1, row.getMappingList().get(i).getValue());
	        stmt.setString(2, row.getCoID());
	        stmt.setString(3, row.getMappingList().get(i).getPoID());
	        stmt.setString(4, row.getCourseID());
	        stmt.setInt(5, year);
	        int status=stmt.executeUpdate();
	        if(status!=0)
	        	return false;
			}
			stmt.close();
			conn.close();
			return true;
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

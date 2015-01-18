package edu.tce.cse.obe.database_abstraction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import edu.tce.cse.obe.configuration.Configuration;
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
	public static void addList(List<CO_mapping> list, String courseId){
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
			sql = "SELECT * FROM COMapping WHERE courseId='"+courseId+"'";
			ResultSet rs = stmt.executeQuery(sql);

			if(!rs.next()) {
				for(int i=0; i<list.size(); i++)
				{
				sql="INSERT INTO COMapping(co_id,po_id,courseId,value,year) VALUES('"+list.get(i).getCo_id()+"','"+list.get(i).getPo_id()+"','"+courseId+"','"+list.get(i).getValue()+"','2014')";
				
				stmt.executeUpdate(sql);
				}
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
	}
	public static void deleteCOMapping(String courseOutcomeId, String courseId){
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
			sql = "DELETE FROM COMapping WHERE co_id='"+courseOutcomeId+"' AND courseId='"+courseId+"'";
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
	
	public static void updateCOMapping(String courseId, List<CO_mapping> list){
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
			for(int i=0; i<list.size(); i++){
			sql = "UPDATE COMapping SET value='"+list.get(i).getValue()+"' WHERE co_id='"+list.get(i).getCo_id()+"' AND courseId='"+courseId+"'";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			stmt.close();
			conn.close();
		    }
		}
		catch (SQLException se) {
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

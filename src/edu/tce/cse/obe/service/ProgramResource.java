package edu.tce.cse.obe.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

import edu.tce.cse.obe.database_abstraction.ProgramRelation;
import edu.tce.cse.obe.model.Program;

@Path("/department/{departmentID}/program")
public class ProgramResource {

	@GET
	public Response getPrograms(
			@PathParam("departmentID") final String departmentID) {
		List<Program> programList = null;
		try {
			programList = ProgramRelation.getPrograms(departmentID);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
		Program[] programs = programList.toArray(
				new Program[programList.size()]);
		return Response.ok(programs, MediaType.APPLICATION_XML).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response addProgram(Program program){
		try {
			ProgramRelation.addProgram(program);
			return Response.status(Status.OK).build();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
		} catch (SQLException e) {			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}		
	}
	
	@PUT
	@Path("modify/{programID}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response modifyProgram(@PathParam("programID") final String programID,
			Program program){
		try {
			
			boolean modificationStatus = ProgramRelation.modifyProgram(programID,program);
			if (modificationStatus) {
				return Response.status(Status.OK).build();
			} else {
				return Response.status(Status.BAD_REQUEST).build();
			}
			
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
		} catch (SQLException e) {			
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST).build();
		}		
	}

	@DELETE
	@Path("{programID}")
	public Response deleteProgram(
			@PathParam("programID") final String programID) {
		
		try {
			boolean deleteStatus = ProgramRelation.deleteProgram(programID);
			if (deleteStatus) {
				return Response.status(Status.OK).build();
			} else {
				return Response.status(Status.BAD_REQUEST).build();
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
		}
	}

}

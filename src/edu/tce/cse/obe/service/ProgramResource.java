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

@Path("/{year}/department/{departmentID}/program")
public class ProgramResource {

	@GET
	public Response getPrograms(
			@PathParam("departmentID") final String departmentID,
			@PathParam("year") final int year) {
		List<Program> programList = null;
		try {
			programList = ProgramRelation.getPrograms(departmentID, year);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*").build();
		}
		Program[] programs = programList
				.toArray(new Program[programList.size()]);
		return Response.ok(programs, MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addProgram(Program program,
			@PathParam("year") final int year,
			@PathParam("departmentID") final String departmentID) {
		try {
			ProgramRelation.addProgram(program, departmentID);
			return Response.status(Status.OK)
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@PUT
	@Path("{programID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyProgram(
			@PathParam("programID") final String programID, Program program,
			@PathParam("year") final int year,
			@PathParam("departmentID") final String departmentID) {
		try {

			boolean modificationStatus = ProgramRelation.modifyProgram(
					programID, program, departmentID, year);
			if (modificationStatus) {
				return Response.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*").build();
			} else {
				return Response.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*").build();
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@DELETE
	@Path("{programID}")
	public Response deleteProgram(
			@PathParam("programID") final String programID,
			@PathParam("year") final int year,
			@PathParam("departmentID") final String departmentID) {

		try {
			boolean deleteStatus = ProgramRelation.deleteProgram(programID,
					departmentID, year);
			if (deleteStatus) {
				return Response.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*").build();
			} else {
				return Response.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*").build();
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*").build();
		}
	}

}

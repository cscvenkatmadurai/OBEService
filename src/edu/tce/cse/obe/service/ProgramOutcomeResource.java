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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

import edu.tce.cse.obe.database_abstraction.ProgramOutcomeRelation;
import edu.tce.cse.obe.model.ProgramOutcome;
@Path("/{year}/department/{departmentID}/program/{programID}/programOutcome")
public class ProgramOutcomeResource {
      @GET
	  @Produces(MediaType.APPLICATION_XML)
	  public Response getProgramOutcomes(
				@PathParam("ProgramID") final String ProgramID,
				@PathParam("year") final int year)
	  {
		  List<ProgramOutcome> ProgramOutcomeList = null;
			try {
				ProgramOutcomeList = ProgramOutcomeRelation.getProgramOutcomes(ProgramID,year);
			} catch (ClassNotFoundException | IOException | SQLException e) {
				e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();
			}
			ProgramOutcome[] ProgramOutcomes = ProgramOutcomeList.toArray(
					new ProgramOutcome[ProgramOutcomeList.size()]);
			return Response.ok(ProgramOutcomes, MediaType.APPLICATION_XML).build();

	  }
	  @PUT
	  @Consumes(MediaType.APPLICATION_XML)
	  public Response addProgramOutcome(ProgramOutcome ProgramOutcome,
				@PathParam("programID") final String ProgramID,
				@PathParam("year") final int year) {
		  try {
				ProgramOutcomeRelation.addProgramOutcome(ProgramOutcome, ProgramID);
				return Response.status(Status.OK).build();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
				return Response.status(Status.INTERNAL_SERVER_ERROR).build();  
			} catch (SQLException e) {			
				e.printStackTrace();
				return Response.status(Status.BAD_REQUEST).build();
			}		
	  }
	  
	  @DELETE
	  @Path("{ProgramOutcomeID}")
	  public Response deleteProgramOutcome(@PathParam("ProgramOutcomeID")String ProgramOutcomeID, 
				@PathParam("programID") final String ProgramID,
				@PathParam("year") final int year) {

			try {
				boolean deleteStatus = ProgramOutcomeRelation.deleteProgramOutcome(ProgramOutcomeID,ProgramID,year);
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
	  
	  @PUT
	  @Path("{ProgramOutcomeID}")
	  public Response modifyProgramOutcome(@PathParam("ProgramOutcomeID")String ProgramOutcomeID, ProgramOutcome ProgramOutcome,@PathParam("ProgramID") final String ProgramID,
				@PathParam("year") final int year){
		  try {
				
				boolean modificationStatus = ProgramOutcomeRelation.modifyProgramOutcome(ProgramOutcome,ProgramOutcomeID,ProgramID,year);
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

}

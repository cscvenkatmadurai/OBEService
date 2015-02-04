package edu.tce.cse.obe.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
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

import edu.tce.cse.obe.database_abstraction.AssessmentRelation;
import edu.tce.cse.obe.model.Assessment;

@Path("/assessment")
public class AssessmentResource {

	@GET
	public Response getAssessments() {
		List<Assessment> assessmentList;
		try {
			assessmentList = AssessmentRelation.getAssessments();
			Assessment[] assessments = assessmentList
					.toArray(new Assessment[assessmentList.size()]);
			return Response.ok(assessments, MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*").build();
		} catch (ParseException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*").build();
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(final Assessment assessment) {
		try {
			AssessmentRelation.addAssessment(assessment);
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
	@Path("{assessmentID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modify(final Assessment assessment,
			@PathParam("assessmentID") final String assessmentID) {

		try {
			boolean modifyStatus = AssessmentRelation.modifyAssessment(
					assessmentID, assessment);
			if (modifyStatus) {
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
	@Path("{assessmentID}")
	public Response deleteAssessment(
			@PathParam("assessmentID") final String assessmentID) {
		try {
			boolean deleteStatus = AssessmentRelation
					.deleteAssessment(assessmentID);
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

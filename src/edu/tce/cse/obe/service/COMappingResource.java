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

import edu.tce.cse.obe.database_abstraction.COMappingRelation;
import edu.tce.cse.obe.model.COPOMapping;

@Path("/{year}/department/{departmentID}/program/{programID}/course/{courseID}/mapping")
public class COMappingResource {

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response addCOMapping(COPOMapping row,
			@PathParam("year") final int year,
			@PathParam("courseID") final String courseID) {
		try {
			COMappingRelation.addCOMapping(row, courseID);
			return Response.status(Status.OK).build();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods",
							"GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response
					.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods",
							"GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.build();
		}
	}

	@PUT
	@Path("{courseOutcomeID}")
	@Consumes(MediaType.APPLICATION_XML)
	public Response modifyCOMapping(
			@PathParam("courseOutcomeID") final String courseOutcomeID,
			COPOMapping row, @PathParam("year") final int year,
			@PathParam("courseID") final String courseID) {
		try {

			boolean modificationStatus = COMappingRelation.modifyCOMapping(row,
					courseID, year);
			if (modificationStatus) {
				return Response
						.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods",
								"GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers", "Content-Type")
						.build();
			} else {
				return Response
						.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods",
								"GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers", "Content-Type")
						.build();
			}

		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods",
							"GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response
					.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods",
							"GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.build();
		}
	}

	@DELETE
	@Path("{courseOutcomeID}")
	public Response deleteCOMapping(
			@PathParam("courseOutcomeID") final String courseOutcomeID,
			@PathParam("year") final int year,
			@PathParam("courseID") final String courseID) {

		try {
			boolean deleteStatus = COMappingRelation.deleteCOMapping(
					courseOutcomeID, courseID, year);
			if (deleteStatus) {
				return Response
						.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods",
								"GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers", "Content-Type")
						.build();
			} else {
				return Response
						.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods",
								"GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers", "Content-Type")
						.build();
			}
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response
					.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods",
							"GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.build();
		}
	}

}

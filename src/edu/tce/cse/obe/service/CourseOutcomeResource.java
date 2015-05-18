package edu.tce.cse.obe.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

import edu.tce.cse.obe.database_abstraction.CourseOutcomeRelation;
import edu.tce.cse.obe.database_abstraction.CourseRelation;
import edu.tce.cse.obe.model.Course;
import edu.tce.cse.obe.model.CourseOutcome;

@Path("/{year}/department/{departmentID}/program/{programID}/course/{courseID}/courseOutcome")
public class CourseOutcomeResource {
	@GET
	public Response getCourseOutcomes(
			@PathParam("courseID") final String courseID,
			@PathParam("year") final int year) {
		List<CourseOutcome> courseOutcomeList = null;
		try {
			courseOutcomeList = CourseOutcomeRelation.getCourseOutcomes(
					courseID, year);
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
		CourseOutcome[] courseOutcomes = courseOutcomeList
				.toArray(new CourseOutcome[courseOutcomeList.size()]);
		return Response
				.ok(courseOutcomes, MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods",
						"GET, POST, DELETE, PUT")
				.header("Access-Control-Allow-Headers", "Content-Type").build();

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourseOutcome(CourseOutcome courseOutcome,
			@PathParam("courseID") final String courseID,
			@PathParam("year") final int year) {
		try {
			CourseOutcomeRelation.addCourseOutcome(courseOutcome, courseID);
			return Response
					.status(Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods",
							"GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers", "Content-Type")
					.build();
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
	public Response deleteCourseOutcome(
			@PathParam("courseOutcomeID") String courseOutcomeID,
			@PathParam("courseID") final String courseID,
			@PathParam("year") final int year) {

		try {
			boolean deleteStatus = CourseOutcomeRelation.deleteCourseOutcome(
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

	@PUT
	@Path("{courseOutcomeID}")
	public Response modifyCourseOutcome(
			@PathParam("courseOutcomeID") String courseOutcomeID,
			CourseOutcome CourseOutcome,
			@PathParam("courseID") final String courseID,
			@PathParam("year") final int year) {
		try {

			boolean modificationStatus = CourseOutcomeRelation
					.modifyCourseOutcome(CourseOutcome, courseOutcomeID,
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

}

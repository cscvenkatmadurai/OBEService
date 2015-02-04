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

import edu.tce.cse.obe.database_abstraction.CourseRelation;
import edu.tce.cse.obe.model.Course;

@Path("/{year}/department/{departmentID}/program/{programID}/course")
public class CourseResource {

	@GET
	public Response getCourses(@PathParam("programID") final String programID,
			@PathParam("year") final int year) {
		List<Course> courseList = null;
		try {
			courseList = CourseRelation.getCourses(programID, year);
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*").build();
		}
		Course[] courses = courseList.toArray(new Course[courseList.size()]);
		return Response.ok(courses, MediaType.APPLICATION_JSON)
				.header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCourse(Course course, @PathParam("year") final int year,
			@PathParam("programID") final String programID) {
		try {
			CourseRelation.addCourse(course, programID);
			return Response.status(Status.OK).build();
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
	@Path("{courseID}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifyCourse(@PathParam("courseID") final String courseID,
			Course course, @PathParam("year") final int year,
			@PathParam("programID") final String programID) {
		try {

			boolean modificationStatus = CourseRelation.modifyCourse(courseID,
					course, year, programID);
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
	@Path("{courseID}")
	public Response deleteCourse(@PathParam("courseID") final String courseID,
			@PathParam("year") final int year) {

		try {
			boolean deleteStatus = CourseRelation.deleteCourse(courseID, year);
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

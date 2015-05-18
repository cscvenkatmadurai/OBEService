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
import com.sun.xml.internal.ws.server.provider.AsyncProviderInvokerTube.AsyncWebServiceContext;

import edu.tce.cse.obe.database_abstraction.AssessmentRelation;
import edu.tce.cse.obe.database_abstraction.DepartmentRelation;
import edu.tce.cse.obe.database_abstraction.QuestionRelation;
import edu.tce.cse.obe.model.Assessment;
import edu.tce.cse.obe.model.Department;
import edu.tce.cse.obe.model.Question;

@Path("/question")
public class QuestionResource {

	@GET
	@Path("/{courseID}/{year}")
	public Response getQuestions(@PathParam("courseID") final String courseId,
			@PathParam("year") final int year) {

		List<Question> questionsList;

		try {
			questionsList = QuestionRelation.getQuestions(courseId, year);

			Question[] questionArray = questionsList
					.toArray(new Question[questionsList.size()]);

			return Response.ok(questionArray, MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (ParseException | SQLException e) {

			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		}

		// questions[0] = new Question(1, "A1", "REST?");
		// complete

	}

	@GET
	@Path("/{courseID}")
	public Response getQuestions(@PathParam("courseID") final String courseId) {

		List<Question> questionsList;

		try {
			questionsList = QuestionRelation.getQuestions(courseId);

			Question[] questionArray = questionsList
					.toArray(new Question[questionsList.size()]);

			return Response.ok(questionArray, MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (ParseException | SQLException e) {

			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		}

	}

	@GET
	@Path("/{courseID}/{year}/{assessmentId}/")
	public Response getQuestions(@PathParam("courseID") final String courseId,
			@PathParam("year") final int year,
			@PathParam("assessmentId") final String assessmentId) {

		List<Question> questionsList;

		try {
			questionsList = QuestionRelation.getQuestions(courseId,
					assessmentId, year);

			Question[] questionArray = questionsList
					.toArray(new Question[questionsList.size()]);

			return Response.ok(questionArray, MediaType.APPLICATION_JSON)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (ParseException | SQLException e) {
			System.out.println(e.getMessage());
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		}

	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(final Question question) {
		try {

			QuestionRelation.addQuestion(question);
			return Response.status(Status.OK)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		}
	}

	@DELETE
	@Path("/{courseId}/")
	public Response delete(@PathParam("courseId") final String courseId) {
		try {

			boolean status = QuestionRelation.deleteQuestionCourseId(courseId);

			if (status) {
				return Response.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					    .header("Access-Control-Allow-Headers","Content-Type")
					    .build();
			} else {
				return Response.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					    .header("Access-Control-Allow-Headers","Content-Type")
						.build();
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (SQLException e) {
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		}
	}

	@DELETE
	@Path("/{courseId}/{year}")
	public Response delete(@PathParam("courseId") final String courseId,
			@PathParam("year") final int year) {
		try {

			boolean status = QuestionRelation.deleteQuestionCourseIdYear(
					courseId, year);
			if (status) {
				return Response.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
						.build();
			} else {
				return Response.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
						.build();
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (SQLException e) {
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		}
	}

	@DELETE
	@Path("/{courseId}/{year}/{assessmentId}")
	public Response delete(@PathParam("courseId") String courseId,
			@PathParam("year") int year,
			@PathParam("assessmentId") String assessmentId) {
		try {

			boolean status = QuestionRelation
					.deleteQuestionCourseIdYearAssessmentId(courseId, year,
							assessmentId);
			if (status) {
				return Response.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
						.build();
			} else {
				return Response.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
						.build();
			}

		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
					.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		} catch (SQLException e) {
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{courseId}/{year}/{assessmentId}/{section}/{questionNo}")
	public Response update(final Question question,
			@PathParam("courseId") final String courseId,
			@PathParam("year") final int year,
			@PathParam("assessmentId") final String assessmentId,
			@PathParam("questionNo") final String questionNO,
			@PathParam("section") final String section) {
		try {
			boolean status = QuestionRelation.update(question, courseId, year,
					assessmentId, questionNO, section);
			if (status) {
				return Response.status(Status.OK)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
						.build();
			} else {
				return Response.status(Status.BAD_REQUEST)
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
						.build();
			}
		} catch (ClassNotFoundException | IOException e) {

			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
						.build();

		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Status.BAD_REQUEST)
					.header("Access-Control-Allow-Origin", "*")
					.header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
						.header("Access-Control-Allow-Headers","Content-Type")
					.build();
		}

	}

}

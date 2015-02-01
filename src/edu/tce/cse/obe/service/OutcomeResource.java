package edu.tce.cse.obe.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.tce.cse.obe.database_abstraction.CourseOutcomeRelation;
import edu.tce.cse.obe.database_abstraction.CourseRelation;
import edu.tce.cse.obe.database_abstraction.ExamSubdivisionRelation;
import edu.tce.cse.obe.database_abstraction.ProgramOutcomeRelation;
import edu.tce.cse.obe.database_abstraction.ProgramRelation;
import edu.tce.cse.obe.model.Course;
import edu.tce.cse.obe.model.CourseOutcome;
import edu.tce.cse.obe.model.ExamSubdivision;
import edu.tce.cse.obe.model.Program;
import edu.tce.cse.obe.model.ProgramOutcome;

@Path("/{year}/department/{departmentID}/program/{programID}/outcome")
public class OutcomeResource {

	@GET
	public Response getProgramOutcome(@PathParam("year") final int year,
			@PathParam("departmentID") final String departmentID,
			@PathParam("programID") final String programID)
			throws ClassNotFoundException, IOException, SQLException,
			ParseException {

		Program program = ProgramRelation.getProgramObject(programID, year);

		List<Course> courseList = CourseRelation.getCourses(programID, year);

		List<ProgramOutcome> programOutcomeList = computeOutcome(program,
				courseList);

		ProgramOutcome[] programOutcomes = programOutcomeList
				.toArray(new ProgramOutcome[programOutcomeList.size()]);

		return Response.ok(programOutcomes, MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("course/{courseID}")
	public Response getProgramOutcomeForCourse(
			@PathParam("year") final int year,
			@PathParam("departmentID") final String departmentID,
			@PathParam("programID") final String programID,
			@PathParam("courseID") final String courseID)
			throws ClassNotFoundException, IOException, SQLException,
			ParseException {

		Program program = ProgramRelation.getProgramObject(programID, year);
		Course course = CourseRelation.getCourseObject(courseID, year);

		List<Course> courseList = new LinkedList<Course>();
		courseList.add(course);

		List<ProgramOutcome> programOutcomeList = computeOutcome(program,
				courseList);

		ProgramOutcome[] programOutcomes = programOutcomeList
				.toArray(new ProgramOutcome[programOutcomeList.size()]);

		return Response.ok(programOutcomes, MediaType.APPLICATION_JSON).build();

	}

	private static List<ProgramOutcome> computeOutcome(Program program,
			List<Course> courseList) 
					throws ClassNotFoundException,IOException, SQLException, ParseException {

		List<ProgramOutcome> programOutcomeList = ProgramOutcomeRelation
				.getProgramOutcomes(program.getProgramID(), program.getYear());

		HashMap<CourseOutcome, Double> coAttainmentMap = new HashMap<CourseOutcome, Double>();
		for (Course course : courseList) {
			coAttainmentMap.putAll(computeCOAttainment(course));
		}

		for (ProgramOutcome programOutcome : programOutcomeList) {

			double weightTotal = 0.0;
			double sum = 0.0;

			for (CourseOutcome courseOutcome : coAttainmentMap.keySet()) {
				double weight =  mapPOCO(programOutcome, courseOutcome);
				sum += coAttainmentMap.get(courseOutcome) * weight;
				weightTotal += weight;
			}

			double programOutcomeAttainment = Double.NaN;
			if (weightTotal != 0.0) {
				programOutcomeAttainment = sum / weightTotal;
			}
			programOutcome.setPoAttainment(programOutcomeAttainment);

		}

		return programOutcomeList;
	}

	private static HashMap<CourseOutcome, Double> computeCOAttainment(
			Course course) throws FileNotFoundException,
			ClassNotFoundException, IOException, SQLException, ParseException {

		String courseID = course.getCourseID();
		int year = course.getYear();

		List<ExamSubdivision> examSubDivisionList;
		List<CourseOutcome> courseOutcomeList;

		examSubDivisionList = ExamSubdivisionRelation.getExamSubdivision(
				courseID, year);

		courseOutcomeList = CourseOutcomeRelation.getCourseOutcomes(courseID,
				year);

		HashMap<String, Double> coPercentageSumMap = new HashMap<>();
		HashMap<String, Integer> coCountMap = new HashMap<>();

		for (CourseOutcome courseOutcome : courseOutcomeList) {
			coPercentageSumMap.put(courseOutcome.getCoID(), 0.0);
			coCountMap.put(courseOutcome.getCoID(), 0);
		}

		for (ExamSubdivision examSubdivision : examSubDivisionList) {

			for (String coID : examSubdivision.getCOList()) {
				double percentage = 1.0 * examSubdivision.getRightAnswerCount()
						/ (1.0 * examSubdivision.getTotalAnswerCount());

				coPercentageSumMap.put(coID, coPercentageSumMap.get(coID)
						+ percentage);
				coCountMap.put(coID, coCountMap.get(coID) + 1);
			}

		}

		HashMap<CourseOutcome, Double> courseOutcomeAttainmentMap = new HashMap<>();

		for (CourseOutcome courseOutcome : courseOutcomeList) {
			Double coAttainment = 0.0;
			if (coCountMap.get(courseOutcome.getCoID()) != 0) {
				coAttainment = 100.0
						* coPercentageSumMap.get(courseOutcome.getCoID())
						/ (1.0 * coCountMap.get(courseOutcome.getCoID()));
			}

			courseOutcomeAttainmentMap.put(courseOutcome, coAttainment);
		}

		return courseOutcomeAttainmentMap;
	}

	private static int mapPOCO(ProgramOutcome po, CourseOutcome co) {

		return 1;
	}
}

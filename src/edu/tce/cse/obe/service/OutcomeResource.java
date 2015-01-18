package edu.tce.cse.obe.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.ClientResponse.Status;

import edu.tce.cse.obe.database_abstraction.COMappingRelation;
import edu.tce.cse.obe.database_abstraction.CourseOutcomeRelation;
import edu.tce.cse.obe.database_abstraction.ExamSubdivisionRelation;
import edu.tce.cse.obe.database_abstraction.ProgramOutcomeRelation;
import edu.tce.cse.obe.database_abstraction.ProgramRelation;
import edu.tce.cse.obe.model.Department;
import edu.tce.cse.obe.model.ExamSubdivision;
import edu.tce.cse.obe.model.CourseOutcome;
import edu.tce.cse.obe.model.Program;
import edu.tce.cse.obe.model.ProgramOutcome;

//NOT YET COMPLETE
@Path("/{year}/department/{departmentID}/program/{programID}/course/{courseID}/outcome")
public class OutcomeResource {

	@GET
	public Response getCourseAttainment(@PathParam("year") final int year,
			@PathParam("programID") final String programID,
			@PathParam("courseID") final String courseID) {

		/*List<ProgramOutcome> programOutcomeList = ProgramOutcomeRelation
				.getProgramOutcomes(programID, year);

		ProgramOutcome[] programOutcomes = programOutcomeList
				.toArray(new ProgramOutcome[programOutcomeList.size()]);*/
		
		
		return Response.ok(new Integer(COMappingRelation.getPOCOValue(courseID, "CO1", "PO1", year)).toString(), MediaType.TEXT_PLAIN).build();

	}

	private static HashMap<CourseOutcome, Double> computeCourseAttainment(
			String courseID, int year) throws FileNotFoundException,
			ClassNotFoundException, IOException, SQLException, ParseException {

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
			Double coAttainment = 100.0
					* coPercentageSumMap.get(courseOutcome.getCoID())
					/ (1.0 * coCountMap.get(courseOutcome.getCoID()));
			courseOutcomeAttainmentMap.put(courseOutcome, coAttainment);
		}

		return courseOutcomeAttainmentMap;
	}

	// public static List<ProgramOutcome> computeProgramOutcomeAttainment()
	
	private static int mapPOCO(ProgramOutcome po, CourseOutcome co){
		
		return 0;
	}
}

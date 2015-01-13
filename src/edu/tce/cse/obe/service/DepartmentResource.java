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

import edu.tce.cse.obe.model.Department;
import edu.tce.cse.obe.database_abstraction.DepartmentRelation;

@Path("/department")
public class DepartmentResource {

	@GET
	public Response getDepartments() {
		List<Department> departmentList;
		try {

			departmentList = DepartmentRelation.getDepartments();

			Department[] departments = departmentList
					.toArray(new Department[departmentList.size()]);
			return Response.ok(departments, MediaType.APPLICATION_XML).build();

		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response add(Department department) {
		
		try {
			 DepartmentRelation.addDepartment(department);
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
	@Path("{departmentID}")
	public Response deleteDepartment(@PathParam("departmentID")String departmentID) {
		try {
			boolean deleteStatus = DepartmentRelation.deleteDepartment(departmentID);
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
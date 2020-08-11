package az.hrmodule.api;

import az.hrmodule.dao.EmployeeDao;
import az.hrmodule.domain.Employee;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/employee")
public class EmployeeController {

    /*@GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{empId}")
    public String getEmployeeById(@PathParam("empId") String empId){
        EmployeeDao  employeeDao=new EmployeeDao();
        Employee e=employeeDao.getEmployeeById(empId);
        StringBuilder stringBuilder=new StringBuilder("");
        stringBuilder.append("<b>Name</b> : "+e.getLastName()+e.getFirstName());
        stringBuilder.append("<br><b>Email</b> : "+e.getEmail());

        return stringBuilder.toString();
    }*/


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{empId}")
    public Response getEmployeeById(@PathParam("empId") String empId){
        EmployeeDao  employeeDao=new EmployeeDao();
        Employee e=employeeDao.getEmployeeById(empId);
        return Response.ok().entity(e).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/search")
    public Response search(@QueryParam("empId") String empId,
                           @QueryParam("fullname") String fullname){
        EmployeeDao  employeeDao=new EmployeeDao();
        List<Employee> e=employeeDao.search(empId,fullname);
        return Response.ok().entity(e).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{empId}")
    public Response getEmployeeById(Employee employee){
        EmployeeDao  employeeDao=new EmployeeDao();
        Employee e=employeeDao.save(employee);
        return Response.ok().entity(e).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/department/{empId}")
    public String getEmployeeByDept(@PathParam("empId") String empId){
        EmployeeDao  employeeDao=new EmployeeDao();
        Employee e=employeeDao.getEmployeeById(empId);
        StringBuilder stringBuilder=new StringBuilder("");
        stringBuilder.append("<b>Name</b> : "+e.getLastName()+e.getFirstName());
        stringBuilder.append("<br><b>Email</b> : "+e.getEmail());
        stringBuilder.append("<br><b>By Dept</b> : "+empId);

        return stringBuilder.toString();
    }




}

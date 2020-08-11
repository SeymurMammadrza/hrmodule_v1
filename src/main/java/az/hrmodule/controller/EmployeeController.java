package az.hrmodule.controller;

import az.hrmodule.dao.DepartmentDao;
import az.hrmodule.dao.EmployeeDao;
import az.hrmodule.dao.JobDao;
import az.hrmodule.domain.Employee;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class EmployeeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute("loggeduser") != null) {
            if (req.getServletPath().equals("/employee/list")) {

                EmployeeDao employeeDao = new EmployeeDao();
                // System.out.println(employeeDao.getList());
                req.setAttribute("empList", employeeDao.getList());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/employeeList.jsp");
                requestDispatcher.include(req, resp);
            }


            if (req.getServletPath().equals("/employee/new")) {

                EmployeeDao employeeDao = new EmployeeDao();
                JobDao jobDao = new JobDao();
                DepartmentDao departmentDao = new DepartmentDao();

            /*for(Employee e:employeeDao.getList()){
                System.out.println(e.getDepartmentId());
            }*/
                req.setAttribute("empList", employeeDao.getList());
                req.setAttribute("jobList", jobDao.getList());
                req.setAttribute("deptList", departmentDao.getList());
                req.setAttribute("employee", new Employee());
                req.setAttribute("action", "save");

                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/employeeForm.jsp");
                requestDispatcher.include(req, resp);
            }


            if (req.getServletPath().equals("/employee/edit")) {

                EmployeeDao employeeDao = new EmployeeDao();
                JobDao jobDao = new JobDao();
                DepartmentDao departmentDao = new DepartmentDao();
                String empId = req.getParameter("employeeId");
                Employee employee = employeeDao.getEmployeeById(empId);
                req.setAttribute("empList", employeeDao.getList());
                req.setAttribute("jobList", jobDao.getList());
                req.setAttribute("deptList", departmentDao.getList());
                req.setAttribute("employee", employee);
                System.out.println(employee.toString());
                req.setAttribute("action", "update");
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/employeeForm.jsp");
                requestDispatcher.include(req, resp);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login");

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("loggeduser") != null) {
            if (req.getServletPath().equals("/employee/save")) {

                EmployeeDao employeeDao = new EmployeeDao();

                Employee employee = adddImage(req, resp);
                employeeDao.save(employee);


                req.setAttribute("empList", employeeDao.getList());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/employeeList.jsp");
                requestDispatcher.include(req, resp);
            }

            if (req.getServletPath().equals("/employee/update")) {

                EmployeeDao employeeDao = new EmployeeDao();
                Employee employee = new Employee();

                employee.setManagerId(Integer.parseInt(req.getParameter("manager_id")));
                employee.setDepartmentId(Integer.parseInt(req.getParameter("department_id")));
                employee.setFirstName(req.getParameter("first_name"));
                employee.setLastName(req.getParameter("last_name"));
                employee.setEmail(req.getParameter("email"));
                employee.setPhoneNumber(req.getParameter("phone_number"));
                employee.setJobId(req.getParameter("job_id"));
                employee.setCommissionPct(Double.parseDouble(req.getParameter("commission_pct")));
                employee.setSalary(Double.parseDouble(req.getParameter("salary")));

                employeeDao.save(employee);


                req.setAttribute("empList", employeeDao.getList());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("/views/employeeList.jsp");
                requestDispatcher.include(req, resp);
            } else {

                resp.sendRedirect(req.getContextPath() + "/login");
            }
        }
    }

    private boolean isMultipart;
    private String filePath;
    private int maxFileSize = 1024 * 1024 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    public void init() {
        // Get the file location where it would be stored.
        filePath = getServletContext().getInitParameter("file-upload");
    }


    public Employee adddImage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);
        Employee employee = new Employee();

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("C:\\hrapp\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();


            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();

                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String  fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    employee.setImage(fileName);

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    try {
                        fi.write(file);
                    }catch (Exception e){
                        employee.setImage(null);
                    }

                } else {

                    System.out.println("Field name "+fi.getFieldName());
                    System.out.println("Field value "+fi.getString());
                    if (fi.getFieldName().equals("manager_id")) {
                        employee.setManagerId(Integer.parseInt(fi.getString()));
                    }
                    if (fi.getFieldName().equals("department_id")) {
                        employee.setDepartmentId(Integer.parseInt(fi.getString()));
                    }
                    if (fi.getFieldName().equals("first_name")) {
                        employee.setFirstName(fi.getString());
                    }
                    if (fi.getFieldName().equals("last_name")) {
                        employee.setLastName(fi.getString());
                    }
                    if (fi.getFieldName().equals("email")) {
                        employee.setEmail(fi.getString());
                    }
                    if (fi.getFieldName().equals("phone_number")) {
                        employee.setPhoneNumber(fi.getString());
                    }
                    if (fi.getFieldName().equals("job_id")) {
                        employee.setJobId(fi.getString());
                    }
                    if (fi.getFieldName().equals("commission_pct")) {
                        employee.setCommissionPct(Double.parseDouble(fi.getString()));
                    }
                    if (fi.getFieldName().equals("salary")) {
                        employee.setSalary(Double.parseDouble(fi.getString()));
                    }


                }

            }
            System.out.println(employee);
        } catch (Exception ex) {

            System.out.println(ex);
        }

        return employee;
    }


}

package az.hrmodule.controller;

import az.hrmodule.dao.EmployeeDao;
import az.hrmodule.dao.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getServletPath().equals("/login")) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");
            UserDao userDao = new UserDao();

            if (userDao.login(email, password)) {
                HttpSession session = req.getSession();
                session.setAttribute("loggeduser", email);
                System.out.println("Success");
                resp.sendRedirect(req.getContextPath() + "/employee/list");
            } else {
                System.out.println("Error");
            }
            /*req.setAttribute("empList",employeeDao.getList());
            RequestDispatcher requestDispatcher=req.getRequestDispatcher("/views/employeeList.jsp");
            requestDispatcher.include(req,resp);*/
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getServletPath().equals("/login")) {
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        }

        if (req.getServletPath().equals("/logoff")) {
            HttpSession session=req.getSession();
            session.removeAttribute("loggeduser");
            session.invalidate();

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/index.jsp");
            requestDispatcher.forward(req, resp);
        }

    }
}

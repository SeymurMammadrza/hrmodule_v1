package az.hrmodule.dao;

import az.hrmodule.config.DBConfig;
import az.hrmodule.domain.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    private String select_query = "select  * from departments";

    public List<Department> getList() {
        DBConfig dbConfig = new DBConfig();
        List<Department> departments = new ArrayList<Department>();

        try {
            Connection conn = dbConfig.getConnect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select_query);

            while (resultSet.next()) {
                Department department = new Department();

                department.setLocationId(resultSet.getInt("location_id"));
                department.setManagerId(resultSet.getInt("manager_id"));
                department.setDepartmentId(resultSet.getInt("department_id"));
                department.setDepartmentName(resultSet.getString("department_name"));

                departments.add(department);
            }


            conn.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
        return departments;

    }
}

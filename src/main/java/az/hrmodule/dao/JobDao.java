package az.hrmodule.dao;

import az.hrmodule.config.DBConfig;
import az.hrmodule.domain.Job;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JobDao {

    private String select_query = "select  * from jobs";

    public List<Job> getList() {
        DBConfig dbConfig = new DBConfig();
        List<Job> jobs = new ArrayList<Job>();

        try {
            Connection conn = dbConfig.getConnect();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(select_query);

            while (resultSet.next()) {
                Job job = new Job();

                job.setJobId(resultSet.getString("job_id"));
                job.setJobTitle(resultSet.getString("job_title"));
                job.setMaxSalary(resultSet.getInt("max_salary"));
                job.setMinSalary(resultSet.getInt("min_salary"));

                jobs.add(job);
            }


            conn.close();
        } catch (Exception e) {
e.printStackTrace();

        }
        return jobs;

    }
}

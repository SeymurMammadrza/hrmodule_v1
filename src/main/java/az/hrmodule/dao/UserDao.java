package az.hrmodule.dao;

import az.hrmodule.config.DBConfig;

import java.sql.SQLException;

public class UserDao extends Database{


    public boolean login(String email,String password){
        DBConfig dbConfig=new DBConfig();
        conn=dbConfig.getConnect();


       String query="select * from hr_users t WHERE t.email=? AND t.password=? AND t.isactive=1";
        try {
            preparedStatement=conn.prepareStatement(query);
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                close();
                return true;
            }


       } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }finally {
            close();
        }



        return false;

    }


}

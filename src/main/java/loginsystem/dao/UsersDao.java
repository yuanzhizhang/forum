package loginsystem.dao;

import loginsystem.entity.User;
import loginsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao {
    public static User getUser(ResultSet resultSet) throws SQLException{
        String name = resultSet.getString("uname");
        String password  = resultSet.getString("upassword");

        User users = new User();
        users.setUname(name);
        users.setPassword(password);

        return users;
    }

    public static List<User> getUsers(){
        List<User> users = new ArrayList<User>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareCall("select * from users");
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                User user = getUser(resultSet);
                users.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        DBUtil.close(resultSet, statement, connection);
        return users;
    }

    public static int addUser(User user){
        Connection connection = DBUtil.getConnection();
        String sql = "INSERT INTO users VALUES (?,?);";
        int rows=0;

        try {
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1,user.getUname());
            statement.setString(2,user.getPassword());

            rows = statement.executeUpdate();
            DBUtil.close(null,statement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    public static int updateUser(User user){
        Connection connection = DBUtil.getConnection();
        String sql = "update users set upassword=? where uname=?;";
        int rows=0;

        try {
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1,user.getPassword());
            statement.setString(2,user.getUname());

            rows = statement.executeUpdate();
            DBUtil.close(null,statement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }


    public static void main(String[] args) {
        //获取用户数据
//        List<User> users= getUsers();
//        System.out.println(users.get(0));
        //测试添加用户
        int rows = addUser(new User("wh","123"));
        System.out.println(rows);
    }

}

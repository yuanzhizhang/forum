package loginsystem.dao;

import loginsystem.entity.Comment;
import loginsystem.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao {
    public static List<Comment> getCommentByArtID(String artID){
        List<Comment> comments =new ArrayList<Comment>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement =null;
        ResultSet resultSet = null;


        String sql = "select * from comment where artID = ?";
        try {
            statement = connection.prepareCall(sql);
            statement.setString(1, artID);

            resultSet = statement.executeQuery();
            while (resultSet.next()){
                String user = resultSet.getString("user");
                String comment_ = resultSet.getString("comment");
                Comment comment = new Comment(user,comment_);
                comments.add(comment);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return comments;
    }

    public static int addComment(Comment comment,String artID,String user){
        Connection connection = DBUtil.getConnection();
        String sql = "insert into comment (artID, user , comment) values (?, ?, ?)";
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1, artID);
            statement.setString(2, user);
            statement.setString(3, comment.getComment());


            rows = statement.executeUpdate();
            DBUtil.close(null, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }

//    public static void main(String[] args) {
//        List<Comment> comments = getCommentByArtID("1");
//        Comment comment = comments.get(0);
//        System.out.println(comment.getComment());
//    }
}

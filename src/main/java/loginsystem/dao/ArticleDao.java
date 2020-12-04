package loginsystem.dao;

import loginsystem.entity.Article;
import loginsystem.util.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ArticleDao {
    public static Article getArticle(ResultSet resultSet) throws SQLException {
        String title = resultSet.getString("title");
        String text  = resultSet.getString("text");
        String id  = resultSet.getString("id");

        Article article = new Article();
        article.setText(text);
        article.setTitle(title);
        article.setId(id);

        return article;
    }

    public static List<Article> getArticles() {
        List<Article> articles = new ArrayList<Article>();
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.prepareCall("select * from article");
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Article article = getArticle(resultSet);
                articles.add(article);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        DBUtil.close(resultSet, statement, connection);

        return articles;
    }

    public static List<Article> getPersonArticles(String name,int page, int limit) {
        List<Article> articles = new ArrayList<Article>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = DBUtil.getConnection();
        String sql = "select * from article where user = ? limit ?, ?;";

        try {
            statement = connection.prepareCall(sql);
            statement.setString(1,name);
            statement.setInt(2, (page - 1) * limit);
            statement.setInt(3, limit);

            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Article article = getArticle(resultSet);
                articles.add(article);
            }
            DBUtil.close(null,statement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return articles;
    }

    public static int delArticle(String title){
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        int rows=0;
        String sql = "delete from article where title = ?";
        try {
            statement = connection.prepareCall(sql);
            statement.setString(1,title);

            rows = statement.executeUpdate();
            DBUtil.close(null,statement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rows;
    }

    public static Article getOneArticle(String id){
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from article where id = ?;";
        Article article = new Article();
        try {
            statement = connection.prepareCall(sql);
            statement.setString(1,id);

            resultSet = statement.executeQuery();
            if (resultSet != null && resultSet.next())
            {
                article= getArticle(resultSet);
            }
            DBUtil.close(resultSet,statement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return article;
    }

    public static int updateArticle(Article article){
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        int rows=0;
        String sql = "update article set text=? where Title=?;";

        try {
            statement = connection.prepareCall(sql);
            statement.setString(1,article.getText());
            statement.setString(2,article.getTitle());
            System.out.println(article.getText());
            System.out.println(article.getTitle());

            rows = statement.executeUpdate();
            DBUtil.close(null,statement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rows;
    }

    public static int addArticle(Article article,String user){
        Connection connection = DBUtil.getConnection();
        PreparedStatement statement = null;
        int rows=0;
        String sql = "INSERT INTO article(title,text,user) VALUES (?,?,?);";

        try {
            statement = connection.prepareCall(sql);
            statement.setString(1,article.getTitle());
            statement.setString(2,article.getText());
            statement.setString(3,user);

            rows = statement.executeUpdate();
            DBUtil.close(null,statement,connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return rows;
    }

    public static int getCount(String name)
    {
        Connection connection = DBUtil.getConnection();
        String sql = "select count(*) from article where user = ?";
        int rows = 0;
        try {
            PreparedStatement statement = connection.prepareCall(sql);
            statement.setString(1,name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                rows = resultSet.getInt(1);
            }
            DBUtil.close(resultSet, statement, connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows;
    }
}

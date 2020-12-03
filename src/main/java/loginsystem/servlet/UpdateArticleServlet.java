package loginsystem.servlet;

import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import loginsystem.dao.ArticleDao;
import loginsystem.dao.UsersDao;
import loginsystem.entity.Article;
import loginsystem.entity.BaseResponse;
import loginsystem.entity.User;
import loginsystem.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/article/update")
public class UpdateArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String title = req.getParameter("title");
        String text = req.getParameter("text");
//        System.out.println(title);
//        System.out.println(text);
        Article article = new Article();
        article.setTitle(title);
        article.setText(text);
        int rows = ArticleDao.updateArticle(article);
        if(rows>0){
            resp.sendRedirect(req.getContextPath() + "/test.jsp");
            System.out.println("rows");
        }


    }
}

package loginsystem.servlet;

import loginsystem.dao.ArticleDao;
import loginsystem.entity.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/addarticle")
public class AddArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        HttpSession session = req.getSession();

//        System.out.println(session.getAttribute("name"));
        String title = req.getParameter("title");
        String text = req.getParameter("text");
        String user = (String) session.getAttribute("name");

        Article article = new Article(title,text);
        int rows = ArticleDao.addArticle(article,user);
        if(rows>0){
            resp.sendRedirect(req.getContextPath() + "/addarticle_msg.jsp");
        }
    }
}

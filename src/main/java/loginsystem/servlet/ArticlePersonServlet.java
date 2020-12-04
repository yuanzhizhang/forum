package loginsystem.servlet;

import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import loginsystem.dao.ArticleDao;
import loginsystem.entity.Article;
import loginsystem.entity.BaseResponse;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/article/person")
public class ArticlePersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        String page = req.getParameter("page");
        String limit = req.getParameter("limit");

        HttpSession session = req.getSession();
//        List<Article> articles = ArticleDao.getPersonArticles((String)session.getAttribute("name"));
//        StringUtils.isNullOrEmpty(page) ? 1 :
//        StringUtils.isNullOrEmpty(limit) ? 10 :
                List<Article> articles = ArticleDao.getPersonArticles((String)session.getAttribute("name"),Integer.parseInt(page),
                Integer.parseInt(limit));

        int rows = ArticleDao.getCount((String)session.getAttribute("name"));

        BaseResponse<List<Article>> response = new BaseResponse<List<Article>>();
        response.setCode(200);
        response.setCount(rows);
        response.setMsg("请求成功");
        response.setData(articles);

        Gson gson = new Gson();
        String json = gson.toJson(response);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}

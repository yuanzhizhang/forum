package loginsystem.servlet;

import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import loginsystem.dao.ArticleDao;
import loginsystem.entity.Article;
import loginsystem.entity.BaseResponse;
import sun.plugin.dom.core.Element;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/index.jsp")
public class ArticleAllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");


        List<Article> articles = ArticleDao.getArticles();
        String[] title=new String[articles.size()];
        String[] id=new String[articles.size()];
        for(int i=0;i<articles.size();i++){
            title[i] =articles.get(i).getTitle();
            id[i] = articles.get(i).getId();
        }

        String[] text=new String[articles.size()];
        for(int i=0;i<articles.size();i++){
            text[i] =articles.get(i).getText();
        }

        HttpSession session = req.getSession();
        session.setAttribute("title",title);
        session.setAttribute("id",id);
        resp.sendRedirect(req.getContextPath() + "/home.jsp");

//        BaseResponse<List<Article>> response = new BaseResponse<List<Article>>();
//        response.setCode(200);
//        response.setMsg("请求成功");
//        response.setData(articles);

//        Gson gson = new Gson();
//        String json = gson.toJson(response);
//        resp.setContentType("application/json;charset=utf-8");

//        PrintWriter out = resp.getWriter();
//        out.print("<html> <head> <title>首页</title> </head> <body> ");
//        for(int i=0;i<articles.size();i++){
//            out.print("<a>"+articles.get(i).getTitle()+"</a>");
//        }
//        out.print( "</body> "+"</html>");
//        out.flush();
    }
}

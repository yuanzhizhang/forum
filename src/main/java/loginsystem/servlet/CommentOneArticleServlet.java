package loginsystem.servlet;

import com.google.gson.Gson;
import com.mysql.cj.util.StringUtils;
import loginsystem.dao.CommentDao;
import loginsystem.entity.Article;
import loginsystem.entity.BaseResponse;
import loginsystem.entity.Comment;

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

@WebServlet(urlPatterns = "/comment/all")
public class CommentOneArticleServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("1");
        req.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();

        BaseResponse<List<Comment>> response = new BaseResponse<List<Comment>>();
        String artID = (String) session.getAttribute("artID");
        List<Comment> comments= CommentDao.getCommentByArtID(artID);
        System.out.println(2);
        response.setCode(200);
        response.setMsg("请求成功");
        response.setData(comments);

        Gson gson = new Gson();
        String json = gson.toJson(response);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();
    }
}

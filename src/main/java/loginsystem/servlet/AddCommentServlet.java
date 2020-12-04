package loginsystem.servlet;

import com.google.gson.Gson;
import loginsystem.dao.CommentDao;
import loginsystem.entity.BaseResponse;
import loginsystem.entity.Comment;
import loginsystem.util.RequestUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/add/comment")
public class AddCommentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();

        Gson gson = new Gson();
        String comJson = RequestUtil.getRequestBody(req);
        // 修改时提交的数据
        Comment comBody = gson.fromJson(comJson, Comment.class);

        String artID = (String) session.getAttribute("artID");
        String user = (String) session.getAttribute("name");

        int rows = CommentDao.addComment(comBody,artID,user);

        BaseResponse<Integer> baseResponse = new BaseResponse<Integer>();
        if (rows > 0)
        {
            baseResponse.setCode(200);
            baseResponse.setMsg("操作成功");
        }
        else
        {
            baseResponse.setCode(600);
            baseResponse.setMsg("操作失败");
        }
        baseResponse.setData(rows);

        String json = gson.toJson(baseResponse);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(json);
        out.flush();

    }

}

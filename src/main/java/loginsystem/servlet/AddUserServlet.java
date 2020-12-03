package loginsystem.servlet;

import com.google.gson.Gson;
import loginsystem.dao.UsersDao;
import loginsystem.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login/adduser")
public class AddUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User(username,password);
        int rows = UsersDao.addUser(user);
        if(rows>0){
            resp.sendRedirect(req.getContextPath() + "/add_msg.jsp");
        }
    }
}

package loginsystem.servlet;

import loginsystem.dao.UsersDao;
import loginsystem.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login/update")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = new User();
        user.setUname(username);
        user.setPassword(password);
        int rows = UsersDao.updateUser(user);
        if(rows>0){
            resp.sendRedirect(req.getContextPath() + "/update_msg.jsp");
        }
    }
}

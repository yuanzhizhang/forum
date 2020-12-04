package loginsystem;

import com.mysql.cj.Session;
import loginsystem.dao.UsersDao;
import loginsystem.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        HttpSession session =req.getSession();

        ////获取输入的用户名和密码
        String name = req.getParameter("username");
        String pwd = req.getParameter("password");

        //获取数据库中的用户数据来判断登录
        List<User> users = UsersDao.getUsers();
        Iterator iterator = users.iterator();

        while(iterator.hasNext()){
            User user = (User) iterator.next();
            if(name.equals(user.getUname())){
                session.setAttribute("name",user.getUname());
                if(pwd.equals(user.getPassword())){
                    System.out.println("111");

//                    resp.sendRedirect(req.getContextPath() + "/admin.jsp");
                    req.getRequestDispatcher("admin.jsp").forward(req,resp);
                    break;
                }
            }
        }
    }
}


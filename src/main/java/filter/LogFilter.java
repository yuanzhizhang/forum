package filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;
public class LogFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 添加日志
        String curDate = new Date().toString();
        String ip = request.getRemoteAddr();
        System.out.println(curDate + " ip: " + ip);
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
//package filter;
//
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.Date;
//
//public class LogFilter implements Filter {
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        String curDate = new Date().toString();
//        String address = request.getRemoteAddr();
//        System.out.println(curDate + " ip:" + address);
//        HttpServletRequest httpRequest =(HttpServletRequest)request;
//        HttpServletResponse httpResponse = (HttpServletResponse)response;
//        HttpSession session = httpRequest.getSession();
//        String name =(String) session.getAttribute("name");
//
//        String requestURI = httpRequest.getRequestURI();
//        String page = requestURI.substring(httpRequest.getContextPath().length());
//
////        if (page.equals("/login")){
////            chain.doFilter(request,response);
////        }
////
////        if (page.equals("/login/adduser")){
////            chain.doFilter(request,response);
////        }
////
////        if (page.equals("updateuser.jsp")){
////            chain.doFilter(request,response);
////        }
////
////        if (name==null){
////            httpRequest.getRequestDispatcher("login.jsp").forward(request,response);
////        }
//
//        chain.doFilter(request, response);
//    }
//
//    public void destroy() {
//
//    }
//}
////package filter;
////
////import javax.servlet.*;
////import java.io.IOException;
////import java.util.Date;
////s
////public class LogFilter implements Filter {
////    public void init(FilterConfig filterConfig) throws ServletException {
////
////    }
////
////    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
////        String curDate = new Date().toString();
////        String address = request.getRemoteAddr();
////        System.out.println(curDate + " ip:" + address);
////        chain.doFilter(request, response);
////    }
////
////    public void destroy() {
////
////    }
////}
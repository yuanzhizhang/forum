package filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String curDate = new Date().toString();
        String address = request.getRemoteAddr();
        System.out.println(curDate + " ip:" + address);
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}

package ua.simpleproject.filters;

import javax.security.auth.login.Configuration;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class SecurityMainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        if (role != null) {
            filterChain.doFilter(request, response);
        } else {
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    public void destroy() {
    }

    private String getStringCommand(String URI, Set<String> endPoints){
        for (String endPoint: endPoints) {
            if(URI.contains(endPoint))
                return endPoint;
        }
        return null;
    }
}

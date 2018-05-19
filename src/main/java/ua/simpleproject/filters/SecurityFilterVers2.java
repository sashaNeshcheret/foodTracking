package ua.simpleproject.filters;

import ua.simpleproject.authentification.Authentification;
import ua.simpleproject.configaration.SecurityConfiguraton;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class SecurityFilterVers2 implements Filter {
    int i =0;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("start filter" + ++i);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        SecurityConfiguraton configuraton = SecurityConfiguraton.getInstance();
        String command = getStringCommand(request.getRequestURI(),configuraton.getEndPoints());
        System.out.println(command);
        String role = configuraton.security(command);
        System.out.println(role);
        response.getWriter().write("security filter");
        if ("ALL".equals(role)) {
            request.setAttribute("command",command);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (("AUTH".equals(role))
                && Authentification.isUserLogIn(request.getSession())) {
            request.setAttribute("command",command);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (("AUTH".equals(role)
                && !Authentification.isUserLogIn(request.getSession()))) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
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

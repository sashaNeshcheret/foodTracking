package ua.simpleproject.filters;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Date;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class LogFilter implements Filter {
    private static String logFile ;
    private static File loggerFile;

    //private static final Logger logger = Logger.getLogger("LogFilter.class.getName()");

    static {
        logFile = "C:\\Users\\Нещерет\\IdeaProjects\\SimpleProject2\\src\\main" +
                "\\resources\\filterLog.txt";
        loggerFile = new File(logFile);
    }
    public LogFilter() {
    }

    @Override
    public void init(FilterConfig fConfig){
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        response.getWriter().write("loger filter");


        HttpServletRequest req = (HttpServletRequest) request;
        String servletPath = req.getServletPath();
        Writer writer = response.getWriter();
        writer.write(request.getParameter("commad"));
        writer.write(((HttpServletRequest) request).getContextPath());
        writer.write(((HttpServletRequest) request).getRequestURI());
        writer.write(((HttpServletRequest) request).getServletPath());

        writer.write(((HttpServletRequest) request).getMethod());
        writer.write(((HttpServletRequest) request).getPathInfo());
        writer.write(((HttpServletRequest) request).getQueryString());

        writer.write(((HttpServletRequest) request).getRequestURL().toString());
        writer.write(((HttpServletRequest) request).getPathInfo());
        writer.write(((HttpServletRequest) request).getQueryString());
        /*try(Writer bufferedWriter = new BufferedWriter(new FileWriter(loggerFile));) {
            bufferedWriter.write("#INFO " + new Date() + " - ServletPath :" + servletPath
                    + ", URL =" + req.getRequestURL());
        }
        logger.info("#INFO " + new Date() + " - ServletPath :" + servletPath
                + ", URL =" + req.getRequestURL());
*/
        chain.doFilter(request, response);
    }
}
package ua.simpleproject.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class OutputServlet extends HttpServlet{
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userName = (String) request.getAttribute("name");

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        writer.println("<html><body>");
        writer.println("Hello "+userName);
        writer.println("filerParam"+request.getAttribute("Locale"));
        writer.println("<body/></html>");
        writer.close();
    }
}

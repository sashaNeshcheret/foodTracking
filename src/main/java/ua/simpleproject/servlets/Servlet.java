package ua.simpleproject.servlets;

import ua.simpleproject.comand.ActionCommand;
import ua.simpleproject.comand.ActionFactory;
import ua.simpleproject.configaration.MessageManager;
import ua.simpleproject.transactions.ConnectionPool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


public class Servlet  extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        if (ConnectionPool.getConnectionPool().getDataSource() == null) {
            try {
                Context context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/cash_register");//
                ConnectionPool.getConnectionPool().setDataSource(dataSource);
            } catch (NamingException e) {
                e.printStackTrace();
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
// определение команды, пришедшей из JSP
        ActionFactory client = new ActionFactory();
        ActionCommand command = client.defineCommand(request);
System.out.println("command from Servlet "+ command.toString());
        /*
         * вызов реализованного метода execute() и передача параметров
         * классу-обработчику конкретной команды
         */
        page = command.execute(request);
        System.out.println("QQQQQQQQQ" + page);
// метод возвращает страницу ответа
// page = null; // поэксперементировать!
        if (page != null) {
            request.setAttribute("names", " sorry, but here name is not avialable");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
// вызов страницы ответа на запрос
            dispatcher.forward(request, response);
        } else {
// установка страницы c cообщением об ошибке
            //page = ConfigurationManager.getProperty("path.page.index");
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
            request.getRequestDispatcher(page).forward(request,response);
        }
    }
}

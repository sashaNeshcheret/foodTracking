package ua.trackingFood.servlet;

import ua.trackingFood.command.Command;
import ua.trackingFood.command.FactoryCommand;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;


public class Servlet1 extends HttpServlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = config;
        if (ua.trackingFood.transactions.ConnectionPool.getConnectionPool().getDataSource() == null) {
            try {
                Context context = new InitialContext();
                DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/tracking_food");//
                ua.trackingFood.transactions.ConnectionPool.getConnectionPool().setDataSource(dataSource);
            } catch (NamingException e) {
//logger.error("Error in init method. misplaced name of the database location\n");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandStr = request.getParameter("command");
        FactoryCommand factoryCommand = FactoryCommand.getInstance();
        Command command = factoryCommand.getCommand(commandStr);
        command.execute(request, response);
    }
}

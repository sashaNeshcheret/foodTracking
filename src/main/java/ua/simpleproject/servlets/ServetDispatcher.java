package ua.simpleproject.servlets;

import org.apache.log4j.Logger;
import ua.simpleproject.command.Command;
import ua.simpleproject.command.FactoryCommand;
import ua.simpleproject.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ServetDispatcher extends HttpServlet{

    private Logger logger = Logger.getLogger(ServetDispatcher.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        FactoryCommand factory = FactoryCommand.getInstance();
        String attrComand = (String) req.getAttribute("command");
        if(attrComand != null){
            Command command = factory.getCommand(attrComand);
            try {
                command.execute(req,resp);
            } catch (ServletException e) {
                e.printStackTrace();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            catch (DAOException e) {
                e.printStackTrace();
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            req.getRequestDispatcher("index.html");
        }
    }
}

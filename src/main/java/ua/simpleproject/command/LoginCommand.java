package ua.simpleproject.command;

import org.apache.log4j.Logger;
import ua.simpleproject.exception.DAOException;
import ua.simpleproject.services.LoginService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginCommand implements Command {
    private Logger logger = Logger.getLogger(LoginCommand.class);
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, DAOException {
   /*     String login = request.getParameter("login");
        String password = request.getParameter("password");

        if(Objects.isNull(login) || Objects.isNull(password)) {
            request.getRequestDispatcher("error.jsp");
        }
        LoginService loginService = LoginService.getLoginService();
        if(loginService.verify(login,password)){
            HttpSession session = request.getSession();
            session.setAttribute("login",login);
            request.getRequestDispatcher(FactoryCommand.USERS).forward(request,response);

        }
        request.setAttribute("errorMessageLogin","Lonin incorrect");
        request.getRequestDispatcher(FactoryCommand.LOGIN).forward(request,response);
    */}
}

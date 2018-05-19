package ua.trackingFood.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LogOutCommand implements Command {
    private static final Logger logger = Logger.getLogger("LogOutCommand.class");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* в случае ошибки или прямого обращения к контроллеру
         * переадресация на страницу ввода логина */
        logger.info("LogOut Command has been called");
        request.getSession().invalidate();
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
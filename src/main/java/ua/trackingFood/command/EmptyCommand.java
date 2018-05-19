package ua.trackingFood.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class EmptyCommand implements Command {
    private static final Logger logger = Logger.getLogger("EmptyCommand.class");

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* в случае ошибки или прямого обращения к контроллеру
         * переадресация на страницу ввода логина */
        logger.info("Empty Command has been called");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
}
package ua.trackingFood.command;

import org.apache.log4j.Logger;
import ua.trackingFood.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ua.trackingFood.utils.resourceHolders.PagesHolder.LOGIN_PAGE;

public class EmptyCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(EmptyCommand.class.getSimpleName());

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* в случае ошибки или прямого обращения к контроллеру
         * переадресация на страницу ввода логина */
        LOGGER.info("Empty Command has been called");
        request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
    }
}